package br.com.couto.mastertech.api.user.service.impl;

import br.com.couto.mastertech.api.user.model.User;
import br.com.couto.mastertech.api.user.repository.UserRepository;
import br.com.couto.mastertech.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Long idUser) {
        return repository.findById(idUser).isPresent() ? repository.findById(idUser).get() : null;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }
}