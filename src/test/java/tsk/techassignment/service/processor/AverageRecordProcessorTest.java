package tsk.techassignment.service.processor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.springframework.ui.Model;

import tsk.techassignment.controller.MASCompareRateController;
import tsk.techassignment.testcommon.TestUtil;

public class AverageRecordProcessorTest {
	final private String mockMasRecords = "{\"end_of_month\":\"2018-03\",\"banks_fixed_deposits_3m\":\"0.15\",\"banks_fixed_deposits_6m\":\"0.22\",\"banks_fixed_deposits_12m\":\"0.34\",\"banks_savings_deposits\":\"0.16\",\"fc_fixed_deposits_3m\":null,\"fc_fixed_deposits_6m\":null,\"fc_fixed_deposits_12m\":null,\"fc_savings_deposits\":null}@{\"end_of_month\":\"2018-02\",\"banks_fixed_deposits_3m\":\"0.15\",\"banks_fixed_deposits_6m\":\"0.22\",\"banks_fixed_deposits_12m\":\"0.34\",\"banks_savings_deposits\":\"0.16\",\"fc_fixed_deposits_3m\":\"0.30\",\"fc_fixed_deposits_6m\":\"0.38\",\"fc_fixed_deposits_12m\":\"0.50\",\"fc_savings_deposits\":\"0.17\"}@{\"end_of_month\":\"2018-01\",\"banks_fixed_deposits_3m\":\"0.15\",\"banks_fixed_deposits_6m\":\"0.22\",\"banks_fixed_deposits_12m\":\"0.34\",\"banks_savings_deposits\":\"0.16\",\"fc_fixed_deposits_3m\":\"0.30\",\"fc_fixed_deposits_6m\":\"0.38\",\"fc_fixed_deposits_12m\":\"0.50\",\"fc_savings_deposits\":\"0.17\"}";
	private Model mockModel = mock(Model.class);
	
	@Test
	public void testEnsureOriginalRecordNotAmendedAfterAverage() {
		final TransformMasResponseRecordProcessor transformResponseRecordProcessor = new TransformMasResponseRecordProcessor(mockMasRecords, mockModel);
		transformResponseRecordProcessor.process();
		
		final AverageRecordProcessor averageRecordProcessor = new AverageRecordProcessor();
		averageRecordProcessor.setRecordList(transformResponseRecordProcessor.getRecordList());
		averageRecordProcessor.setModel(mockModel);
		averageRecordProcessor.process();
				
		TestUtil.verifyRecordData("March 2018", new Double(0.15), new Double(0.22), new Double(0.34), new Double(0.16),
				null, null, null, null, averageRecordProcessor.getRecordList().get(0));
		TestUtil.verifyRecordData("February 2018", new Double(0.15), new Double(0.22), new Double(0.34), new Double(0.16),
				new Double(0.30), new Double(0.38), new Double(0.50), new Double(0.17), averageRecordProcessor.getRecordList().get(1));		
		TestUtil.verifyRecordData("January 2018", new Double(0.15), new Double(0.22), new Double(0.34), new Double(0.16),
				new Double(0.30), new Double(0.38), new Double(0.50), new Double(0.17), averageRecordProcessor.getRecordList().get(2));				
	}
	
	@Test
	public void testAverageValues() {
		final TransformMasResponseRecordProcessor transformResponseRecordProcessor = new TransformMasResponseRecordProcessor(mockMasRecords, mockModel);
		transformResponseRecordProcessor.process();
		
		final AverageRecordProcessor averageRecordProcessor = new AverageRecordProcessor();
		averageRecordProcessor.setRecordList(transformResponseRecordProcessor.getRecordList());
		averageRecordProcessor.setModel(mockModel);
		averageRecordProcessor.process();
		
		TestUtil.verifyRecordData(null, new Double(0.15), new Double(0.22), new Double(0.34), new Double(0.16),
				new Double(0.30), new Double(0.38), new Double(0.50), new Double(0.17), averageRecordProcessor.getAverageRecord());						
	}	
	
	@Test
	public void testAverageRecordInsertIntoModel() {
		final TransformMasResponseRecordProcessor transformResponseRecordProcessor = new TransformMasResponseRecordProcessor(mockMasRecords, mockModel);
		transformResponseRecordProcessor.process();
		
		final AverageRecordProcessor averageRecordProcessor = new AverageRecordProcessor();
		averageRecordProcessor.setRecordList(transformResponseRecordProcessor.getRecordList());
		averageRecordProcessor.setModel(mockModel);
		averageRecordProcessor.process();
		
		verify(mockModel).addAttribute(MASCompareRateController.SUMMARYAVERAGEMODEL, averageRecordProcessor.getAverageRecord());						
	}	
	
	@Test
	public void testNoAverageRecord() {
		final TransformMasResponseRecordProcessor transformResponseRecordProcessor = new TransformMasResponseRecordProcessor(" ", mockModel);
		transformResponseRecordProcessor.process();
		
		final AverageRecordProcessor averageRecordProcessor = new AverageRecordProcessor();
		averageRecordProcessor.setRecordList(transformResponseRecordProcessor.getRecordList());
		averageRecordProcessor.setModel(mockModel);
		averageRecordProcessor.process();
		
		assertTrue(averageRecordProcessor.getAverageRecord() == null);
		verify(mockModel, never()).addAttribute(MASCompareRateController.SUMMARYAVERAGEMODEL, averageRecordProcessor.getAverageRecord());
	}	
}
