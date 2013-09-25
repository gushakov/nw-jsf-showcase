package ch.unil.sflight.java.jra;

import java.sql.SQLException;
import java.util.Arrays;

import javax.resource.ResourceException;
import javax.resource.cci.ConnectionFactory;
import javax.resource.cci.MappedRecord;
import javax.resource.cci.Record;
import javax.resource.cci.RecordFactory;

import org.springframework.dao.DataAccessException;

import ch.unil.sflight.java.dto.AdditionalInfo;

import com.sap.mw.jco.jra.ResultMap;
import com.sap.tc.logging.Location;
import com.sap.tc.logging.Severity;
import com.sap.tc.logging.SimpleLogger;

public class BapiFlightGetDetailOperation extends
		ErrorCheckingMappingRecordOperation {
	private static final Location loc = Location
			.getLocation(BapiFlightGetDetailOperation.class);

	public BapiFlightGetDetailOperation(ConnectionFactory connectionFactory) {
		super(connectionFactory, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Record createInputRecord(RecordFactory recordFactory, Object obj)
			throws ResourceException, DataAccessException {
		Object[] input = (Object[]) obj;
		SimpleLogger.trace(Severity.DEBUG, loc, "Record inputs: " + Arrays.toString(input));
		MappedRecord record = recordFactory
				.createMappedRecord("BAPI_FLIGHT_GETDETAIL");
		record.put("AIRLINEID", ((String)input[0]).toUpperCase());
		record.put("CONNECTIONID", ((String)input[1]).toUpperCase());
		record.put("FLIGHTDATE", (String)input[2]);
		return record;
	}

	@Override
	protected Object extractOutputData(Record record) throws ResourceException,
			SQLException, DataAccessException {
		Object obj = null;
		Bapiret2[] errors = checkForBapiErrors(((MappedRecord)record), "RETURN");
		if (errors.length == 0) {
			obj = readAdditionalInfo((ResultMap)((MappedRecord)record).get("ADDITIONAL_INFO"));
		}
		else {
			obj = errors;
		}
		return obj;

	}
	
	private AdditionalInfo readAdditionalInfo(ResultMap resultMap) throws SQLException {
		AdditionalInfo info = new AdditionalInfo();
		info.setFlightTime(resultMap.getInt("FLIGHTTIME"));
		info.setDistance(resultMap.getDouble("DISTANCE"));
		info.setUnit(resultMap.getString("UNIT"));
		info.setPlaneType(resultMap.getString("PLANETYPE"));
		return info;
	}

	@Override
	protected Location getLocation() {
		return loc;
	}
}
