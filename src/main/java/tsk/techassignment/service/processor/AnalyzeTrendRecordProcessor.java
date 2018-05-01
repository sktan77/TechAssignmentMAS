package tsk.techassignment.service.processor;

import tsk.techassignment.controller.MASCompareRateController;
import tsk.techassignment.model.TrendOverview;
import tsk.techassignment.model.TrendValue;

public class AnalyzeTrendRecordProcessor extends RecordProcessor {
	private TrendOverview trendOverview;

	public TrendOverview getTrendOverview() {
		return trendOverview;
	}

	@Override
	public void process() {
		if (this.recordList != null && this.recordList.size() > 1) {

			this.trendOverview = new TrendOverview();

			this.trendOverview.setTrendBankFixDeposit3Months(goUpOrDownBaseOnEarliestAndLatest(
					recordList.get(recordList.size() - 1).getBanks_fixed_deposits_3m(),
					recordList.get(0).getBanks_fixed_deposits_3m()));
			this.trendOverview.setTrendBankFixDeposit6Months(goUpOrDownBaseOnEarliestAndLatest(
					recordList.get(recordList.size() - 1).getBanks_fixed_deposits_6m(),
					recordList.get(0).getBanks_fixed_deposits_6m()));
			this.trendOverview.setTrendBankFixDeposit12Months(goUpOrDownBaseOnEarliestAndLatest(
					recordList.get(recordList.size() - 1).getBanks_fixed_deposits_12m(),
					recordList.get(0).getBanks_fixed_deposits_12m()));
			this.trendOverview.setTrendBankSavingDeposit(
					goUpOrDownBaseOnEarliestAndLatest(recordList.get(recordList.size() - 1).getBanks_savings_deposits(),
							recordList.get(0).getBanks_savings_deposits()));

			this.trendOverview.setTrendFCFixDeposit3Months(
					goUpOrDownBaseOnEarliestAndLatest(recordList.get(recordList.size() - 1).getFc_fixed_deposits_3m(),
							recordList.get(0).getFc_fixed_deposits_3m()));
			this.trendOverview.setTrendFCFixDeposit6Months(
					goUpOrDownBaseOnEarliestAndLatest(recordList.get(recordList.size() - 1).getFc_fixed_deposits_6m(),
							recordList.get(0).getFc_fixed_deposits_6m()));
			this.trendOverview.setTrendFCFixDeposit12Months(
					goUpOrDownBaseOnEarliestAndLatest(recordList.get(recordList.size() - 1).getFc_fixed_deposits_12m(),
							recordList.get(0).getFc_fixed_deposits_12m()));
			this.trendOverview.setTrendFCSavingDeposit(
					goUpOrDownBaseOnEarliestAndLatest(recordList.get(recordList.size() - 1).getFc_savings_deposits(),
							recordList.get(0).getFc_savings_deposits()));
						
			this.setModelValue(MASCompareRateController.SUMMARYTRENDMODEL, this.trendOverview);
			this.setModelValue(MASCompareRateController.EARLIESTRECORD, this.getRecordList().get(this.getRecordList().size() - 1));
			this.setModelValue(MASCompareRateController.LATESTRECORD, this.getRecordList().get(0));
		}
	}

	private TrendValue goUpOrDownBaseOnEarliestAndLatest(final Double earliestRate, final Double latestRate) {
		if (earliestRate == null || latestRate == null)
			return TrendValue.NA;

		int i = earliestRate.compareTo(latestRate);

		if (i == 0)
			return TrendValue.NoChange;
		else if (i > 0)
			return TrendValue.Down;
		else
			return TrendValue.Up;
	}
}
