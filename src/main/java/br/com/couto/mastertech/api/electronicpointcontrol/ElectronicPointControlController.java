package br.com.couto.mastertech.api.electronicpointcontrol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.couto.mastertech.api.electronicpointcontrol.model.ElectronicPointControl;
import br.com.couto.mastertech.api.electronicpointcontrol.pojo.ElectronicPointControlDTO;
import br.com.couto.mastertech.api.electronicpointcontrol.service.ElectronicPointControlService;

@RestController
@RequestMapping("/electronicpointcontrol")
public class ElectronicPointControlController {

    @Autowired
    private ElectronicPointControlService electronicPointControlService;

    @GetMapping(path="/find/{idUser}")
    public ResponseEntity<?> findById(@PathVariable Long idUser) {

        try {
            List<ElectronicPointControlDTO> electronicPointControl = electronicPointControlService.findByUser(idUser);
            return new ResponseEntity<>(electronicPointControl, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <-- error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/register", consumes="application/json")
    public ResponseEntity<?> save(@RequestBody ElectronicPointControl electronicPointControl) {

        try {
        	ElectronicPointControl point = electronicPointControlService.save(electronicPointControl);
            return new ResponseEntity<>(point, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <-- error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }
}