package ch.unil.sflight.web.jsf.view;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import ch.unil.sflight.ejb.JraManagerLocal;
import ch.unil.sflight.java.dto.BookingData;
import ch.unil.sflight.java.exception.BapiException;
import ch.unil.sflight.web.jsf.utils.Utils;

import com.sap.tc.logging.Location;
import com.sap.tc.logging.Severity;
import com.sap.tc.logging.SimpleLogger;

@ManagedBean
@SessionScoped
public class SearchBookings implements Serializable {
	private static final Location loc = Location
			.getLocation(SearchBookings.class);
	private static final long serialVersionUID = 1L;

	private transient JraManagerLocal jraManager;

	private String airlineId;
	private String customerId;

	private Collection<BookingData> bookings;

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		jraManager = Utils.getBean("jraManager", JraManagerLocal.class);
	}

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Collection<BookingData> getBookings() {
		return bookings;
	}

	public void setBookings(Collection<BookingData> bookings) {
		this.bookings = bookings;
	}

	public void search(ActionEvent event) {
		SimpleLogger.trace(Severity.DEBUG, loc,
				"Searching for bookings with airlineId {0} and customerId {1}",
				(Object) airlineId, (Object) customerId);
		try {
			bookings = jraManager.searchBookings(airlineId, customerId);
			if (bookings.size() == 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						Utils.makeFacesMessage(FacesMessage.SEVERITY_INFO,
								"info", "no_bookings_found"));
			}
		} catch (BapiException e) {
			bookings = null;
			Utils.processBapiErrors(e, loc);
		}
	}

}
