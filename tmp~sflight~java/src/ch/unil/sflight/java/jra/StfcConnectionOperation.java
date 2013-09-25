package ch.unil.sflight.java.jra;

import java.sql.SQLException;

import javax.resource.ResourceException;
import javax.resource.cci.ConnectionFactory;
import javax.resource.cci.MappedRecord;
import javax.resource.cci.Record;
import javax.resource.cci.RecordFactory;

import org.springframework.dao.DataAccessException;
import org.springframework.jca.cci.object.MappingRecordOperation;

public class StfcConnectionOperation extends MappingRecordOperation {

	public StfcConnectionOperation(ConnectionFactory connectionFactory) {
		super(connectionFactory, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Record createInputRecord(RecordFactory recordFactory,
			Object requestText) throws ResourceException, DataAccessException {

		MappedRecord record = recordFactory
				.createMappedRecord("STFC_CONNECTION");
		record.put("REQUTEXT", "test");
		return record;
	}

	@Override
	protected Object extractOutputData(Record record) throws ResourceException,
			SQLException, DataAccessException {
		return ((MappedRecord) record).get("RESPTEXT");
	}

}
