package br.com.couto.mastertech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.couto.mastertech.entity.PontoEletronico;
import br.com.couto.mastertech.entity.Usuario;

@Repository
public interface PontoEletronicoRepository extends JpaRepository<PontoEletronico, Long> {
	
	List<PontoEletronico> findByUser(Usuario user);

}
