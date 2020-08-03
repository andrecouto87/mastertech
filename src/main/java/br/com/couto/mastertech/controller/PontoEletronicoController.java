package br.com.couto.mastertech.controller;

import br.com.couto.mastertech.model.PontoEletronicoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.couto.mastertech.service.pontoeletronico.PontoEletronicoService;

import java.util.List;

@RestController
@RequestMapping("/pontoeletronico")
public class PontoEletronicoController {

    @Autowired
    private PontoEletronicoService pontoEletronicoService;

    @GetMapping()
    public ResponseEntity<List<PontoEletronicoModel>> findAll(@RequestHeader Long idUsuario) {
        try {
            List<PontoEletronicoModel> pontos = pontoEletronicoService.findAll(idUsuario);
            return ResponseEntity.ok().body(pontos);
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <-- error";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag(errorMessage).build();
        }
    }

    @PostMapping()
    public ResponseEntity<String> register(@RequestHeader Long idUsuario) {
        try {
            pontoEletronicoService.register(idUsuario);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <-- error";
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
}