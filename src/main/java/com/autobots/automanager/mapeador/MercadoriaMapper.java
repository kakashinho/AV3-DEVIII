package com.autobots.automanager.mapeador;

import org.springframework.stereotype.Component;

import java.util.Date;

import com.autobots.automanager.dto.requisicao.MercadoriaRequest;
import com.autobots.automanager.dto.resposta.MercadoriaResponse;
import com.autobots.automanager.entidade.Mercadoria;

@Component
public class MercadoriaMapper {

    public Mercadoria toEntity(MercadoriaRequest request) {
        if (request == null) return null;

        Mercadoria m = new Mercadoria();
        m.setNome(request.getNome());
        m.setDescricao(request.getDescricao());
        m.setValor(request.getValor());
        m.setQuantidade(request.getQuantidade());
        m.setValidade(request.getValidade());
        m.setFabricacao(request.getFabricao());
        m.setCadastro(new Date());

        m.setDisponivel(request.getQuantidade() > 0);

        return m;
    }

    public MercadoriaResponse toResponse(Mercadoria mercadoria) {
        if (mercadoria == null) return null;

        MercadoriaResponse r = new MercadoriaResponse();
        r.setId(mercadoria.getId());
        r.setNome(mercadoria.getNome());
        r.setDescricao(mercadoria.getDescricao());
        r.setValor(mercadoria.getValor());
        r.setQuantidadeEstoque(mercadoria.getQuantidade());
        r.setDisponivel(mercadoria.isDisponivel());
        r.setValidade(mercadoria.getValidade());
        r.setFabricao(mercadoria.getFabricacao());
        r.setCadastro(mercadoria.getCadastro());
        return r;
    }

    public void atualizarEntidade(Mercadoria entity, MercadoriaRequest request) {
        if (entity == null || request == null) return;

        entity.setNome(request.getNome());
        entity.setDescricao(request.getDescricao());
        entity.setValor(request.getValor());
        entity.setValidade(request.getValidade());
        entity.setFabricacao(request.getFabricao());

        entity.setDisponivel(request.getQuantidade() > 0);
    }
}