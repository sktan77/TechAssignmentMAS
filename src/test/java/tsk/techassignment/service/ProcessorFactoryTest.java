package tsk.techassignment.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.springframework.ui.Model;

import tsk.techassignment.controller.MASCompareRateController;
import tsk.techassignment.service.processor.AnalyzeTrendRecordProcessor;
import tsk.techassignment.service.processor.AverageRecordProcessor;
import tsk.techassignment.service.processor.RecordProcessor;

public class ProcessorFactoryTest {
	final private String mockMasRecords = "{\"end_of_month\":\"2018-03\",\"banks_fixed_deposits_3m\":\"0.15\",\"banks_fixed_deposits_6m\":\"0.22\",\"banks_fixed_deposits_12m\":\"0.34\",\"banks_savings_deposits\":\"0.16\",\"fc_fixed_deposits_3m\":null,\"fc_fixed_deposits_6m\":null,\"fc_fixed_deposits_12m\":null,\"fc_savings_deposits\":null}@{\"end_of_month\":\"2018-02\",\"banks_fixed_deposits_3m\":\"0.15\",\"banks_fixed_deposits_6m\":\"0.22\",\"banks_fixed_deposits_12m\":\"0.34\",\"banks_savings_deposits\":\"0.16\",\"fc_fixed_deposits_3m\":\"0.30\",\"fc_fixed_deposits_6m\":\"0.38\",\"fc_fixed_deposits_12m\":\"0.50\",\"fc_savings_deposits\":\"0.17\"}@{\"end_of_month\":\"2018-01\",\"banks_fixed_deposits_3m\":\"0.15\",\"banks_fixed_deposits_6m\":\"0.22\",\"banks_fixed_deposits_12m\":\"0.34\",\"banks_savings_deposits\":\"0.16\",\"fc_fixed_deposits_3m\":\"0.30\",\"fc_fixed_deposits_6m\":\"0.38\",\"fc_fixed_deposits_12m\":\"0.50\",\"fc_savings_deposits\":\"0.17\"}";
	private Model mockModel = mock(Model.class);

	@Test
	public void testCreateProcessorSuccessorCreateCorrectly() {
		RecordProcessor recordProcessor = ProcessorFactory.createProcessor(mockMasRecords, mockModel);

		assertTrue(recordProcessor.getSuccessor() instanceof AverageRecordProcessor);
		assertTrue(recordProcessor.getSuccessor().getSuccessor() instanceof AnalyzeTrendRecordProcessor);
		assertTrue(recordProcessor.getSuccessor().getSuccessor().getSuccessor() == null);
	}

	@Test
	public void testCreateProcessorProcessTriggerCorrectly() {
		RecordProcessor recordProcessor = ProcessorFactory.createProcessor(mockMasRecords, mockModel);
		recordProcessor.process();

		verify(mockModel).addAttribute(MASCompareRateController.DETAILMODEL, recordProcessor.getRecordList());
		verify(mockModel).addAttribute(MASCompareRateController.SUMMARYAVERAGEMODEL,
				((AverageRecordProcessor) recordProcessor.getSuccessor()).getAverageRecord());
		
		final AnalyzeTrendRecordProcessor testTrendRecordProcessor = (AnalyzeTrendRecordProcessor) recordProcessor.getSuccessor().getSuccessor(); 		
		verify(mockModel).addAttribute(MASCompareRateController.SUMMARYTRENDMODEL, testTrendRecordProcessor.getTrendOverview());
		verify(mockModel).addAttribute(MASCompareRateController.EARLIESTRECORD, testTrendRecordProcessor.getRecordList().get(testTrendRecordProcessor.getRecordList().size() - 1));
		verify(mockModel).addAttribute(MASCompareRateController.LATESTRECORD, testTrendRecordProcessor.getRecordList().get(0));
	}
}
