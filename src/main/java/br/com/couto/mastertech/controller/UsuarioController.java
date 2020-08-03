package br.com.couto.mastertech.controller;

import br.com.couto.mastertech.model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.couto.mastertech.service.usuario.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<UsuarioModel>> findAll() {
        List<UsuarioModel> usuarios = usuarioService.findAll();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping(path = {"{id}"})
    public ResponseEntity<UsuarioModel> findById(@PathVariable("id") Long idUsuario) {
        try {
            return ResponseEntity.ok(usuarioService.findById(idUsuario));
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex + " <-- error";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag(errorMessage).build();
        }
    }

    @PostMapping()
    public ResponseEntity<UsuarioModel> save(@RequestBody UsuarioModel usuario) {
        try {
            UsuarioModel newUsuario = usuarioService.save(usuario);
            return ResponseEntity.ok().body(newUsuario);
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <-- error";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).eTag(errorMessage).build();
        }
    }

    @PutMapping(path = {"{id}"})
    public ResponseEntity<UsuarioModel> edit(@PathVariable(value = "id") Long id, @RequestBody UsuarioModel usuario) {
        try {
            return ResponseEntity.ok().body(usuarioService.edit(id, usuario));
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <-- error";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag(errorMessage).build();
        }
    }
}