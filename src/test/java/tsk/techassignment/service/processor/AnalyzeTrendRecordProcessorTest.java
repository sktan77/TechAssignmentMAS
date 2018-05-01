package tsk.techassignment.service.processor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.ui.Model;

import tsk.techassignment.controller.MASCompareRateController;
import tsk.techassignment.model.TrendValue;
import tsk.techassignment.model.jsonobj.Record;

public class AnalyzeTrendRecordProcessorTest {
	private Model mockModel = mock(Model.class);

	@Test
	public void testAnalyzeTrendFirstAndLastMonthOneRecordNull() {
		final List<Record> oneRecordNoTrend = new ArrayList<Record>();
		oneRecordNoTrend.add(new Record(null, new Double(2.0), new Double(3.0), new Double(4.0), new Double(5.0),
				new Double(1.0), new Double(1.1), new Double(1.2), new Double(1.3)));
		final AnalyzeTrendRecordProcessor analyzeTrendRecordProcessor = new AnalyzeTrendRecordProcessor();
		analyzeTrendRecordProcessor.setRecordList(oneRecordNoTrend);
		analyzeTrendRecordProcessor.setModel(mockModel);
		analyzeTrendRecordProcessor.process();

		assertTrue(analyzeTrendRecordProcessor.getTrendOverview() == null);
		verify(mockModel, never()).addAttribute(MASCompareRateController.SUMMARYTRENDMODEL,
				analyzeTrendRecordProcessor.getTrendOverview());
		verify(mockModel, never()).addAttribute(MASCompareRateController.EARLIESTRECORD, analyzeTrendRecordProcessor
				.getRecordList().get(analyzeTrendRecordProcessor.getRecordList().size() - 1));
		verify(mockModel, never()).addAttribute(MASCompareRateController.LATESTRECORD,
				analyzeTrendRecordProcessor.getRecordList().get(0));
	}
	
	@Test
	public void testAnalyzeTrendFirstAndLastMonthRecordWithNull() {
		final List<Record> twoRecordTrendWithNull = new ArrayList<Record>();
		twoRecordTrendWithNull.add(new Record(null, new Double(2.0), null, new Double(4.0), null,
				new Double(1.0), new Double(1.1), new Double(1.2), new Double(1.3)));
		twoRecordTrendWithNull.add(new Record(null, new Double(2.0), new Double(3.0), new Double(4.0), new Double(5.0),
				null, new Double(1.1), new Double(1.2), null));
		
		final AnalyzeTrendRecordProcessor analyzeTrendRecordProcessor = new AnalyzeTrendRecordProcessor();
		analyzeTrendRecordProcessor.setRecordList(twoRecordTrendWithNull);
		analyzeTrendRecordProcessor.setModel(mockModel);
		analyzeTrendRecordProcessor.process();
		
		assertTrue(analyzeTrendRecordProcessor.getTrendOverview().getTrendBankFixDeposit3Months() == TrendValue.NoChange);
		assertTrue(analyzeTrendRecordProcessor.getTrendOverview().getTrendBankFixDeposit6Months() == TrendValue.NA);
		assertTrue(analyzeTrendRecordProcessor.getTrendOverview().getTrendBankFixDeposit12Months() == TrendValue.NoChange);
		assertTrue(analyzeTrendRecordProcessor.getTrendOverview().getTrendBankSavingDeposit() == TrendValue.NA);
		assertTrue(analyzeTrendRecordProcessor.getTrendOverview().getTrendFCFixDeposit3Months() == TrendValue.NA);
		assertTrue(analyzeTrendRecordProcessor.getTrendOverview().getTrendFCFixDeposit6Months() == TrendValue.NoChange);
		assertTrue(analyzeTrendRecordProcessor.getTrendOverview().getTrendFCFixDeposit12Months() == TrendValue.NoChange);
		assertTrue(analyzeTrendRecordProcessor.getTrendOverview().getTrendFCSavingDeposit() == TrendValue.NA);
		
		verify(mockModel).addAttribute(MASCompareRateController.SUMMARYTRENDMODEL,
				analyzeTrendRecordProcessor.getTrendOverview());
		verify(mockModel).addAttribute(MASCompareRateController.EARLIESTRECORD, analyzeTrendRecordProcessor
				.getRecordList().get(analyzeTrendRecordProcessor.getRecordList().size() - 1));
		verify(mockModel).addAttribute(MASCompareRateController.LATESTRECORD,
				analyzeTrendRecordProcessor.getRecordList().get(0));
	}	
	
