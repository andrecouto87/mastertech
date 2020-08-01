package br.com.couto.mastertech.service;

import java.util.List;

import br.com.couto.mastertech.model.PontoEletronicoModel;
import br.com.couto.mastertech.api.electronicpointcontrol.pojo.ElectronicPointControlDTO;

public interface PontoEletronicoService {

    PontoEletronicoModel save(PontoEletronicoModel electronicPointControl);
    
    List<ElectronicPointControlDTO> findByUser(Long idUser);
    
}
