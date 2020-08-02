package br.com.couto.mastertech.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PontoEletronicoModel {

    @JsonProperty(value = "data")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date data;

    @JsonProperty(value = "horasTrabalhadas")
    private String horasTrabalhadas;

    @JsonProperty(value = "marcacoes")
    private List<MarcacaoModel> marcacoes;
}

