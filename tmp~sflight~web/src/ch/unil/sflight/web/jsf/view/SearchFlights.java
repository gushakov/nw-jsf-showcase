package ch.unil.sflight.web.jsf.view;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import ch.unil.sflight.ejb.JraManagerLocal;
import ch.unil.sflight.java.dto.AdditionalInfo;
import ch.unil.sflight.java.dto.FlightData;
import ch.unil.sflight.java.exception.BapiException;
import ch.unil.sflight.web.jsf.utils.Utils;

import com.sap.tc.logging.Location;
import com.sap.tc.logging.Severity;
import com.sap.tc.logging.SimpleLogger;

@ManagedBean
@SessionScoped
public class SearchFlights implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Location loc = Location
			.getLocation(SearchFlights.class);

	private transient JraManagerLocal jraManager;

	private String airlineId;
	private String destFrom;;
	private String destTo;

	private Collection<FlightData> flights;

	private AdditionalInfo details;

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	public String getDestFrom() {
		return destFrom;
	}

	public void setDestFrom(String destFrom) {
		this.destFrom = destFrom;
	}

	public String getDestTo() {
		return destTo;
	}

	public void setDestTo(String destTo) {
		this.destTo = destTo;
	}

	public Collection<FlightData> getFlights() {
		return flights;
	}

	public void setFlights(Collection<FlightData> flights) {
		this.flights = flights;
	}

	public AdditionalInfo getDetails() {
		return details;
	}

	public void setDetails(AdditionalInfo details) {
		this.details = details;
	}

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		jraManager = Utils.getBean("jraManager", JraManagerLocal.class);
	}

	public void search(ActionEvent event) {
		SimpleLogger
				.trace(
						Severity.DEBUG,
						loc,
						"Search for flights with airlineId {0}, destFrom {1}, destTo {2}",
						(Object) airlineId, (Object) destFrom, (Object) destTo);
		try {
			flights = jraManager.searchFlights(airlineId, destFrom, destTo);
			SimpleLogger.trace(Severity.DEBUG, loc, "Found {0} flights ",
					(Object) flights.size());
			if (flights.size() == 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						Utils.makeFacesMessage(FacesMessage.SEVERITY_INFO,
								"info", "no_flights_found"));
			}
		} catch (BapiException e) {
			flights = null;
			Utils.processBapiErrors(e, loc);
		}

	}

	public void fetchDetails(ActionEvent event) {

		String id = (String) event.getComponent().getAttributes().get(
				"airlineId");
		String connectId = (String) event.getComponent().getAttributes().get(
				"connectId");
		String flightDate = Utils.formatDate((Date) event.getComponent()
				.getAttributes().get("flightDate"));

		SimpleLogger
				.trace(
						Severity.DEBUG,
						loc,
						"Show flight details for airlineId {0}, connectId {1}, flightDate {2}",
						(Object) id, (Object) connectId, (Object) flightDate);

		try {
			details = jraManager.getFlightDetails(id, connectId, flightDate);
		} catch (BapiException e) {
			Utils.processBapiErrors(e, loc);
		}

	}

}
