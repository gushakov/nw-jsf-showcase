package ch.unil.sflight.java.jra;

import java.sql.SQLException;
import java.util.Arrays;

import javax.resource.ResourceException;
import javax.resource.cci.ConnectionFactory;
import javax.resource.cci.MappedRecord;
import javax.resource.cci.Record;
import javax.resource.cci.RecordFactory;

import org.springframework.dao.DataAccessException;

import ch.unil.sflight.java.dto.FlightBooking;

import com.sap.mw.jco.jra.ResultMap;
import com.sap.tc.logging.Location;
import com.sap.tc.logging.Severity;
import com.sap.tc.logging.SimpleLogger;

public class BapiFlBookingCreateFromDataOperation extends
		ErrorCheckingMappingRecordOperation {

	public BapiFlBookingCreateFromDataOperation(
			ConnectionFactory connectionFactory) {
		super(connectionFactory, null);
	}

	private static final Location loc = Location.getLocation(BapiFlBookingCreateFromDataOperation.class);
	
	@SuppressWarnings("unchecked")
	@Override
	protected Record createInputRecord(RecordFactory recordFactory, Object obj)
			throws ResourceException, DataAccessException {
		
		Object[] input = (Object[]) obj;
		SimpleLogger.trace(Severity.DEBUG, loc, "Record inputs: " + Arrays.toString(input));
		MappedRecord bapi = (MappedRecord) recordFactory.createMappedRecord("BAPI_FLBOOKING_CREATEFROMDATA");
		MappedRecord bookData = (MappedRecord) bapi.get("BOOKING_DATA");
		
		bookData.put("AIRLINEID", ((String)input[0]).toUpperCase());
		bookData.put("CONNECTID", (String)input[1]);
		bookData.put("FLIGHTDATE", ((String)input[2]));
		bookData.put("CUSTOMERID", (String)input[3]);
		bookData.put("COUNTER", (String)input[4]);
		bookData.put("PASSNAME", (String)input[5]);
		
		return bapi;
	}

	@Override
	protected Object extractOutputData(Record record) throws ResourceException,
			SQLException, DataAccessException {
		Object obj = null;
		Bapiret2[] errors = checkForBapiErrors((MappedRecord)record, "RETURN");
		if (errors.length == 0) {
			obj = readFlightBooking((ResultMap)record);
		}
		else {
			obj = errors;
		}
		return obj;
	}
	
	private FlightBooking readFlightBooking(ResultMap record) throws SQLException{
		FlightBooking book = new FlightBooking();
		book.setAirlineId(record.getString("AIRLINEID"));
		book.setBookingNumber(record.getString("BOOKINGNUMBER"));
		ResultMap price = (ResultMap) record.get("TICKET_PRICE");
		book.setTiketPrice(price.getBigDecimal("PRICE"));
		book.setCurrency(price.getString("CURR"));
		return book;
	}
	
	@Override
	protected Location getLocation() {
		return loc;
	}	
}
