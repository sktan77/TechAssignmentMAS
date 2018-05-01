package tsk.techassignment.service.processor;

import tsk.techassignment.controller.MASCompareRateController;
import tsk.techassignment.model.jsonobj.Record;

public class AverageRecordProcessor extends RecordProcessor {
	private Record averageRecord;
	
	public Record getAverageRecord() {
		return averageRecord;
	}

	@Override
	public void process() {
		if (this.getRecordList() != null && this.getRecordList().size() != 0) {
			int countBankFixD3M = 0, countBankFixD6M = 0, countBankFixD12M = 0, countBankSaveD = 0, countFCFixD3M = 0,
					countFCFixD6M = 0, countFCFixD12M = 0, countFCSaveD = 0;
			this.averageRecord = new Record(null, new Double(0), new Double(0), new Double(0), new Double(0),
					new Double(0), new Double(0), new Double(0), new Double(0));

			for (Record record : this.getRecordList()) {
				sumEachRecord(record);

				if (record.getBanks_fixed_deposits_3m() != null)
					countBankFixD3M++;
				if (record.getBanks_fixed_deposits_6m() != null)
					countBankFixD6M++;
				if (record.getBanks_fixed_deposits_12m() != null)
					countBankFixD12M++;
				if (record.getBanks_savings_deposits() != null)
					countBankSaveD++;

				if (record.getFc_fixed_deposits_3m() != null)
					countFCFixD3M++;
				if (record.getFc_fixed_deposits_6m() != null)
					countFCFixD6M++;
				if (record.getFc_fixed_deposits_12m() != null)
					countFCFixD12M++;
				if (record.getFc_savings_deposits() != null)
					countFCSaveD++;
			}

			performAverage(countBankFixD3M, countBankFixD6M, countBankFixD12M, countBankSaveD,
					countFCFixD3M, countFCFixD6M, countFCFixD12M, countFCSaveD);
			
			this.setModelValue(MASCompareRateController.SUMMARYAVERAGEMODEL, this.averageRecord);
			
			this.continueProcessing();
		}
	}

	private void sumEachRecord(final Record eachRecord) {
		this.averageRecord.setBanks_fixed_deposits_3m(
				addDouble(this.averageRecord.getBanks_fixed_deposits_3m(), eachRecord.getBanks_fixed_deposits_3m()));
		this.averageRecord.setBanks_fixed_deposits_6m(
				addDouble(this.averageRecord.getBanks_fixed_deposits_6m(), eachRecord.getBanks_fixed_deposits_6m()));
		this.averageRecord.setBanks_fixed_deposits_12m(
				addDouble(this.averageRecord.getBanks_fixed_deposits_12m(), eachRecord.getBanks_fixed_deposits_12m()));
		this.averageRecord.setBanks_savings_deposits(
				addDouble(this.averageRecord.getBanks_savings_deposits(), eachRecord.getBanks_savings_deposits()));

		this.averageRecord.setFc_fixed_deposits_3m(
				addDouble(this.averageRecord.getFc_fixed_deposits_3m(), eachRecord.getFc_fixed_deposits_3m()));
		this.averageRecord.setFc_fixed_deposits_6m(
				addDouble(this.averageRecord.getFc_fixed_deposits_6m(), eachRecord.getFc_fixed_deposits_6m()));
		this.averageRecord.setFc_fixed_deposits_12m(
				addDouble(this.averageRecord.getFc_fixed_deposits_12m(), eachRecord.getFc_fixed_deposits_12m()));
		this.averageRecord.setFc_savings_deposits(
				addDouble(this.averageRecord.getFc_savings_deposits(), eachRecord.getFc_savings_deposits()));
	}

	private Double addDouble(Double totalValue, final Double recordValue) {
		if (recordValue != null) {
			totalValue += recordValue;
		}

		return totalValue;
	}

	private void performAverage(final int totalBankFix3M, final int totalBankFix6M,
			final int totalBankFix12M, final int totalBankSaveD, final int totalFCFix3M, final int totalFCFix6M,
			final int totalFCFix12M, final int totalFCSaveD) {
		this.averageRecord.setBanks_fixed_deposits_3m(this.averageRecord.getBanks_fixed_deposits_3m() / totalBankFix3M);
		this.averageRecord.setBanks_fixed_deposits_6m(this.averageRecord.getBanks_fixed_deposits_6m() / totalBankFix6M);
		this.averageRecord.setBanks_fixed_deposits_12m(this.averageRecord.getBanks_fixed_deposits_12m() / totalBankFix12M);
		this.averageRecord.setBanks_savings_deposits(this.averageRecord.getBanks_savings_deposits() / totalBankSaveD);

		this.averageRecord.setFc_fixed_deposits_3m(this.averageRecord.getFc_fixed_deposits_3m() / totalFCFix3M);
		this.averageRecord.setFc_fixed_deposits_6m(this.averageRecord.getFc_fixed_deposits_6m() / totalFCFix6M);
		this.averageRecord.setFc_fixed_deposits_12m(this.averageRecord.getFc_fixed_deposits_12m() / totalFCFix12M);
		this.averageRecord.setFc_savings_deposits(this.averageRecord.getFc_savings_deposits() / totalFCSaveD);
	}
}
