package tsk.techassignment.service.processor;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.google.gson.Gson;

import tsk.techassignment.controller.MASCompareRateController;
import tsk.techassignment.model.jsonobj.Record;

public class TransformMasResponseRecordProcessor extends RecordProcessor {
	private final String SPLIT_DELIMITER = "@";
	
	public TransformMasResponseRecordProcessor(final String jsonStr, final Model model) {
		this.jsonString = jsonStr;
		this.model = model;
	}
	
	@Override
	public void process() {
		if (this.getJsonString() != null && this.getJsonString().trim().length() != 0) {
			final Gson gson = new Gson();
			this.setRecordList(new ArrayList<Record>());

			String[] splitRecords = this.getJsonString().split(SPLIT_DELIMITER);

			for (String record : splitRecords) {
				this.getRecordList().add(gson.fromJson(record, Record.class));
			}
									
			this.setModelValue(MASCompareRateController.DETAILMODEL, this.getRecordList());
								
			this.continueProcessing();
		}
	}
}
