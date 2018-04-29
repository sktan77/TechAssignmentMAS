package tsk.techassignment.process;

import java.util.List;

import tsk.techassignment.model.jsonobj.Record;

public class AggregateRecords {
	public static Record overallAverage(final List<Record> recordList) {
		if (recordList == null || recordList.size() == 0) {
			return null;			
		}
		
		int countBankFixD3M=0, countBankFixD6M=0, countBankFixD12M=0, countBankSaveD=0, 
				countFCFixD3M=0, countFCFixD6M=0, countFCFixD12M=0, countFCSaveD=0;		
		final Record averageRecord = new Record(null, new Double(0), new Double(0), new Double(0), new Double(0), new Double(0), new Double(0), new Double(0), new Double(0));
		
		for (Record record : recordList) {			
			sumEachRecord(averageRecord, record);
			
			if (record.getBanks_fixed_deposits_3m() != null) countBankFixD3M++;
			if (record.getBanks_fixed_deposits_6m() != null) countBankFixD6M++;
			if (record.getBanks_fixed_deposits_12m() != null) countBankFixD12M++;
			if (record.getBanks_savings_deposits() != null) countBankSaveD++;
			
			if (record.getFc_fixed_deposits_3m() != null) countFCFixD3M++;
			if (record.getFc_fixed_deposits_6m() != null) countFCFixD6M++;
			if (record.getFc_fixed_deposits_12m() != null) countFCFixD12M++;
			if (record.getFc_savings_deposits() != null) countFCSaveD++;						
		}
		
		performAverage(averageRecord, countBankFixD3M, countBankFixD6M, countBankFixD12M, countBankSaveD, countFCFixD3M, countFCFixD6M, countFCFixD12M, countFCSaveD);
		
		return averageRecord;
	}
	
	private static void sumEachRecord(final Record averageRecord, final Record eachRecord) {
		averageRecord.setBanks_fixed_deposits_3m(addDouble(averageRecord.getBanks_fixed_deposits_3m(), eachRecord.getBanks_fixed_deposits_3m()));
		averageRecord.setBanks_fixed_deposits_6m(addDouble(averageRecord.getBanks_fixed_deposits_6m(), eachRecord.getBanks_fixed_deposits_6m()));
		averageRecord.setBanks_fixed_deposits_12m(addDouble(averageRecord.getBanks_fixed_deposits_12m(), eachRecord.getBanks_fixed_deposits_12m()));
		averageRecord.setBanks_savings_deposits(addDouble(averageRecord.getBanks_savings_deposits(), eachRecord.getBanks_savings_deposits()));
		
		averageRecord.setFc_fixed_deposits_3m(addDouble(averageRecord.getFc_fixed_deposits_3m(), eachRecord.getFc_fixed_deposits_3m()));
		averageRecord.setFc_fixed_deposits_6m(addDouble(averageRecord.getFc_fixed_deposits_6m(), eachRecord.getFc_fixed_deposits_6m()));
		averageRecord.setFc_fixed_deposits_12m(addDouble(averageRecord.getFc_fixed_deposits_12m(), eachRecord.getFc_fixed_deposits_12m()));
		averageRecord.setFc_savings_deposits(addDouble(averageRecord.getFc_savings_deposits(), eachRecord.getFc_savings_deposits()));
	}
	
	private static Double addDouble(Double totalValue, final Double recordValue) {
		if (recordValue != null) {
			totalValue += recordValue;			
		}
		
		return totalValue;
	}
	
	private static void performAverage(final Record averageRecord, final int totalBankFix3M, final int totalBankFix6M, final int totalBankFix12M,
			final int totalBankSaveD, final int totalFCFix3M, final int totalFCFix6M, final int totalFCFix12M, final int totalFCSaveD) {
		averageRecord.setBanks_fixed_deposits_3m(averageRecord.getBanks_fixed_deposits_3m() / totalBankFix3M);
		averageRecord.setBanks_fixed_deposits_6m(averageRecord.getBanks_fixed_deposits_6m() / totalBankFix6M);
		averageRecord.setBanks_fixed_deposits_12m(averageRecord.getBanks_fixed_deposits_12m() / totalBankFix12M);
		averageRecord.setBanks_savings_deposits(averageRecord.getBanks_savings_deposits() / totalBankSaveD);
		
		averageRecord.setFc_fixed_deposits_3m(averageRecord.getFc_fixed_deposits_3m() / totalFCFix3M);
		averageRecord.setFc_fixed_deposits_6m(averageRecord.getFc_fixed_deposits_6m() / totalFCFix6M);
		averageRecord.setFc_fixed_deposits_12m(averageRecord.getFc_fixed_deposits_12m() / totalFCFix12M);
		averageRecord.setFc_savings_deposits(averageRecord.getFc_savings_deposits() / totalFCSaveD);
	}	
}
