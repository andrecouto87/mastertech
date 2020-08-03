package br.com.couto.mastertech.util;

import br.com.couto.mastertech.entity.Usuario;
import br.com.couto.mastertech.model.UsuarioModel;

import javax.validation.constraints.NotNull;

public class UsuarioWrapperUtil {

    public static UsuarioModel unwrapperUsuarioToUsuarioModel(@NotNull Usuario usuario) {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setId(usuario.getId());
        usuarioModel.setNome(usuario.getNome());
        usuarioModel.setEmail(usuario.getEmail());
        usuarioModel.setCpf(usuario.getCpf());
        usuarioModel.setData(usuario.getData());
        return usuarioModel;
    }

    public static Usuario unwrapperUsuarioModelToUsuario(@NotNull UsuarioModel usuarioModel) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioModel.getId());
        usuario.setNome(usuarioModel.getNome());
        usuario.setEmail(usuarioModel.getEmail());
        usuario.setCpf(usuarioModel.getCpf());
        return usuario;
    }
}
