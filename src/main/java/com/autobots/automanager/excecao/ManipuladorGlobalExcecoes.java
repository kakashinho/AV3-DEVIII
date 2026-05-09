package com.autobots.automanager.excecao;

import com.autobots.automanager.modelo.ResponsePadrao;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ManipuladorGlobalExcecoes {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
        return construirResposta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<?> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex) {
        return construirResposta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(EmpresaNaoEncontradaException.class)
    public ResponseEntity<?> handleEmpresaNaoEncontrada(EmpresaNaoEncontradaException ex) {
        return construirResposta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UsuarioJaAssociadoException.class)
    public ResponseEntity<?> handleUsuarioJaAssociado(UsuarioJaAssociadoException ex) {
        return construirResposta(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(EstoqueNegativoException.class)
    public ResponseEntity<?> handleEstoqueNegativo(EstoqueNegativoException ex) {
        return construirResposta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ServicoInativoException.class)
    public ResponseEntity<?> handleServicoInativo(ServicoInativoException ex) {
        return construirResposta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(CredencialDuplicadaException.class)
    public ResponseEntity<?> handleCredencialDuplicada(CredencialDuplicadaException ex) {
        return construirResposta(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(DocumentoDuplicadoException.class)
    public ResponseEntity<?> handleDocumentoDuplicado(DocumentoDuplicadoException ex) {
        return construirResposta(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(EmailDuplicadoException.class)
    public ResponseEntity<?> handleEmailDuplicado(EmailDuplicadoException ex) {
        return construirResposta(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidation(ValidationException ex) {
        return construirResposta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    private ResponseEntity<ResponsePadrao<String>> construirResposta(
            HttpStatus status, String mensagem) {
        ResponsePadrao<String> resposta = new ResponsePadrao<>();
        resposta.setStatus(status.value());
        resposta.setMensagem(mensagem);
        resposta.setTimestamp(Instant.now().toString());
        return ResponseEntity.status(status).body(resposta);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponsePadrao> manipularViolacaoDeIntegridade(DataIntegrityViolationException ex) {
        ResponsePadrao response = new ResponsePadrao();
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setMensagem("Conflito de dados: O valor informado (ex: nome de usuário, código de barras) já existe no sistema."); 
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}