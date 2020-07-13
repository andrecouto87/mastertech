package br.com.couto.mastertech.api.electronicpointcontrol.pojo;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

public class EletronicPointControlVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String punchDate;
	private List<ToPunchTheClockVO> records;
	private LocalTime workedHours;
	
	public EletronicPointControlVO(String punchDate, List<ToPunchTheClockVO> records) {
		super();
		this.punchDate = punchDate;
		this.records = records;
	}
	
	public String getRecordDate() {
		return punchDate;
	}
	
	public List<ToPunchTheClockVO> getRecord() {
		return records;
	}

	public LocalTime getWorkedHours() {
		return workedHours;
	}

	public void setWorkHours(LocalTime workedHours) {
		this.workedHours = workedHours;
	}

	public void setPunchDate(String punchDate) {
		this.punchDate = punchDate;
	}

	public void setRecord(List<ToPunchTheClockVO> record) {
		this.records = record;
	}
}