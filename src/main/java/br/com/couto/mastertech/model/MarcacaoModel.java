package br.com.couto.mastertech.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarcacaoModel {

    @JsonProperty(value = "hora")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date hora;

    @JsonProperty(value = "tipo")
    private TipoMarcacao tipoMarcacao;
}
