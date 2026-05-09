package com.autobots.automanager.servico;

import com.autobots.automanager.dto.requisicao.*;

import java.util.List;

import com.autobots.automanager.entidade.Credencial;
import com.autobots.automanager.entidade.Documento;
import com.autobots.automanager.entidade.Email;
import com.autobots.automanager.entidade.Telefone;
import com.autobots.automanager.entidade.Usuario;

public interface UsuarioService {
    Usuario cadastrar(UsuarioRequest request);
    Usuario buscarPorId(Long id);
    List<Usuario> listarTodos();
    Usuario atualizar(Long id, UsuarioRequest request);
    void remover(Long id);
    Telefone adicionarTelefone(Long usuarioId, TelefoneRequest request);
    Email adicionarEmail(Long usuarioId, EmailRequest request);
    Documento adicionarDocumento(Long usuarioId, DocumentoRequest request);
    Credencial adicionarCredencial(Long usuarioId, CredencialRequest request);
}