package ch.unil.sflight.ejb;
import java.util.Collection;

import javax.ejb.Local;

import ch.unil.sflight.java.dto.AdditionalInfo;
import ch.unil.sflight.java.dto.AirlineData;
import ch.unil.sflight.java.dto.BookingData;
import ch.unil.sflight.java.dto.FlightBooking;
import ch.unil.sflight.java.dto.FlightData;
import ch.unil.sflight.java.exception.BapiException;

@Local
public interface JraManagerLocal {
	public String checkRfcStatus();
	public Collection<FlightData> searchFlights(String airlineId, String destFrom, String destTo) throws BapiException;
	public AdditionalInfo getFlightDetails(String airlineId, String connectId, String flightDate) throws BapiException;
	public Collection<AirlineData> getAirlines();
	public Collection<BookingData> searchBookings(String airlineId, String customerId) throws BapiException;
	public FlightBooking bookFlight(String airlineId, String connectId, String flightDate, String customerId, String counter, String passName) throws BapiException;
}
