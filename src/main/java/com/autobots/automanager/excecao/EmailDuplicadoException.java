package com.autobots.automanager.excecao;

public class EmailDuplicadoException extends RuntimeException {
    public EmailDuplicadoException(String email) {
        super("Email já cadastrado: " + email);
    }
}