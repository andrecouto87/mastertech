package br.com.couto.mastertech.api.electronicpointcontrol.pojo;

import java.util.List;

import br.com.couto.mastertech.api.user.model.User;

public class ElectronicPointControlDTO<EletronicPointControlVO> {
	
	private User user;
	private List<EletronicPointControlVO> eletronicPointControls;

	public User getUser() {
		return user;
	}

	public void setUsuario(User user) {
		this.user = user;
	}

	public List<EletronicPointControlVO> getEletricPointControl() {
		return eletronicPointControls;
	}

	public void setEletricPointControl(List<EletronicPointControlVO> eletronicPointControls) {
		this.eletronicPointControls = eletronicPointControls;
	}
}