package tsk.techassignment.service;

import org.springframework.ui.Model;

import tsk.techassignment.service.processor.AnalyzeTrendRecordProcessor;
import tsk.techassignment.service.processor.AverageRecordProcessor;
import tsk.techassignment.service.processor.RecordProcessor;
import tsk.techassignment.service.processor.TransformMasResponseRecordProcessor;

public class ProcessorFactory {
	public static RecordProcessor createProcessor(final String jsonStirng, final Model model) {
		RecordProcessor convertJsonStringToObjProcessor = new TransformMasResponseRecordProcessor(jsonStirng, model);
		RecordProcessor averageProcessor = new AverageRecordProcessor();
		RecordProcessor trendProcessor = new AnalyzeTrendRecordProcessor();
		
		convertJsonStringToObjProcessor.setSuccessor(averageProcessor);
		averageProcessor.setSuccessor(trendProcessor);
		
		return convertJsonStringToObjProcessor;
	}
}
