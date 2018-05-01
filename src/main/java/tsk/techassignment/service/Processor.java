package tsk.techassignment.process;

import java.util.List;

import org.springframework.ui.Model;

import tsk.techassignment.controller.MASCompareRateController;
import tsk.techassignment.model.TrendOverview;
import tsk.techassignment.model.jsonobj.Record;

public class ProcessRecords {
	public static void processStart(final String jsonString, final Model model) {
		// 1. Convert to Json Object
		final List<Record> recordList = TransformMasResponse.convertJsonToObj(jsonString);
		
		// 2. Calculate Average
		final Record averageRecord = AggregateRecords.overallAverage(recordList);
		
		// 3. Perform Trending
		final TrendOverview trendOverview = AnalyzeTrend.analyzeTrendFirstAndLastMonth(recordList);
		
		model.addAttribute(MASCompareRateController.DETAILMODEL, recordList);
		if (recordList != null && recordList.size() >= 2) {
			model.addAttribute(MASCompareRateController.EARLIESTRECORD, recordList.get(recordList.size() - 1));
			model.addAttribute(MASCompareRateController.LATESTRECORD, recordList.get(0));
		}
		model.addAttribute(MASCompareRateController.SUMMARYAVERAGEMODEL, averageRecord);
		model.addAttribute(MASCompareRateController.SUMMARYTRENDMODEL, trendOverview);
	}
}
