package br.com.couto.mastertech.service.usuario;

import java.util.List;

import br.com.couto.mastertech.model.UsuarioModel;

public interface UsuarioService {

    List<UsuarioModel> findAll();

    UsuarioModel findById(Long idUsuario) throws Exception;

    UsuarioModel save(UsuarioModel usuario);

    UsuarioModel edit(Long id, UsuarioModel usuario) throws Exception;
}