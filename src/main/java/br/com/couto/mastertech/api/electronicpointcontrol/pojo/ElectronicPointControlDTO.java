package br.com.couto.mastertech.api.electronicpointcontrol.pojo;

import java.util.List;

import br.com.couto.mastertech.entity.Usuario;

public class ElectronicPointControlDTO<EletronicPointControlVO> {
	
	private Usuario user;
	private List<EletronicPointControlVO> eletronicPointControls;

	public Usuario getUser() {
		return user;
	}

	public void setUsuario(Usuario user) {
		this.user = user;
	}

	public List<EletronicPointControlVO> getEletricPointControl() {
		return eletronicPointControls;
	}

	public void setEletricPointControl(List<EletronicPointControlVO> eletronicPointControls) {
		this.eletronicPointControls = eletronicPointControls;
	}
}