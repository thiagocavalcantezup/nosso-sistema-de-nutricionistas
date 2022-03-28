package br.com.zup.edu.nutricionistas.model;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.br.CPF;

public class NutricionistaDTO {

    @NotBlank
    private String crn;

    @NotBlank
    private String nome;

    @NotBlank
    @CPF
    private String cpf;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("data_nascimento")
    @Past
    private LocalDate dataNascimento;

    @NotBlank
    @Email
    private String email;

    public NutricionistaDTO() {}

    public NutricionistaDTO(@NotBlank String crn, @NotBlank String nome, @NotBlank @CPF String cpf,
                            @NotNull @Past LocalDate dataNascimento,
                            @NotBlank @Email String email) {
        this.crn = crn;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    public Nutricionista paraNutricionista() {
        String novoCrn = cpf.replaceAll("[^0-9A-Z/]", "");
        String novoCpf = cpf.replaceAll("[^0-9]", "");

        return new Nutricionista(novoCrn, nome, novoCpf, dataNascimento, email);
    }

    public String getCrn() {
        return crn;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }

}
