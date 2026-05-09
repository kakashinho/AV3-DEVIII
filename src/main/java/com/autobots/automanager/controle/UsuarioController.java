package com.autobots.automanager.controle;

import com.autobots.automanager.hateaos.UsuarioAssembler;
import com.autobots.automanager.mapeador.UsuarioMapper;
import com.autobots.automanager.servico.UsuarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import com.autobots.automanager.dto.requisicao.UsuarioRequest;
import com.autobots.automanager.dto.requisicao.CredencialRequest;
import com.autobots.automanager.dto.requisicao.TelefoneRequest;
import com.autobots.automanager.dto.resposta.CredencialResponse;
import com.autobots.automanager.dto.resposta.TelefoneResponse;
import com.autobots.automanager.dto.resposta.UsuarioResponse;
import com.autobots.automanager.dto.resposta.UsuarioResumo;
import com.autobots.automanager.entidade.Credencial;
import com.autobots.automanager.entidade.Telefone;
import com.autobots.automanager.entidade.Usuario;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;
    private final UsuarioAssembler assembler;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<CollectionModel<EntityModel<UsuarioResumo>>> listar() {
        List<Usuario> usuarios = service.listarTodos();
        List<EntityModel<UsuarioResumo>> resumos = usuarios.stream()
                .map(u -> {
                    UsuarioResumo resumo = mapper.paraResumo(u);
                    EntityModel<UsuarioResumo> model = EntityModel.of(resumo);
                    assembler.addResumoLinks(model, u.getId());
                    return model;
                })
                .collect(Collectors.toList());
        CollectionModel<EntityModel<UsuarioResumo>> collection = CollectionModel.of(resumos);
        assembler.addCollectionLinks(collection);
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<EntityModel<UsuarioResponse>> buscar(@PathVariable Long id) {
        Usuario usuario = service.buscarPorId(id);
        UsuarioResponse response = mapper.paraResponse(usuario);
        EntityModel<UsuarioResponse> model = EntityModel.of(response);
        assembler.addDetailLinks(model, id);
        return ResponseEntity.ok(model);
    }

    @PostMapping
    public ResponseEntity<EntityModel<UsuarioResponse>> criar(@Valid @RequestBody UsuarioRequest request) {
        Usuario usuario = service.cadastrar(request);
        UsuarioResponse response = mapper.paraResponse(usuario);
        EntityModel<UsuarioResponse> model = EntityModel.of(response);
        assembler.addDetailLinks(model, usuario.getId());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(location).body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioResponse>> atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequest request) {
        Usuario usuario = service.atualizar(id, request);
        UsuarioResponse response = mapper.paraResponse(usuario);
        EntityModel<UsuarioResponse> model = EntityModel.of(response);
        assembler.addDetailLinks(model, id);
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/telefones")
    public ResponseEntity<EntityModel<TelefoneResponse>> adicionarTelefone(@PathVariable Long id, @Valid @RequestBody TelefoneRequest request) {
        Telefone tel = service.adicionarTelefone(id, request);
        TelefoneResponse resp = mapper.paraTelefoneResponse(tel);
        EntityModel<TelefoneResponse> model = EntityModel.of(resp);
        assembler.addSubResourceLinks(model, id, "telefones");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).body(model);
    }

    @PostMapping("/{id}/credenciais")
    public ResponseEntity<EntityModel<CredencialResponse>> adicionarCredencial(
            @PathVariable Long id, @Valid @RequestBody CredencialRequest request) {
        
        Credencial cred = service.adicionarCredencial(id, request);
        CredencialResponse resp = mapper.paraCredencialResponse(cred);
        EntityModel<CredencialResponse> model = EntityModel.of(resp);
        
        // Adiciona os links do HATEOAS
        assembler.addSubResourceLinks(model, id, "credenciais");
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).body(model);
    }
}