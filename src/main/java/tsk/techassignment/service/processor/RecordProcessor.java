package tsk.techassignment.service.processor;

import java.util.List;

import org.springframework.ui.Model;

import tsk.techassignment.model.jsonobj.Record;

public abstract class RecordProcessor {
	protected RecordProcessor successor;	
	protected String jsonString;
	protected Model model;
	protected List<Record> recordList;
	
	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public List<Record> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}

	public RecordProcessor getSuccessor() {
		return successor;
	}

	public void setSuccessor(RecordProcessor successor) {
		this.successor = successor;
	}

	protected void continueProcessing() {
		if (this.getSuccessor() != null) {
			this.getSuccessor().setRecordList(this.getRecordList());
			this.getSuccessor().setModel(this.getModel());;
			this.getSuccessor().process();
		}
	}
	
	protected void setModelValue(final String key, final Object obj) {
		if (this.getModel() != null) {
			this.getModel().addAttribute(key, obj);
		}
	}
	
	public abstract void process();		
}
