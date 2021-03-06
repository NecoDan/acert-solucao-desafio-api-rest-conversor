package br.com.acert.api.conversor.temperaturas.util.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidadorException extends Exception {

    public ValidadorException(String s) {
        super(s);
    }

    public ValidadorException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ValidadorException(Throwable throwable) {
        super(throwable);
    }

    public ValidadorException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
