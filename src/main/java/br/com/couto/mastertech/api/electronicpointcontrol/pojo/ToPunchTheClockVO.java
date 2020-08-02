package br.com.couto.mastertech.api.electronicpointcontrol.pojo;

import java.io.Serializable;

import br.com.couto.mastertech.model.TipoMarcacao;

public class ToPunchTheClockVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String record;
	private TipoMarcacao pointRecorderType;

	public ToPunchTheClockVO(String record, TipoMarcacao pointRecorderType) {
		super();
		this.record = record;
		this.pointRecorderType = pointRecorderType;
	}
	
	public String getRecord() {
		return record;
	}
	
	public TipoMarcacao getPointRecorderType() {
		return pointRecorderType;
	}
}