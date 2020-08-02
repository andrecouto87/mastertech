package br.com.couto.mastertech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.couto.mastertech.entity.Usuario;
import br.com.couto.mastertech.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioService userService;

    @GetMapping(path="/find")
    public ResponseEntity<?> findAll() {

        try {
            List<Usuario> users = userService.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <-- error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/find/{idUser}")
    public ResponseEntity<?> findById(@PathVariable Long idUser) {

        try {
            
            Usuario user = userService.findById(idUser);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <-- error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/save", consumes="application/json")
    public ResponseEntity<?> save(@RequestBody Usuario user) {

        try {        	        	        	
        	user.validateUser();

            Usuario userresponse = userService.save(user);
            return new ResponseEntity<>(userresponse, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <-- error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path="/save", consumes="application/json")
    public ResponseEntity<?> edit(@RequestBody Usuario user) {

        try {
        	user.validateUser();
        	
            Usuario userresponse = userService.save(user);
            return new ResponseEntity<>(userresponse, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <-- error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }
}