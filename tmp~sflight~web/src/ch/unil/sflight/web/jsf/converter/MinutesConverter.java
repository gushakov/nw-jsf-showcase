package ch.unil.sflight.web.jsf.converter;

import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class MinutesConverter implements Converter{

	private static DecimalFormat minutesFormat = new DecimalFormat("00");
	
	
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String serialized) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
		//convert minutes to hours and minutes	
		if (! (obj instanceof Integer)){
			throw new ConverterException();
		}		
		int minutesAll = ((Integer)obj).intValue();
		int hours = (int) minutesAll / 60;
		int minutes = minutesAll % 60;
		
		return hours + ":" + minutesFormat.format(minutes) + " min";
	}

}
