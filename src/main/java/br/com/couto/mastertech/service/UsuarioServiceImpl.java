package br.com.couto.mastertech.service;

import br.com.couto.mastertech.model.UsuarioModel;
import br.com.couto.mastertech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public List<UsuarioModel> findAll() {
        return repository.findAll();
    }

    @Override
    public UsuarioModel findById(Long idUser) {
        return repository.findById(idUser).isPresent() ? repository.findById(idUser).get() : null;
    }

    @Override
    public UsuarioModel save(UsuarioModel user) {
        return repository.save(user);
    }
}