package ch.unil.sflight.web.jsf.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class CurrencyConverter implements Converter {
	
	private DecimalFormat format;
	
	public CurrencyConverter(){
		try {
			format = new DecimalFormat("##,###,##0.00");
			format.setRoundingMode(RoundingMode.UNNECESSARY);
		} catch (IllegalArgumentException e) {
			throw new ConverterException(e);
		}
	}
	
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String serialized) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
		//convert from BigDecimal to string
		if (!(obj instanceof BigDecimal)){
			throw new ConverterException();
		}
		BigDecimal dec = (BigDecimal)obj;
		String str = null;
		try {
			str = format.format(dec.doubleValue());
		} catch (ArithmeticException e) {
			throw new ConverterException(e);
		}
		return str;
	}

}
