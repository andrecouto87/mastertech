package br.com.couto.mastertech.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.couto.mastertech.api.electronicpointcontrol.converter.PointControlTypeConverter;
import br.com.couto.mastertech.model.TipoBatidaModel;

@Entity
@Table(name = "tb_electronic_point_control", schema = "MASTERTECH")
public class PontoEletronicoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id", insertable = true)
    private UsuarioModel user;
    
    @Column(name = "point_record_date")
    @Temporal(TemporalType.TIMESTAMP)
	private Date pointRecordDate;
	
	@Column(name = "point_record_type")
	@Convert(converter = PointControlTypeConverter.class)
    private TipoBatidaModel pointRecordType;
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioModel getUser() {
        return user;
    }

    public void setUser(UsuarioModel user) {
        this.user = user;
    }

	public Date getPointRecordDate() {
		return pointRecordDate;
	}

	public void setPointRecordDate(Date pointRecordDate) {
		this.pointRecordDate= pointRecordDate;
	}

	public TipoBatidaModel getPointRecordType() {
		return pointRecordType;
	}

	public void setPointRecordType(TipoBatidaModel pointRecordType) {
		this.pointRecordType = pointRecordType;
	}
}