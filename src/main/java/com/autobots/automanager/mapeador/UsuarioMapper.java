package com.autobots.automanager.mapeador;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.dto.requisicao.*;
import com.autobots.automanager.dto.resposta.*;
import com.autobots.automanager.entidade.*;

@Component
public class UsuarioMapper {

    public Usuario paraEntidade(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setNomeSocial(request.getNomeSocial());
        usuario.setPerfis(request.getPerfis());

        if (request.getEndereco() != null) {
            usuario.setEndereco(paraEntidadeEndereco(request.getEndereco()));
        }

        usuario.setTelefones(request.getTelefones().stream()
                .map(this::paraEntidadeTelefone).collect(Collectors.toSet()));
        usuario.setEmails(request.getEmails().stream()
                .map(this::paraEntidadeEmail).collect(Collectors.toSet()));
        usuario.setDocumentos(request.getDocumentos().stream()
                .map(this::paraEntidadeDocumento).collect(Collectors.toSet()));
        usuario.setCredenciais(request.getCredenciais().stream()
                .map(this::paraEntidadeCredencial).collect(Collectors.toSet()));

        return usuario;
    }

    public Endereco paraEntidadeEndereco(EnderecoRequest dto) {
        Endereco end = new Endereco();
        end.setEstado(dto.getEstado());
        end.setCidade(dto.getCidade());
        end.setBairro(dto.getBairro());
        end.setRua(dto.getRua());
        end.setNumero(dto.getNumero());
        end.setCodigoPostal(dto.getCodigoPostal());
        end.setInformacoesAdicionais(dto.getInformacoesAdicionais());
        return end;
    }

    public Telefone paraEntidadeTelefone(TelefoneRequest dto) {
        Telefone tel = new Telefone();
        tel.setDdd(dto.getDdd());
        tel.setNumero(dto.getNumero());
        return tel;
    }

    public Email paraEntidadeEmail(EmailRequest dto) {
        Email email = new Email();
        email.setEndereco(dto.getEndereco());
        return email;
    }

    public Documento paraEntidadeDocumento(DocumentoRequest dto) {
        Documento doc = new Documento();
        doc.setTipo(dto.getTipo());
        doc.setNumero(dto.getNumero());
        doc.setDataEmissao(dto.getDataEmissao());
        return doc;
    }

    public Credencial paraEntidadeCredencial(CredencialRequest dto) {
        Credencial cred;
        if (dto.getNomeUsuario() != null) {
            CredencialUsuarioSenha cus = new CredencialUsuarioSenha();
            cus.setNomeUsuario(dto.getNomeUsuario());
            cus.setSenha(dto.getSenha());
            cred = cus;
        } else if (dto.getCodigo() != null) {
            CredencialCodigoBarra ccb = new CredencialCodigoBarra();
            ccb.setCodigo(dto.getCodigo());
            cred = ccb;
        } else {
            throw new IllegalArgumentException("Credencial deve ter nomeUsuario ou codigo");
        }
        cred.setInativo(dto.isInativo());
        cred.setCriacao(new Date());
        return cred;
    }

    public UsuarioResponse paraResponse(Usuario usuario) {
        UsuarioResponse resp = new UsuarioResponse();
        resp.setId(usuario.getId());
        resp.setNome(usuario.getNome());
        resp.setNomeSocial(usuario.getNomeSocial());
        resp.setPerfis(usuario.getPerfis());

        if (usuario.getEndereco() != null)
            resp.setEndereco(paraEnderecoResponse(usuario.getEndereco()));

        resp.setTelefones(usuario.getTelefones().stream()
                .map(this::paraTelefoneResponse).collect(Collectors.toSet()));
        resp.setEmails(usuario.getEmails().stream()
                .map(this::paraEmailResponse).collect(Collectors.toSet()));
        resp.setDocumentos(usuario.getDocumentos().stream()
                .map(this::paraDocumentoResponse).collect(Collectors.toSet()));
        resp.setCredenciais(usuario.getCredenciais().stream()
                .map(this::paraCredencialResponse).collect(Collectors.toSet()));

        return resp;
    }

    public UsuarioResumo paraResumo(Usuario usuario) {
        UsuarioResumo resumo = new UsuarioResumo();
        resumo.setId(usuario.getId());
        resumo.setNome(usuario.getNome());
        resumo.setNomeSocial(usuario.getNomeSocial());
        resumo.setPerfis(usuario.getPerfis());
        return resumo;
    }

    public UsuarioReferencia paraReferencia(Usuario usuario) {
        //boolean strictMapping = true;
        UsuarioReferencia ref = new UsuarioReferencia();
        ref.setId(usuario.getId());
        ref.setNome(usuario.getNome());
        return ref;
    }

    public EnderecoResponse paraEnderecoResponse(Endereco end) {
        EnderecoResponse er = new EnderecoResponse();
        er.setId(end.getId());
        er.setEstado(end.getEstado());
        er.setCidade(end.getCidade());
        er.setBairro(end.getBairro());
        er.setRua(end.getRua());
        er.setNumero(end.getNumero());
        er.setCodigoPostal(end.getCodigoPostal());
        er.setInformacoesAdicionais(end.getInformacoesAdicionais());
        return er;
    }

    public TelefoneResponse paraTelefoneResponse(Telefone tel) {
        TelefoneResponse tr = new TelefoneResponse();
        tr.setId(tel.getId());
        tr.setDdd(tel.getDdd());
        tr.setNumero(tel.getNumero());
        return tr;
    }

    public EmailResponse paraEmailResponse(Email email) {
        EmailResponse er = new EmailResponse();
        er.setId(email.getId());
        er.setEndereco(email.getEndereco());
        return er;
    }

    public DocumentoResponse paraDocumentoResponse(Documento doc) {
        DocumentoResponse dr = new DocumentoResponse();
        dr.setId(doc.getId());
        dr.setTipo(doc.getTipo());
        dr.setNumero(doc.getNumero());
        dr.setDataEmissao(doc.getDataEmissao());
        return dr;
    }

    public CredencialResponse paraCredencialResponse(Credencial cred) {
        CredencialResponse cr = new CredencialResponse();
        cr.setId(cred.getId());
        cr.setCriacao(cred.getCriacao());
        cr.setUltimoAcesso(cred.getUltimoAcesso());
        cr.setInativo(cred.isInativo());
        if (cred instanceof CredencialUsuarioSenha) {
            CredencialUsuarioSenha cus = (CredencialUsuarioSenha) cred;
            cr.setTipo("SENHA");
            cr.setNomeUsuario(cus.getNomeUsuario());
            cr.setSenha(cus.getSenha());
        } else if (cred instanceof CredencialCodigoBarra) {
            CredencialCodigoBarra ccb = (CredencialCodigoBarra) cred;
            cr.setTipo("CODIGO");
            cr.setCodigo(ccb.getCodigo());
        }
        return cr;
    }
}