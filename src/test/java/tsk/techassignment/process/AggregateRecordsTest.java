package tsk.techassignment.process;

import java.util.List;

import org.junit.Test;

import tsk.techassignment.model.jsonobj.Record;
import tsk.techassignment.testcommon.TestUtil;

public class AggregateRecordsTest {
	final private String mockMasRecords = "{\"end_of_month\":\"2018-03\",\"banks_fixed_deposits_3m\":\"0.15\",\"banks_fixed_deposits_6m\":\"0.22\",\"banks_fixed_deposits_12m\":\"0.34\",\"banks_savings_deposits\":\"0.16\",\"fc_fixed_deposits_3m\":null,\"fc_fixed_deposits_6m\":null,\"fc_fixed_deposits_12m\":null,\"fc_savings_deposits\":null}@{\"end_of_month\":\"2018-02\",\"banks_fixed_deposits_3m\":\"0.15\",\"banks_fixed_deposits_6m\":\"0.22\",\"banks_fixed_deposits_12m\":\"0.34\",\"banks_savings_deposits\":\"0.16\",\"fc_fixed_deposits_3m\":\"0.30\",\"fc_fixed_deposits_6m\":\"0.38\",\"fc_fixed_deposits_12m\":\"0.50\",\"fc_savings_deposits\":\"0.17\"}@{\"end_of_month\":\"2018-01\",\"banks_fixed_deposits_3m\":\"0.15\",\"banks_fixed_deposits_6m\":\"0.22\",\"banks_fixed_deposits_12m\":\"0.34\",\"banks_savings_deposits\":\"0.16\",\"fc_fixed_deposits_3m\":\"0.30\",\"fc_fixed_deposits_6m\":\"0.38\",\"fc_fixed_deposits_12m\":\"0.50\",\"fc_savings_deposits\":\"0.17\"}";
	
	@Test
	public void testEnsureOriginalRecordNotAmendedAfterAverage() {
		final List<Record> recordList = TransformMasResponse.convertJsonToObj(mockMasRecords);
		
		AggregateRecords.overallAverage(recordList);

		TestUtil.verifyRecordData("March 2018", new Double(0.15), new Double(0.22), new Double(0.34), new Double(0.16),
				null, null, null, null, recordList.get(0));
		TestUtil.verifyRecordData("February 2018", new Double(0.15), new Double(0.22), new Double(0.34), new Double(0.16),
				new Double(0.30), new Double(0.38), new Double(0.50), new Double(0.17), recordList.get(1));		
		TestUtil.verifyRecordData("January 2018", new Double(0.15), new Double(0.22), new Double(0.34), new Double(0.16),
				new Double(0.30), new Double(0.38), new Double(0.50), new Double(0.17), recordList.get(2));				
	}

	@Test
	public void testAverageValues() {
		final List<Record> recordList = TransformMasResponse.convertJsonToObj(mockMasRecords);
		
		final Record averageRecord = AggregateRecords.overallAverage(recordList);
		
		TestUtil.verifyRecordData(null, new Double(0.15), new Double(0.22), new Double(0.34), new Double(0.16),
				new Double(0.30), new Double(0.38), new Double(0.50), new Double(0.17), averageRecord);						
	}	
	
}
