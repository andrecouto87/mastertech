package br.com.couto.mastertech.service.usuario;

import br.com.couto.mastertech.entity.Usuario;
import br.com.couto.mastertech.model.UsuarioModel;
import br.com.couto.mastertech.repository.UsuarioRepository;
import br.com.couto.mastertech.util.UsuarioWrapperUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public List<UsuarioModel> findAll() {
        List<Usuario> usuarios = repository.findAll();
        return usuarios.stream()
                .map(UsuarioWrapperUtil::unwrapperUsuarioToUsuarioModel)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioModel findById(Long idUsuario) throws Exception {
        Optional<Usuario> usuario = repository.findById(idUsuario);
        if (usuario.isPresent()) {
            return UsuarioWrapperUtil.unwrapperUsuarioToUsuarioModel(usuario.get());
        } else {
            throw new NotFoundException("Usuário não encontrado.");
        }
    }

    @Override
    public UsuarioModel save(@Valid UsuarioModel usuarioModel) {
        Usuario novoUsuario = repository.save(UsuarioWrapperUtil.unwrapperUsuarioModelToUsuario(usuarioModel));
        return UsuarioWrapperUtil.unwrapperUsuarioToUsuarioModel(novoUsuario);
    }

    public UsuarioModel edit(Long id, @Valid UsuarioModel novoUsuario) throws Exception {
        if (repository.existsById(novoUsuario.getId())) {
            Optional<Usuario> oldUsuario = repository.findById(id);
            Usuario usuario = oldUsuario.get();
            usuario.setNome(novoUsuario.getNome());
            usuario.setCpf(novoUsuario.getCpf());
            usuario.setEmail(novoUsuario.getEmail());
            return UsuarioWrapperUtil.unwrapperUsuarioToUsuarioModel(repository.saveAndFlush(usuario));
        } else {
            throw new NotFoundException("Usuário não encontrado.");
        }
    }

}