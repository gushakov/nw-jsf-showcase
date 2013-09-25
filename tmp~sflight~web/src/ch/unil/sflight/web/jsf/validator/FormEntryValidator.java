package ch.unil.sflight.web.jsf.validator;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import ch.unil.sflight.web.jsf.utils.Utils;

import com.sap.tc.logging.Location;
import com.sap.tc.logging.Severity;
import com.sap.tc.logging.SimpleLogger;

public class FormEntryValidator implements Validator {
	private static final Location loc = Location
			.getLocation(FormEntryValidator.class);

	// airline ID pattern: two to three letters
	private static final Pattern airlineIdPattern = Pattern.compile("^[a-z]{2,3}$", Pattern.CASE_INSENSITIVE);

	// customer ID pattern: digits only
	private static final Pattern digitsPattern = Pattern.compile("^\\d+$");
	
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
				
		// airlineId
		if (component.getId().equalsIgnoreCase("airlineId")) {
			if (value != null) {
				SimpleLogger.trace(Severity.DEBUG, loc, "Validating airline ID value {0}", value);
				Matcher matcher = airlineIdPattern.matcher((String) value);
				if (!matcher.matches()) {
					throw new ValidatorException(Utils.makeFacesMessage(
							FacesMessage.SEVERITY_ERROR, "error",
							"airline_id_invalid"));
				}
			}
		}
		
		//customerId, connectId, counter
		else if (component.getId().equalsIgnoreCase("customerId")
				|| component.getId().equalsIgnoreCase("connectId")
				|| component.getId().equalsIgnoreCase("counter")) {
			if (value != null) {
				SimpleLogger.trace(Severity.DEBUG, loc, "Validating numeric value {0}", value);
				Matcher matcher = digitsPattern.matcher((String) value);
				if (!matcher.matches()) {
					throw new ValidatorException(Utils.makeFacesMessage(
							FacesMessage.SEVERITY_ERROR, "error",
							"not_digits"));
				}
			}
		}
		
		//flightDate
		else if (component.getId().equalsIgnoreCase("flightDate")) {
			if (value != null) {
				SimpleLogger.trace(Severity.DEBUG, loc, "Validating flight date value {0}", value);
				Date date = (Date) value;
				if ( date.before(new Date()) ){
					throw new ValidatorException(Utils.makeFacesMessage(
							FacesMessage.SEVERITY_ERROR, "error",
							"past_date"));
				}
			}
		}

	}

}
