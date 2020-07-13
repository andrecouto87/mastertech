package br.com.couto.mastertech.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.couto.mastertech.api.user.model.User;
import br.com.couto.mastertech.api.user.service.UserService;

import java.util.List;

import com.sun.el.stream.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path="/find")
    public ResponseEntity<?> findAll() {

        try {
            List<User> users = userService.findAll();
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
            
            User user = userService.findById(idUser);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <-- error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/save", consumes="application/json")
    public ResponseEntity<?> save(@RequestBody User user) {

        try {        	        	        	
        	user.validateUser();

            User userresponse = userService.save(user);
            return new ResponseEntity<>(userresponse, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <-- error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path="/save", consumes="application/json")
    public ResponseEntity<?> edit(@RequestBody User user) {

        try {
        	user.validateUser();
        	
            User userresponse = userService.save(user);
            return new ResponseEntity<>(userresponse, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <-- error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }
}