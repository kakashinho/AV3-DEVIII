package com.autobots.automanager.excecao;

public class DocumentoDuplicadoException extends RuntimeException {
    public DocumentoDuplicadoException(String numero) {
        super("Documento já cadastrado: " + numero);
    }
}