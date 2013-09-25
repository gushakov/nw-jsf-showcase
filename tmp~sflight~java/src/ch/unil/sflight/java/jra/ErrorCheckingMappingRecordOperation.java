package ch.unil.sflight.java.jra;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.resource.cci.ConnectionFactory;
import javax.resource.cci.InteractionSpec;
import javax.resource.cci.MappedRecord;
import javax.resource.cci.ResultSet;

import org.springframework.dao.DataAccessException;
import org.springframework.jca.cci.object.MappingRecordOperation;

import com.sap.tc.logging.Location;
import com.sap.tc.logging.Severity;
import com.sap.tc.logging.SimpleLogger;

public abstract class ErrorCheckingMappingRecordOperation extends MappingRecordOperation {
	
	protected abstract Location getLocation();
	
	public ErrorCheckingMappingRecordOperation(ConnectionFactory connectionFactory, InteractionSpec interactionSpec){
		super(connectionFactory, interactionSpec);
	}
	
	protected Bapiret2[] checkForBapiErrors(MappedRecord record,
			String bapiretTableName) throws SQLException, DataAccessException {
		Location loc = getLocation();
		ResultSet cursor = (ResultSet) record.get(bapiretTableName);
		ArrayList<Bapiret2> errors = new ArrayList<Bapiret2>();
		while (cursor.next()) {
			String type = cursor.getString("TYPE");
			String message = cursor.getString("MESSAGE");
			if (type.equalsIgnoreCase("E") || type.equalsIgnoreCase("A")) {
				Bapiret2 bapiret2 = new Bapiret2();
				bapiret2.setMessage(message);
				errors.add(bapiret2);
				SimpleLogger.trace(Severity.ERROR, loc, message);
			}
			else if (type.equalsIgnoreCase("S") || type.equalsIgnoreCase("S")){
				SimpleLogger.trace(Severity.INFO, loc, message);
			}
			else if (type.equalsIgnoreCase("W")){
				SimpleLogger.trace(Severity.WARNING, loc, message);
			}
		}
		return errors.toArray(new Bapiret2[errors.size()]);
	}	

}
