package br.com.couto.mastertech.api.electronicpointcontrol.pojo;

import java.io.Serializable;

import br.com.couto.mastertech.model.TipoBatidaModel;

public class ToPunchTheClockVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String record;
	private TipoBatidaModel pointRecorderType;

	public ToPunchTheClockVO(String record, TipoBatidaModel pointRecorderType) {
		super();
		this.record = record;
		this.pointRecorderType = pointRecorderType;
	}
	
	public String getRecord() {
		return record;
	}
	
	public TipoBatidaModel getPointRecorderType() {
		return pointRecorderType;
	}
}