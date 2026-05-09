package com.autobots.automanager.servico;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autobots.automanager.dto.requisicao.*;
import com.autobots.automanager.entidade.*;
import com.autobots.automanager.excecao.*;
import com.autobots.automanager.mapeador.UsuarioMapper;
import com.autobots.automanager.repositorio.RepositorioUsuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final RepositorioUsuario repositorio;
    private final UsuarioMapper mapper;

    public UsuarioServiceImpl(RepositorioUsuario repositorio, UsuarioMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Usuario cadastrar(UsuarioRequest request) {
        validarUnicidadeDocumento(request.getDocumentos(), null);
        validarUnicidadeEmail(request.getEmails(), null);
        validarUnicidadeCredenciais(request.getCredenciais(), null);
        Usuario usuario = mapper.paraEntidade(request);
        return repositorio.save(usuario);
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    @Override
    public List<Usuario> listarTodos() {
        return repositorio.findAll();
    }

    @Override
    @Transactional
    public Usuario atualizar(Long id, UsuarioRequest request) {
        Usuario usuario = buscarPorId(id);
        validarUnicidadeDocumento(request.getDocumentos(), usuario);
        validarUnicidadeEmail(request.getEmails(), usuario);
        validarUnicidadeCredenciais(request.getCredenciais(), usuario);
        usuario.setNome(request.getNome());
        usuario.setNomeSocial(request.getNomeSocial());
        usuario.setPerfis(request.getPerfis());
        if (request.getEndereco() != null)
            usuario.setEndereco(mapper.paraEntidadeEndereco(request.getEndereco()));
        usuario.getTelefones().clear();
        request.getTelefones().forEach(t -> usuario.getTelefones().add(mapper.paraEntidadeTelefone(t)));
        usuario.getEmails().clear();
        request.getEmails().forEach(e -> usuario.getEmails().add(mapper.paraEntidadeEmail(e)));
        usuario.getDocumentos().clear();
        request.getDocumentos().forEach(d -> usuario.getDocumentos().add(mapper.paraEntidadeDocumento(d)));
        usuario.getCredenciais().clear();
        request.getCredenciais().forEach(c -> usuario.getCredenciais().add(mapper.paraEntidadeCredencial(c)));
        return repositorio.save(usuario);
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Usuario usuario = buscarPorId(id);
        repositorio.delete(usuario);
    }

    @Override
    @Transactional
    public Telefone adicionarTelefone(Long usuarioId, TelefoneRequest request) {
        Usuario usuario = buscarPorId(usuarioId);
        Telefone telefone = mapper.paraEntidadeTelefone(request);
        usuario.getTelefones().add(telefone);
        repositorio.save(usuario);
        return telefone;
    }

    @Override
    @Transactional
    public Documento adicionarDocumento(Long usuarioId, DocumentoRequest request) {
        Usuario usuario = buscarPorId(usuarioId);
        Documento doc = mapper.paraEntidadeDocumento(request);
        usuario.getDocumentos().add(doc);
        repositorio.save(usuario);
        return doc;
    }

    @Override
    @Transactional
    public Email adicionarEmail(Long usuarioId, EmailRequest request) {
        Usuario usuario = buscarPorId(usuarioId);
        Email email = mapper.paraEntidadeEmail(request);
        usuario.getEmails().add(email);
        repositorio.save(usuario);
        return email;
    }
    
    @Override
    @Transactional
    public Credencial adicionarCredencial(Long usuarioId, CredencialRequest request) {
        Usuario usuario = buscarPorId(usuarioId);
        Credencial cred = mapper.paraEntidadeCredencial(request);
        usuario.getCredenciais().add(cred);
        repositorio.save(usuario);
        return cred;
    }

    private void validarUnicidadeDocumento(Set<DocumentoRequest> docs, Usuario usuarioExistente) {
        if (docs == null) return;
        for (DocumentoRequest doc : docs) {
            if (repositorio.existsByDocumentosNumero(doc.getNumero())
                    && (usuarioExistente == null || usuarioExistente.getDocumentos().stream()
                    .noneMatch(d -> d.getNumero().equals(doc.getNumero())))) {
                throw new DocumentoDuplicadoException(doc.getNumero());
            }
        }
    }

    private void validarUnicidadeEmail(Set<EmailRequest> emails, Usuario usuarioExistente) {
        if (emails == null) return;
        for (EmailRequest email : emails) {
            if (repositorio.existsByEmailsEndereco(email.getEndereco())
                    && (usuarioExistente == null || usuarioExistente.getEmails().stream()
                    .noneMatch(e -> e.getEndereco().equals(email.getEndereco())))) {
                throw new EmailDuplicadoException(email.getEndereco());
            }
        }
    }

    private void validarUnicidadeCredenciais(Set<CredencialRequest> credenciais, Usuario usuarioExistente) {
        if (credenciais == null) return;
    }
}