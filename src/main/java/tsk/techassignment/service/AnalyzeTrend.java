package tsk.techassignment.process;

import java.util.List;

import tsk.techassignment.model.TrendOverview;
import tsk.techassignment.model.TrendValue;
import tsk.techassignment.model.jsonobj.Record;

public class AnalyzeTrend {
	public static TrendOverview analyzeTrendFirstAndLastMonth(List<Record> recordList) {
		if (recordList == null || recordList.size() <= 1) return null;
		
		final TrendOverview trendOverview = new TrendOverview(); 
		
		trendOverview.setTrendBankFixDeposit3Months(goUpOrDownBaseOnEarliestAndLatest(recordList.get(recordList.size()-1).getBanks_fixed_deposits_3m(), 
				recordList.get(0).getBanks_fixed_deposits_3m()));
		trendOverview.setTrendBankFixDeposit6Months(goUpOrDownBaseOnEarliestAndLatest(recordList.get(recordList.size()-1).getBanks_fixed_deposits_6m(), 
				recordList.get(0).getBanks_fixed_deposits_6m()));
		trendOverview.setTrendBankFixDeposit12Months(goUpOrDownBaseOnEarliestAndLatest(recordList.get(recordList.size()-1).getBanks_fixed_deposits_12m(), 
				recordList.get(0).getBanks_fixed_deposits_12m()));
		trendOverview.setTrendBankSavingDeposit(goUpOrDownBaseOnEarliestAndLatest(recordList.get(recordList.size()-1).getBanks_savings_deposits(), 
				recordList.get(0).getBanks_savings_deposits()));
		
		trendOverview.setTrendFCFixDeposit3Months(goUpOrDownBaseOnEarliestAndLatest(recordList.get(recordList.size()-1).getFc_fixed_deposits_3m(), 
				recordList.get(0).getFc_fixed_deposits_3m()));
		trendOverview.setTrendFCFixDeposit6Months(goUpOrDownBaseOnEarliestAndLatest(recordList.get(recordList.size()-1).getFc_fixed_deposits_6m(), 
				recordList.get(0).getFc_fixed_deposits_6m()));
		trendOverview.setTrendFCFixDeposit12Months(goUpOrDownBaseOnEarliestAndLatest(recordList.get(recordList.size()-1).getFc_fixed_deposits_12m(), 
				recordList.get(0).getFc_fixed_deposits_12m()));
		trendOverview.setTrendFCSavingDeposit(goUpOrDownBaseOnEarliestAndLatest(recordList.get(recordList.size()-1).getFc_savings_deposits(), 
				recordList.get(0).getFc_savings_deposits()));
		
		return trendOverview;
	}	
	
	private static TrendValue goUpOrDownBaseOnEarliestAndLatest(final Double earliestRate, final Double latestRate) {
		if (earliestRate == null || latestRate == null) return TrendValue.NA;		
		
		int i = earliestRate.compareTo(latestRate);
		
		if (i == 0) return TrendValue.NoChange;
		else if (i > 0) return TrendValue.Down;
		else return TrendValue.Up;
	}	
}
