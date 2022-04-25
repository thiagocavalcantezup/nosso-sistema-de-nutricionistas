package br.com.zup.edu.nutricionistas.model;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Nutricionista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String CRN;

    @Column(nullable = false)
    @CPF
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    public Nutricionista(String nome, String email, String CRN, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.CRN = CRN;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }


    @Deprecated
    public Nutricionista() {
    }

    public Long getId() {
        return id;
    }
}
