package br.com.couto.mastertech.api.electronicpointcontrol.pojo;

import java.util.List;

import br.com.couto.mastertech.model.UsuarioModel;

public class ElectronicPointControlDTO<EletronicPointControlVO> {
	
	private UsuarioModel user;
	private List<EletronicPointControlVO> eletronicPointControls;

	public UsuarioModel getUser() {
		return user;
	}

	public void setUsuario(UsuarioModel user) {
		this.user = user;
	}

	public List<EletronicPointControlVO> getEletricPointControl() {
		return eletronicPointControls;
	}

	public void setEletricPointControl(List<EletronicPointControlVO> eletronicPointControls) {
		this.eletronicPointControls = eletronicPointControls;
	}
}