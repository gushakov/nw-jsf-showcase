/*
 * Based on examples from
 * http://www.captaindebug.com/2011/04/auto-wiring-ejb3-beans-with-spring.html
 * http://static.springsource.org/spring/docs/3.1.x/spring-framework-reference/html/cci.html
 */

package ch.unil.sflight.ejb;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;
import org.springframework.stereotype.Component;

import ch.unil.sflight.java.dto.AdditionalInfo;
import ch.unil.sflight.java.dto.AirlineData;
import ch.unil.sflight.java.dto.BookingData;
import ch.unil.sflight.java.dto.FlightBooking;
import ch.unil.sflight.java.dto.FlightData;
import ch.unil.sflight.java.exception.BapiException;
import ch.unil.sflight.java.jra.Bapiret2;
import ch.unil.sflight.java.jra.JraDao;

import com.sap.tc.logging.Location;

/**
 * Session Bean implementation class JraManagerBean
 */
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Stateless
@Component
public class JraManagerBean implements JraManagerLocal {

	@SuppressWarnings("unused")
	private static final Location loc = Location.getLocation(JraManagerBean.class);
		
	@Autowired
	private JraDao jraDao;
	
	@Override
	public String checkRfcStatus() {
		return (String)jraDao.checkRfcStatus();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<FlightData> searchFlights(String airlineId,
			String destFrom, String destTo) throws BapiException {
		Object obj = jraDao.searchFlights(airlineId, destFrom, destTo);
		if (obj instanceof Bapiret2[]) {
			throw new BapiException("BAPI returned with an error", (Bapiret2[]) obj);			
		}
		return (Collection<FlightData>)obj;
	}

	@Override
	public AdditionalInfo getFlightDetails(String airlineId, String connectId,
			String flightDate) throws BapiException {
		
		Object obj = jraDao.getFlightDetails(airlineId, connectId, flightDate);
		if (obj instanceof Bapiret2[]){
			throw new BapiException("BAPI returned with an error", (Bapiret2[]) obj);
		}
		return (AdditionalInfo) obj;
	}

	@Override
	public Collection<AirlineData> getAirlines() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<BookingData> searchBookings(String airlineId,
			String customerId) throws BapiException {
		Object obj = jraDao.searchBookings(airlineId, customerId);
		if (obj instanceof Bapiret2[]){
			throw new BapiException("BAPI returned with an error", (Bapiret2[]) obj);
		}
		return (Collection<BookingData>) obj;
	}

	@Override
	public FlightBooking bookFlight(String airlineId, String connectId,
			String flightDate, String customerId, String counter,
			String passName) throws BapiException {
		Object obj = jraDao.bookFlight(airlineId, connectId, flightDate, customerId, counter, passName);
		if (obj instanceof Bapiret2[]){
			throw new BapiException("BAPI returned with an error", (Bapiret2[]) obj);
		}
		return (FlightBooking) obj;
	}

}
