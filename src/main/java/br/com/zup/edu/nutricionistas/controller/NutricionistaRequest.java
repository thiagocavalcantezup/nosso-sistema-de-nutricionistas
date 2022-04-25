package br.com.zup.edu.nutricionistas.controller;

import br.com.zup.edu.nutricionistas.model.Nutricionista;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class NutricionistaRequest {
    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @JsonProperty("CRN")
    private String CRN;

    @NotBlank
    @CPF
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    @Past
    private LocalDate dataNascimento;

    public NutricionistaRequest(String nome, String email, String CRN, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.CRN = CRN;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    @Deprecated
    public NutricionistaRequest() {
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCRN() {
        return CRN;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Nutricionista paraNutricionista() {
        return new Nutricionista(nome,email,CRN,cpf,dataNascimento);
    }
}
