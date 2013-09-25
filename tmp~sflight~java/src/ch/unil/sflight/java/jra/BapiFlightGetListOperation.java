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

import ch.unil.sflight.java.dto.FlightData;

public class BapiFlightGetListOperation extends
		ErrorCheckingMappingRecordOperation {
	private static final Location loc = Location.getLocation(BapiFlightGetListOperation.class);

	public BapiFlightGetListOperation(ConnectionFactory connectionFactory) {
		super(connectionFactory, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Record createInputRecord(RecordFactory recordFactory, Object obj)
			throws ResourceException, DataAccessException {
		Object[] input = (Object[]) obj;
		SimpleLogger.trace(Severity.DEBUG, loc, "Record inputs: "
				+ Arrays.toString(input));
		MappedRecord record = recordFactory
				.createMappedRecord("BAPI_FLIGHT_GETLIST");
		// TODO: set other input parameters
		if (input[0] != null) {
			record.put("AIRLINE", ((String) input[0]).toUpperCase());
		}
		return record;
	}

	@Override
	protected Object extractOutputData(Record record) throws ResourceException,
			SQLException, DataAccessException {
		Object obj = null;
		Bapiret2[] errors = checkForBapiErrors(((MappedRecord) record),
				"RETURN");
		if (errors.length == 0) {
			ArrayList<FlightData> flights = new ArrayList<FlightData>();
			ResultSet cursor = (ResultSet) ((MappedRecord) record)
					.get("FLIGHT_LIST");
			while (cursor.next()) {
				flights.add(readFlightData(cursor));
			}
			obj = flights;
		} else {
			obj = errors;
		}
		return obj;
	}

	private FlightData readFlightData(ResultSet cursor) throws SQLException {
		FlightData data = new FlightData();
		data.setAirlineId(cursor.getString("AIRLINEID"));
		data.setAirlineName(cursor.getString("AIRLINE"));
		data.setConnectId(cursor.getString("CONNECTID"));
		data.setFlightDate(cursor.getDate("FLIGHTDATE"));
		data.setAirportFrom(cursor.getString("AIRPORTFR"));
		data.setCityFrom(cursor.getString("CITYFROM"));
		data.setAirportTo(cursor.getString("AIRPORTTO"));
		data.setCityTo(cursor.getString("CITYTO"));
		data.setDepartureTime(cursor.getTime("DEPTIME"));
		data.setArrivalTime(cursor.getTime("ARRTIME"));
		data.setArrivalDate(cursor.getDate("ARRDATE"));
		data.setPrice(cursor.getBigDecimal("PRICE"));
		data.setCurrency(cursor.getString("CURR"));
		return data;
	}

	@Override
	protected Location getLocation() {
		return loc;
	}
}
