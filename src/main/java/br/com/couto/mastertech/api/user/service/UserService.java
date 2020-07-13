package br.com.couto.mastertech.api.user.service;

import java.util.List;

import br.com.couto.mastertech.api.user.model.User;

public interface UserService {

    List<User> findAll();

    User findById(Long idUsuario);

    User save(User usuario);
}