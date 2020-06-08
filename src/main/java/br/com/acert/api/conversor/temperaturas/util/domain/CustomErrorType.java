package br.com.acert.api.conversor.temperaturas.util.domain;

public class CustomErrorType {

    private String erroMensagem;

    public CustomErrorType(String erroMensagem) {
        this.erroMensagem = erroMensagem;
    }

    public String getErroMensagem() {
        return erroMensagem;
    }
}
