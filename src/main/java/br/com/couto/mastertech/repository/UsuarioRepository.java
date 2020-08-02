package br.com.couto.mastertech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.couto.mastertech.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}