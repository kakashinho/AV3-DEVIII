package com.autobots.automanager.excecao;

public class CredencialDuplicadaException extends RuntimeException {
    public CredencialDuplicadaException(String identificador) {
        super("Credencial já cadastrada: " + identificador);
    }
}