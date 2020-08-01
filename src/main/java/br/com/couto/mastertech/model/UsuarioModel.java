package br.com.couto.mastertech.model;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_user", schema = "MASTERTECH")
public class UsuarioModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CPF(message = "Invalid CPF")
    @Column(name = "cpf")
    @NotBlank
    private String cpf;

    @Column(name = "full_name")
    @NotBlank
    private String fullName;

    @Email(message = "Invalid E-mail")
    @Column(name = "email")
    @NotBlank
    private String email;

    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationNamwe) {
        this.registrationDate = registrationNamwe;
    }
    
    public void validateUser() {
    	if (this.getCpf() == null || this.getCpf().isEmpty())
    		throw new IllegalArgumentException("CPF cannot be blank.");
    	
    	if (this.getEmail() == null || this.getEmail().isEmpty())
    		throw new IllegalArgumentException("Email cannot be blank.");
    	
    	if (this.getFullName() == null || this.getFullName().isEmpty())
    		throw new IllegalArgumentException("Full name cannot be blank.");
    	
    	if (this.getRegistrationDate() == null)
    		throw new IllegalArgumentException("Registration date cannot be blank.");    	
    }
}