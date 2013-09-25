package ch.unil.sflight.web.jsf.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public boolean isBookingAgent(){
		return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("SFLIGHT_BOOK");
	}

}
