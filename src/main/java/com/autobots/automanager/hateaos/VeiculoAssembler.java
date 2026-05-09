package com.autobots.automanager.hateaos;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.autobots.automanager.controle.VeiculoController;
import com.autobots.automanager.dto.resposta.VeiculoResponse;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class VeiculoAssembler implements RepresentationModelAssembler<VeiculoResponse, VeiculoResponse> {

    @Override
    public VeiculoResponse toModel(VeiculoResponse response) {
        response.add(linkTo(methodOn(VeiculoController.class)
                .obterVeiculo(response.getId())).withSelfRel());
        response.add(linkTo(methodOn(VeiculoController.class)
                .listarVeiculos()).withRel("veiculos"));
        response.add(linkTo(methodOn(VeiculoController.class)
                .atualizarVeiculo(response.getId(), null)).withRel("atualizar"));
        response.add(linkTo(methodOn(VeiculoController.class)
                .excluirVeiculo(response.getId())).withRel("excluir"));
        return response;
    }
}