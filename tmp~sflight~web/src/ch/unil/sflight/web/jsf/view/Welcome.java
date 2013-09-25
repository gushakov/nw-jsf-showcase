package ch.unil.sflight.web.jsf.view;

import java.io.Serializable;
import java.util.Locale;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import ch.unil.sflight.ejb.JraManagerLocal;
import ch.unil.sflight.web.jsf.utils.Utils;

import com.sap.tc.logging.Location;
import com.sap.tc.logging.Severity;
import com.sap.tc.logging.SimpleLogger;

@ManagedBean
@SessionScoped
public class Welcome implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Location loc = Location.getLocation(Welcome.class);

	private JraManagerLocal jraManager;

	private String theme;

	private TreeMap<String, String> themes;

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public TreeMap<String, String> getThemes() {
		return themes;
	}

	public Locale getLocale() {
		return FacesContext.getCurrentInstance().getViewRoot().getLocale();
	}
	
	public String getHashcode(){
		return toString();
	}

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		jraManager = Utils.getBean("jraManager", JraManagerLocal.class);

		theme = "cupertino-sap";
		themes = new TreeMap<String, String>();
		themes.put("Cupertino", "cupertino-sap");
		themes.put("Redmond", "redmond-sap");
	}

	public void testRfc(ActionEvent event) {
		SimpleLogger.trace(Severity.DEBUG, loc, "Testing RFC connetion");
		FacesContext.getCurrentInstance().addMessage(
				null,
				Utils.makeFacesMessage(FacesMessage.SEVERITY_INFO,
						"call_rfc_returned", jraManager.checkRfcStatus()));
	}

	public void saveTheme() {
		SimpleLogger.trace(Severity.DEBUG, loc,
				"User changed the current theme to {0}", (Object) theme);
	}
}
