package br.com.couto.mastertech.api.electronicpointcontrol.pojo;

import java.io.Serializable;

import br.com.couto.mastertech.api.electronicpointcontrol.model.PointRecordType;

public class ToPunchTheClockVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String record;
	private PointRecordType pointRecorderType;

	public ToPunchTheClockVO(String record, PointRecordType pointRecorderType) {
		super();
		this.record = record;
		this.pointRecorderType = pointRecorderType;
	}
	
	public String getRecord() {
		return record;
	}
	
	public PointRecordType getPointRecorderType() {
		return pointRecorderType;
	}
}