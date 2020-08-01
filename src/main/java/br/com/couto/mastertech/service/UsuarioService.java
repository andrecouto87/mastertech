package br.com.couto.mastertech.service;

import java.util.List;

import br.com.couto.mastertech.model.UsuarioModel;

public interface UsuarioService {

    List<UsuarioModel> findAll();

    UsuarioModel findById(Long idUsuario);

    UsuarioModel save(UsuarioModel usuario);
}