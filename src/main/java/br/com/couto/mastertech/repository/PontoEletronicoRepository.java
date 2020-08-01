package br.com.couto.mastertech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.couto.mastertech.model.PontoEletronicoModel;
import br.com.couto.mastertech.model.UsuarioModel;

@Repository
public interface PontoEletronicoRepository extends JpaRepository<PontoEletronicoModel, Long> {
	
	List<PontoEletronicoModel> findByUser(UsuarioModel user);

}
