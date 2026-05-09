package com.autobots.automanager.hateaos;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;
import com.autobots.automanager.controle.UsuarioController;

@Component
public class UsuarioAssembler {

    public void addResumoLinks(EntityModel<?> model, Long id) {
        model.add(linkTo(methodOn(UsuarioController.class).buscar(id)).withRel("detalhe"));
        model.add(linkTo(methodOn(UsuarioController.class).listar()).withRel("todos"));
    }

    public void addDetailLinks(EntityModel<?> model, Long id) {
        model.add(linkTo(methodOn(UsuarioController.class).buscar(id)).withSelfRel());
        model.add(linkTo(methodOn(UsuarioController.class).listar()).withRel("todos"));
        model.add(linkTo(methodOn(UsuarioController.class).atualizar(id, null)).withRel("editar"));
        model.add(linkTo(methodOn(UsuarioController.class).remover(id)).withRel("remover"));
        model.add(linkTo(UsuarioController.class).slash(id).slash("telefones").withRel("adicionar-telefone"));
        model.add(linkTo(UsuarioController.class).slash(id).slash("emails").withRel("adicionar-email"));
        model.add(linkTo(UsuarioController.class).slash(id).slash("documentos").withRel("adicionar-documento"));
        model.add(linkTo(UsuarioController.class).slash(id).slash("credenciais").withRel("adicionar-credencial"));
    }

    public void addCollectionLinks(CollectionModel<?> model) {
        model.add(linkTo(methodOn(UsuarioController.class).listar()).withSelfRel());
        model.add(linkTo(UsuarioController.class).withRel("criar"));
    }

    public void addSubResourceLinks(EntityModel<?> model, Long usuarioId, String tipo) {
        model.add(linkTo(methodOn(UsuarioController.class).buscar(usuarioId)).withRel("usuario"));
        model.add(linkTo(UsuarioController.class).slash(usuarioId).slash(tipo).withSelfRel());
    }
}