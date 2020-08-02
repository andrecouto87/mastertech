package br.com.couto.mastertech.service;

import java.util.List;

import br.com.couto.mastertech.entity.PontoEletronico;
import br.com.couto.mastertech.api.electronicpointcontrol.pojo.ElectronicPointControlDTO;

public interface PontoEletronicoService {

    PontoEletronico save(PontoEletronico electronicPointControl);
    
    List<ElectronicPointControlDTO> findByUser(Long idUser);
    
}
