package br.com.couto.mastertech.service;

import java.util.List;

import br.com.couto.mastertech.entity.Usuario;

public interface UsuarioService {

    List<Usuario> findAll();

    Usuario findById(Long idUsuario);

    Usuario save(Usuario usuario);
}