package br.com.zup.edu.nutricionistas.exceptions;

import java.util.List;

public class ErroPadronizado {

    private Integer codigoHttp;
    private String mensagemHttp;
    private String mensagemGeral;
    private List<String> mensagens;

    public ErroPadronizado(Integer codigoHttp, String mensagemHttp, String mensagemGeral,
                           List<String> mensagens) {
        this.codigoHttp = codigoHttp;
        this.mensagemHttp = mensagemHttp;
        this.mensagemGeral = mensagemGeral;
        this.mensagens = mensagens;
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
