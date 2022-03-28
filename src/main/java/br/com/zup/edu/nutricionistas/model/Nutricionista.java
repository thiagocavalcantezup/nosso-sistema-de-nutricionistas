package br.com.zup.edu.nutricionistas.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "nutricionistas")
public class Nutricionista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String crn;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @CPF
    private String cpf;

    @Column(nullable = false)
    @Past
    private LocalDate dataNascimento;

    @Column(nullable = false)
    @Email
    private String email;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Nutricionista() {}

    public Nutricionista(String crn, String nome, @CPF String cpf, @Past LocalDate dataNascimento,
                         @Email String email) {
        this.crn = crn;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

}
