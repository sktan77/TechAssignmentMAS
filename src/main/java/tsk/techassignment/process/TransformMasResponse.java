package tsk.techassignment.process;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import tsk.techassignment.model.jsonobj.Record;

public class TransformMasResponse {	
	public static List<Record> convertJsonToObj(final String jsonStr) {
		if (jsonStr == null || jsonStr.trim().length() == 0) {
			return null;
		}
		
		final Gson gson = new Gson();
		final List<Record> recordList = new ArrayList<Record>();
		
		String[] splitRecords = jsonStr.split("@");
		
		for (String record : splitRecords) {
			recordList.add(gson.fromJson(record, Record.class));
		}
		
		return recordList;
	}
}
