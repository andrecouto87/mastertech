package br.com.couto.mastertech.repository;

import java.util.List;

import br.com.couto.mastertech.entity.PontoEletronico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontoEletronicoRepository extends JpaRepository<PontoEletronico, Long> {

    List<PontoEletronico> findByUsuarioId(Long idUsuario);
}
