package ch.unil.sflight.web.jsf.utils;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ch.unil.sflight.java.exception.BapiException;
import ch.unil.sflight.java.jra.Bapiret2;
import ch.unil.sflight.web.jsf.Constants;

import com.sap.tc.logging.Location;
import com.sap.tc.logging.Severity;
import com.sap.tc.logging.SimpleLogger;

public class Utils {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	/**
	 * Retrieves a bean with id given by <code>beanName</code> from current {@linkplain WebApplicationContext}.
	 * @param <T> any type
	 * @param beanName id of the bean
	 * @param beanClass type of the bean
	 * @return typed bean from the application context
	 * @throws IllegalStateException if application context could not be retrieved
	 * @throws BeansException if the bean with the given id could not be found
	 * @throws ClassCastException if the retrieved bean cannot be cast to the provided type
	 */
	public static <T> T getBean(String beanName, Class<T> beanClass) {
		WebApplicationContext appContext = WebApplicationContextUtils
				.getWebApplicationContext((ServletContext) FacesContext
						.getCurrentInstance().getExternalContext().getContext());

		if (appContext == null) {
			throw new IllegalStateException(
					"Cannot retrieve Spring web application context");
		}

		return beanClass.cast(appContext.getBean(beanName));
	}

	public static String getResource(String baseName, String key,
			String defaultValue, Object... args) {
		String message = null;
		try {
			String template = ResourceBundle
					.getBundle(
							baseName,
							FacesContext.getCurrentInstance().getViewRoot()
									.getLocale()).getString(key);
			if (args != null && args.length > 0) {
				message = MessageFormat.format(template, args);
			} else {
				message = template;
			}
		} catch (MissingResourceException e) {
			message = defaultValue;
		}
		return message;
	}

	public static FacesMessage makeFacesMessage(
			javax.faces.application.FacesMessage.Severity severity,
			String summaryKey, String detailKey, Object... detailArgs) {

		String bundle = null;
		if (severity == FacesMessage.SEVERITY_ERROR) {
			bundle = Constants.ERRORS_BASENAME;
		} else {
			bundle = Constants.MESSAGES_BASENAME;
		}
		String summary = getResource(bundle, summaryKey, summaryKey);
		String details = getResource(bundle, detailKey, detailKey, detailArgs);
		return new FacesMessage(severity, summary, details);
	}

	public static String getRequestParameter(String paramName) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(paramName);
	}

	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}

	public static void processBapiErrors(BapiException e, Location loc) {
		if (loc == null) {
			loc = Location.getLocation(Utils.class);
		}
		Bapiret2[] errors = e.getBapiErrors();
		if (errors != null) {
			for (Bapiret2 error : errors) {
				SimpleLogger.trace(Severity.ERROR, loc, error.getMessage());
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "BAPI error", error
								.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
	}
}