	@Test
	public void testAnalyzeTrendFirstAndLastMonthRecordWithNullModelIsSet() {
		final List<Record> twoRecordTrendWithNull = new ArrayList<Record>();
		twoRecordTrendWithNull.add(new Record(null, new Double(2.0), null, new Double(4.0), null,
				new Double(1.0), new Double(1.1), new Double(1.2), new Double(1.3)));
		twoRecordTrendWithNull.add(new Record(null, new Double(2.0), new Double(3.0), new Double(4.0), new Double(5.0),
				null, new Double(1.1), new Double(1.2), null));
		
		final AnalyzeTrendRecordProcessor analyzeTrendRecordProcessor = new AnalyzeTrendRecordProcessor();
		analyzeTrendRecordProcessor.setRecordList(twoRecordTrendWithNull);
		analyzeTrendRecordProcessor.setModel(mockModel);
		analyzeTrendRecordProcessor.process();
				
		verify(mockModel).addAttribute(MASCompareRateController.SUMMARYTRENDMODEL,
				analyzeTrendRecordProcessor.getTrendOverview());
		verify(mockModel).addAttribute(MASCompareRateController.EARLIESTRECORD, analyzeTrendRecordProcessor
				.getRecordList().get(analyzeTrendRecordProcessor.getRecordList().size() - 1));
		verify(mockModel).addAttribute(MASCompareRateController.LATESTRECORD,
				analyzeTrendRecordProcessor.getRecordList().get(0));
	}	
	
	@Test
	public void testAnalyzeTrendFirstAndLastMonthThreeRecord() {
		final List<Record> threeRecordTrendWithNull = new ArrayList<Record>();
		threeRecordTrendWithNull.add(new Record(null, new Double(2.0), new Double(1.0), new Double(4.0), new Double(4.4),
				new Double(1.0), new Double(1.1), new Double(1.2), new Double(1.3)));
		threeRecordTrendWithNull.add(new Record(null, new Double(2.0), new Double(3.0), new Double(4.0), new Double(5.0),
				null, new Double(1.1), new Double(1.2), null));
		threeRecordTrendWithNull.add(new Record(null, new Double(1.5), new Double(4.1), new Double(3.1), new Double(7.4),
				new Double(1.1), new Double(0.15), new Double(1.21), new Double(1.8)));
		
		final AnalyzeTrendRecordProcessor analyzeTrendRecordProcessor = new AnalyzeTrendRecordProcessor();
		analyzeTrendRecordProcessor.setRecordList(threeRecordTrendWithNull);
		analyzeTrendRecordProcessor.setModel(mockModel);
		analyzeTrendRecordProcessor.process();
		
		assertTrue(analyzeTrendRecordProcessor.getTrendOverview().getTrendBankFixDeposit3Months() == TrendValue.Up);
		assertTrue(analyzeTrendRecordProcessor.getTrendOverview().getTrendBankFixDeposit6Months() == TrendValue.Down);
		assertTrue(analyzeTrendRecordProcessor.getTrendOverview().getTrendBankFixDeposit12Months() == TrendValue.Up);
		assertTrue(analyzeTrendRecordProcessor.getTrendOverview().getTrendBankSavingDeposit() == TrendValue.Down);
		assertTrue(analyzeTrendRecordProcessor.getTrendOverview().getTrendFCFixDeposit3Months() == TrendValue.Down);
		assertTrue(analyzeTrendRecordProcessor.getTrendOverview().getTrendFCFixDeposit6Months() == TrendValue.Up);
		assertTrue(analyzeTrendRecordProcessor.getTrendOverview().getTrendFCFixDeposit12Months() == TrendValue.Down);
		assertTrue(analyzeTrendRecordProcessor.getTrendOverview().getTrendFCSavingDeposit() == TrendValue.Down);
	}		

	@Test
	public void testAnalyzeTrendFirstAndLastMonthThreeRecordModelIsSet() {
		final List<Record> threeRecordTrendWithNull = new ArrayList<Record>();
		threeRecordTrendWithNull.add(new Record(null, new Double(2.0), new Double(1.0), new Double(4.0), new Double(4.4),
				new Double(1.0), new Double(1.1), new Double(1.2), new Double(1.3)));
		threeRecordTrendWithNull.add(new Record(null, new Double(2.0), new Double(3.0), new Double(4.0), new Double(5.0),
				null, new Double(1.1), new Double(1.2), null));
		threeRecordTrendWithNull.add(new Record(null, new Double(1.5), new Double(4.1), new Double(3.1), new Double(7.4),
				new Double(1.1), new Double(0.15), new Double(1.21), new Double(1.8)));
		
		final AnalyzeTrendRecordProcessor analyzeTrendRecordProcessor = new AnalyzeTrendRecordProcessor();
		analyzeTrendRecordProcessor.setRecordList(threeRecordTrendWithNull);
		analyzeTrendRecordProcessor.setModel(mockModel);
		analyzeTrendRecordProcessor.process();
		
		verify(mockModel).addAttribute(MASCompareRateController.SUMMARYTRENDMODEL,
				analyzeTrendRecordProcessor.getTrendOverview());
		verify(mockModel).addAttribute(MASCompareRateController.EARLIESTRECORD, analyzeTrendRecordProcessor
				.getRecordList().get(analyzeTrendRecordProcessor.getRecordList().size() - 1));
		verify(mockModel).addAttribute(MASCompareRateController.LATESTRECORD,
				analyzeTrendRecordProcessor.getRecordList().get(0));
	}		
}
