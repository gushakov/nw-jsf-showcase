package ch.unil.sflight.java.jra;

import javax.resource.cci.ConnectionFactory;

import org.springframework.jca.cci.core.CciTemplate;
import org.springframework.jca.cci.core.support.CciDaoSupport;

import ch.unil.sflight.java.dto.AdditionalInfo;
import ch.unil.sflight.java.dto.BookingData;
import ch.unil.sflight.java.dto.FlightData;

import com.sap.tc.logging.Location;

public class JraDao extends CciDaoSupport {
	@SuppressWarnings("unused")
	private static final Location loc = Location.getLocation(JraDao.class);

	private StfcConnectionOperation stfcConnectionOp;
	private BapiFlightGetListOperation flightGetListOp;
	private BapiFlightGetDetailOperation flightGetDetailOp;
	private BapiFlBookingGetListOperation bookingGetListOp;
	private BapiFlBookingCreateFromDataOperation bookingCreateFromDataOp;
	
	@Override
	protected CciTemplate createCciTemplate(ConnectionFactory connectionFactory) {
		CciTemplate cciTemplate = super.createCciTemplate(connectionFactory);
		stfcConnectionOp = new StfcConnectionOperation(connectionFactory);
		flightGetListOp = new BapiFlightGetListOperation(connectionFactory);
		flightGetDetailOp = new BapiFlightGetDetailOperation(connectionFactory);
		bookingGetListOp = new BapiFlBookingGetListOperation(connectionFactory);
		bookingCreateFromDataOp = new BapiFlBookingCreateFromDataOperation(connectionFactory);		
		return cciTemplate;
	}
		
	/**
	 * Executes <code>STFC_CONNECTION</code> BAPI and returns the response text.
	 * 
	 * @return Response string or <code>Bapiret2[]</code> if BAPI returned with errors
	 * 
	 * @throws IllegalStateException if corresponding <code>MappingRecordOperation</code>
	 * 				is null
	 * @see StfcConnectionOperation
	 * 
	 */
	public Object checkRfcStatus() {
		if (stfcConnectionOp==null){
			throw new IllegalStateException("MappingRecordOperation " + StfcConnectionOperation.class.getCanonicalName() + " is null");
		}
		return stfcConnectionOp.execute("test");
	}
	
	/**
	 * Searches flights using <code>BAPI_FLIGHT_GETLIST</code> and returns a
	 * collection of <code>FlightData</code> objects corresponding to the search
	 * criteria.
	 * 
	 * @param airlineId
	 *            two letter code for the airline (for example, AA, UA)
	 * @return list of objects of type {@linkplain FlightData} or
	 * 				<code>Bapiret2[]</code> if BAPI returned with errors
	 * @throws IllegalStateException if corresponding <code>MappingRecordOperation</code>
	 * 				is null
	 * @see BapiFlightGetListOperation
	 */
	public Object searchFlights(String airlineId,
			String destFrom, String destTo) {
		if (flightGetListOp==null){
			throw new IllegalStateException("MappingRecordOperation " + BapiFlightGetListOperation.class.getCanonicalName() + " is null");
		}
		return flightGetListOp.execute( new Object[]{airlineId, destFrom, destTo});
	}
	
	/**
	 * Executes <code>BAPI_FLIGHT_GETDETAIL</code> and returns the details of the flight.
	 * @param airlineId airline id
	 * @param connectId connection id
	 * @param flightDate flight date
	 * @return flight details of type {@linkplain AdditionalInfo} or <code>Bapiret2[]</code>
	 * 			if BAPI returned with errors
	 * @throws IllegalStateException if corresponding <code>MappingRecordOperation</code>
	 * 				is null
	 */
	public Object getFlightDetails(String airlineId, String connectId,
			String flightDate) {
		if (flightGetDetailOp==null){
			throw new IllegalStateException("MappingRecordOperation " + BapiFlightGetDetailOperation.class.getCanonicalName() + " is null");
		}
		return flightGetDetailOp.execute(new Object[]{airlineId, connectId, flightDate});
	}
	
	/**
	 * Executes <code>BAPI_FLBOOKING_GETLIST</code> returning a list of <code>BookingData</code>
	 * objects corresponding to the list of flight bookings.
	 * @param airlineId id of the airline, must be specified
	 * @param customerId customer number is empty up to 100 bookings for the given airline are returned
	 * @return list of bookings of type {@linkplain BookingData} or an array of <code>Bapiret2[]</code>
	 * 			if the BAPI returned with errors
	 * @throws IllegalStateException if corresponding <code>MappingRecordOperation</code>
	 * 				is null
	 */
	public Object searchBookings(String airlineId, String customerId){
		if (bookingGetListOp==null){
			throw new IllegalStateException("MappingRecordOperation " + BapiFlBookingGetListOperation.class.getCanonicalName() + " is null");			
		}
		return bookingGetListOp.execute(new Object[]{airlineId, customerId});
	}
	
	
	public Object bookFlight(String airlineId, String connectId,
			String flightDate, String customerId, String counter,
			String passName){
		if (bookingCreateFromDataOp==null){
			throw new IllegalStateException("MappingRecordOperation " + BapiFlBookingCreateFromDataOperation.class.getCanonicalName() + " is null");			
		}
		return bookingCreateFromDataOp.execute(new Object[]{airlineId, connectId, flightDate, customerId, counter, passName});
	}
	
}
