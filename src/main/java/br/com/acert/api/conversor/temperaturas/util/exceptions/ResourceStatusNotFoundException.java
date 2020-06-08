package br.com.acert.api.conversor.temperaturas.util.exceptions;

public class ResourceStatusNotFoundException extends RuntimeException {
    public ResourceStatusNotFoundException(String s) {
        super("Resource: ".concat(s).concat(" not found."));
    }
}
