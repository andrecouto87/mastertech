package br.com.couto.mastertech.api.electronicpointcontrol.service;

import java.util.List;

import br.com.couto.mastertech.api.electronicpointcontrol.model.ElectronicPointControl;
import br.com.couto.mastertech.api.electronicpointcontrol.pojo.ElectronicPointControlDTO;

public interface ElectronicPointControlService {

    ElectronicPointControl save(ElectronicPointControl electronicPointControl);
    
    List<ElectronicPointControlDTO> findByUser(Long idUser);
    
}
