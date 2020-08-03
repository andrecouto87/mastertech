package br.com.couto.mastertech.service.pontoeletronico;

import br.com.couto.mastertech.model.PontoEletronicoModel;

import java.util.List;

public interface PontoEletronicoService {

    void register(Long idUsuario) throws Exception;

    List<PontoEletronicoModel> findAll(Long idUsuario);

}
