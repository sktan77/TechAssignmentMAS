package tsk.techassignment.process;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tsk.techassignment.model.TrendOverview;
import tsk.techassignment.model.TrendValue;
import tsk.techassignment.model.jsonobj.Record;

public class AnalyzeTrendTest {	
	@Test
	public void testAnalyzeTrendFirstAndLastMonthOneRecordNull() {
		final List<Record> oneRecordNoTrend = new ArrayList<Record>();
		oneRecordNoTrend.add(new Record(null, new Double(2.0), new Double(3.0), new Double(4.0), new Double(5.0),
				new Double(1.0), new Double(1.1), new Double(1.2), new Double(1.3)));
		
		final TrendOverview trendOverview = AnalyzeTrend.analyzeTrendFirstAndLastMonth(oneRecordNoTrend);
		
		assertTrue(trendOverview == null);
	}	
	
	@Test
	public void testAnalyzeTrendFirstAndLastMonthRecordWithNull() {
		final List<Record> twoRecordTrendWithNull = new ArrayList<Record>();
		twoRecordTrendWithNull.add(new Record(null, new Double(2.0), null, new Double(4.0), null,
				new Double(1.0), new Double(1.1), new Double(1.2), new Double(1.3)));
		twoRecordTrendWithNull.add(new Record(null, new Double(2.0), new Double(3.0), new Double(4.0), new Double(5.0),
				null, new Double(1.1), new Double(1.2), null));
		
		final TrendOverview trendOverview = AnalyzeTrend.analyzeTrendFirstAndLastMonth(twoRecordTrendWithNull);
		
		assertTrue(trendOverview.getTrendBankFixDeposit3Months() == TrendValue.NoChange);
		assertTrue(trendOverview.getTrendBankFixDeposit6Months() == TrendValue.NA);
		assertTrue(trendOverview.getTrendBankFixDeposit12Months() == TrendValue.NoChange);
		assertTrue(trendOverview.getTrendBankSavingDeposit() == TrendValue.NA);
		assertTrue(trendOverview.getTrendFCFixDeposit3Months() == TrendValue.NA);
		assertTrue(trendOverview.getTrendFCFixDeposit6Months() == TrendValue.NoChange);
		assertTrue(trendOverview.getTrendFCFixDeposit12Months() == TrendValue.NoChange);
		assertTrue(trendOverview.getTrendFCSavingDeposit() == TrendValue.NA);
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
		
		final TrendOverview trendOverview = AnalyzeTrend.analyzeTrendFirstAndLastMonth(threeRecordTrendWithNull);
		
		assertTrue(trendOverview.getTrendBankFixDeposit3Months() == TrendValue.Up);
		assertTrue(trendOverview.getTrendBankFixDeposit6Months() == TrendValue.Down);
		assertTrue(trendOverview.getTrendBankFixDeposit12Months() == TrendValue.Up);
		assertTrue(trendOverview.getTrendBankSavingDeposit() == TrendValue.Down);
		assertTrue(trendOverview.getTrendFCFixDeposit3Months() == TrendValue.Down);
		assertTrue(trendOverview.getTrendFCFixDeposit6Months() == TrendValue.Up);
		assertTrue(trendOverview.getTrendFCFixDeposit12Months() == TrendValue.Down);
		assertTrue(trendOverview.getTrendFCSavingDeposit() == TrendValue.Down);
	}	
}
