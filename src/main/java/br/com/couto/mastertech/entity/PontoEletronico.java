package br.com.couto.mastertech.entity;

import br.com.couto.mastertech.model.TipoMarcacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PontoEletronico {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)
    @Getter
    @OrderBy
    Date data;

    @Temporal(TemporalType.TIME)
    @Getter
    @OrderBy
    Date hora;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private TipoMarcacao tipoMarcacao;

    @ManyToOne
    @Getter
    @Setter
    private Usuario usuario;

    @PrePersist
    protected void onCreate() {
        data = new Date();
        hora = new Date();
    }

}