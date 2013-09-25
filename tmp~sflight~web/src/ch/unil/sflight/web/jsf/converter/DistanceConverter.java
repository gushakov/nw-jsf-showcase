package ch.unil.sflight.web.jsf.converter;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class DistanceConverter implements Converter {

	private DecimalFormat format;
	
	public DistanceConverter() {
		try {
			format = new DecimalFormat("##,###,##0.0000");
			format.setRoundingMode(RoundingMode.UNNECESSARY);
		} catch (IllegalArgumentException e) {
			throw new ConverterException(e);
		}

	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		//convert from Double to string
		if (!(obj instanceof Double)){
			throw new ConverterException();
		}
		return format.format(((Double)obj).doubleValue());
	}

}
