package com.autobots.automanager.mapeador;

import com.autobots.automanager.dto.requisicao.VendaRequest;
import com.autobots.automanager.dto.resposta.VendaResponse;
import com.autobots.automanager.entidade.*;
import com.autobots.automanager.repositorio.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

import com.autobots.automanager.excecao.UsuarioNaoEncontradoException;

@Component
public class VendaMapper {

    @Autowired
    private RepositorioUsuario usuarioRepo;
    @Autowired
    private RepositorioVeiculo veiculoRepo;
    @Autowired
    private RepositorioMercadoria mercadoriaRepo;
    @Autowired
    private RepositorioServico servicoRepo;

    public Venda toEntity(VendaRequest request) {
        Venda venda = new Venda();
        venda.setCadastro(new java.util.Date());
        venda.setIdentificacao(request.getIdentificacao());

        Usuario cliente = usuarioRepo.findById(request.getClienteId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(request.getClienteId()));
        venda.setCliente(cliente);

        Usuario funcionario = usuarioRepo.findById(request.getFuncionarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(request.getFuncionarioId()));
        venda.setFuncionario(funcionario);

        Veiculo veiculo = veiculoRepo.findById(request.getVeiculoId())
                .orElseThrow(() -> new com.autobots.automanager.excecao.ResourceNotFoundException("Veículo não encontrado"));
        venda.setVeiculo(veiculo);

        if (request.getMercadoriasIds() != null) {
            venda.setMercadorias(new HashSet<>(mercadoriaRepo.findAllById(request.getMercadoriasIds())));
        }
        if (request.getServicosIds() != null) {
            venda.setServicos(new HashSet<>(servicoRepo.findAllById(request.getServicosIds())));
        }
        return venda;
    }

    public VendaResponse toResponse(Venda venda) {
        VendaResponse response = new VendaResponse();
        response.setId(venda.getId());
        response.setCadastro(venda.getCadastro());
        response.setIdentificacao(venda.getIdentificacao());

        if (venda.getCliente() != null) {
            response.setClienteId(venda.getCliente().getId());
            response.setClienteNome(venda.getCliente().getNome());
        }
        if (venda.getFuncionario() != null) {
            response.setFuncionarioId(venda.getFuncionario().getId());
            response.setFuncionarioNome(venda.getFuncionario().getNome());
        }
        if (venda.getVeiculo() != null) {
            response.setVeiculoId(venda.getVeiculo().getId());
            response.setVeiculoModelo(venda.getVeiculo().getModelo());
        }
        response.setMercadoriasIds(venda.getMercadorias().stream()
                .map(Mercadoria::getId).collect(Collectors.toSet()));
        response.setServicosIds(venda.getServicos().stream()
                .map(Servico::getId).collect(Collectors.toSet()));
        return response;
    }
}