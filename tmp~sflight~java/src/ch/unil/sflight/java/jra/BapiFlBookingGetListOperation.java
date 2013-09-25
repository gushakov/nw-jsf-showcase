package ch.unil.sflight.java.jra;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.resource.ResourceException;
import javax.resource.cci.ConnectionFactory;
import javax.resource.cci.MappedRecord;
import javax.resource.cci.Record;
import javax.resource.cci.RecordFactory;
import javax.resource.cci.ResultSet;

import org.springframework.dao.DataAccessException;

import com.sap.tc.logging.Location;
import com.sap.tc.logging.Severity;
import com.sap.tc.logging.SimpleLogger;

import ch.unil.sflight.java.dto.BookingData;

public class BapiFlBookingGetListOperation extends
		ErrorCheckingMappingRecordOperation {

	private static final Location loc = Location.getLocation(BapiFlBookingGetListOperation.class);
	
	public BapiFlBookingGetListOperation(ConnectionFactory connectionFactory) {
		super(connectionFactory, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Record createInputRecord(RecordFactory recordFactory, Object obj)
			throws ResourceException, DataAccessException {
		Object[] input = (Object[]) obj;
		SimpleLogger.trace(Severity.DEBUG, loc, "Record inputs: " + Arrays.toString(input));
		MappedRecord record = recordFactory.createMappedRecord("BAPI_FLBOOKING_GETLIST");
		record.put("MAX_ROWS", 100);
		if (input[0]!=null){
			record.put("AIRLINE", ((String)input[0]).toUpperCase());			
		}
		if (input[1]!=null){
			record.put("CUSTOMER_NUMBER", ((String)input[1]).toUpperCase());			
		}
		
		return record;
	}

	@Override
	protected Object extractOutputData(Record record) throws ResourceException,
			SQLException, DataAccessException {
		
		Object obj = null;
		Bapiret2[] errors = checkForBapiErrors(((MappedRecord)record), "RETURN");
		if (errors.length == 0) {
			ArrayList<BookingData> bookings = new ArrayList<BookingData>();
			ResultSet cursor = (ResultSet) ((MappedRecord)record).get("BOOKING_LIST");
			while(cursor.next()){
				bookings.add(readBookingData(cursor));
			}
			obj = bookings;
		}
		else {
			obj = errors;
		}		
		return obj;
	}

	private BookingData readBookingData(ResultSet cursor) throws SQLException{
		BookingData data = new BookingData();
		data.setAirlineId(cursor.getString("AIRLINEID"));
		data.setBookingId(cursor.getString("BOOKINGID"));
		data.setConnectId(cursor.getString("CONNECTID"));
		data.setFlightDate(cursor.getDate("FLIGHTDATE"));
		data.setCustomerId(cursor.getString("CUSTOMERID"));
		data.setBookDate(cursor.getDate("BOOKDATE"));
		data.setReserved(!cursor.getString("RESERVED").matches("\\s?"));
		data.setCancelled(!cursor.getString("CANCELLED").matches("\\s?"));
		data.setPassName(cursor.getString("PASSNAME"));
		return data;
	}

	@Override
	protected Location getLocation() {
		return loc;
	}
	
}
