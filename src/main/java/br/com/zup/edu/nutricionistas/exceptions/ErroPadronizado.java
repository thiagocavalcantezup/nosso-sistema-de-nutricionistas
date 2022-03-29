package br.com.zup.edu.nutricionistas.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

public class ErroPadronizado {

    private Integer codigoHttp;
    private String mensagemHttp;
    private String mensagemGeral;
    private List<String> mensagens;

    public ErroPadronizado(Integer codigoHttp, String mensagemHttp, String mensagemGeral) {
        this.codigoHttp = codigoHttp;
        this.mensagemHttp = mensagemHttp;
        this.mensagemGeral = mensagemGeral;
        this.mensagens = new ArrayList<>();
    }

    public ErroPadronizado(Integer codigoHttp, String mensagemHttp, String mensagemGeral,
                           List<String> mensagens) {
        this.codigoHttp = codigoHttp;
        this.mensagemHttp = mensagemHttp;
        this.mensagemGeral = mensagemGeral;
        this.mensagens = mensagens;
    }

    public void adicionarErro(FieldError fieldError) {
        String field = fieldError.getField();

        if (field.equals("dataNascimento")) {
            field = "data_nascimento";
        }

        mensagens.add(field + ": " + fieldError.getDefaultMessage());
    }

    public void adicionarErro(String error) {
        mensagens.add(error);
    }

    public Integer getCodigoHttp() {
        return codigoHttp;
    }

    public String getMensagemHttp() {
        return mensagemHttp;
    }

    public String getMensagemGeral() {
        return mensagemGeral;
    }

    public List<String> getMensagens() {
        return mensagens;
    }

}
