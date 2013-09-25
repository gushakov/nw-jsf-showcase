package ch.unil.sflight.web.jsf.view;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import ch.unil.sflight.ejb.JraManagerLocal;
import ch.unil.sflight.java.dto.FlightBooking;
import ch.unil.sflight.java.exception.BapiException;
import ch.unil.sflight.web.jsf.utils.Utils;

import com.sap.tc.logging.Location;
import com.sap.tc.logging.Severity;
import com.sap.tc.logging.SimpleLogger;

@ManagedBean
@SessionScoped
public class BookFlight implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Location loc = Location.getLocation(BookFlight.class);

	private transient JraManagerLocal jraManager;

	private String airlineId;
	private String connectId;
	private String customerId;
	private Date flightDate;
	private String counter;
	private String passName;

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	public String getConnectId() {
		return connectId;
	}

	public void setConnectId(String connectId) {
		this.connectId = connectId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

	public String getCounter() {
		return counter;
	}

	public void setCounter(String counter) {
		this.counter = counter;
	}

	public String getPassName() {
		return passName;
	}

	public void setPassName(String passName) {
		this.passName = passName;
	}
	
	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		jraManager = Utils.getBean("jraManager", JraManagerLocal.class);
	}

	public void book(ActionEvent event) {
		try {
			SimpleLogger.trace(Severity.DEBUG, loc, "Booking flight: "
					+ this.toString());
			FlightBooking booking = jraManager
					.bookFlight(airlineId, connectId, Utils
							.formatDate(flightDate), customerId, counter,
							passName);
			FacesMessage message = Utils.makeFacesMessage(
					FacesMessage.SEVERITY_INFO, "info", "booking_created",
					(Object) booking.getBookingNumber(), (Object) booking
							.getAirlineId(), (Object) booking.getTiketPrice(),
					(Object) booking.getCurrency());
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (BapiException e) {
			Utils.processBapiErrors(e, loc);
		}
	}

}
