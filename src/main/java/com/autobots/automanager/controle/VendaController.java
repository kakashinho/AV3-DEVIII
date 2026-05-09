package com.autobots.automanager.controle;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.autobots.automanager.dto.requisicao.VendaRequest;
import com.autobots.automanager.dto.resposta.VendaResponse;
import com.autobots.automanager.entidade.Venda;
import com.autobots.automanager.hateaos.VendaAssembler;
import com.autobots.automanager.mapeador.VendaMapper;
import com.autobots.automanager.servico.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;
    @Autowired
    private VendaMapper vendaMapper;
    @Autowired
    private VendaAssembler vendaAssembler;

    @PostMapping
    public ResponseEntity<VendaResponse> criarVenda(@RequestBody VendaRequest request) {
        Venda venda = vendaMapper.toEntity(request);
        Venda salva = vendaService.criarVenda(venda);
        VendaResponse response = vendaMapper.toResponse(salva);
        response = vendaAssembler.toModel(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<VendaResponse>> listarVendas() {
        List<VendaResponse> responses = vendaService.listarVendas().stream()
                .map(vendaMapper::toResponse)
                .map(vendaAssembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<VendaResponse> obterVenda(@PathVariable Long id) {
        Venda venda = vendaService.obterVenda(id);
        VendaResponse response = vendaMapper.toResponse(venda);
        response = vendaAssembler.toModel(response);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendaResponse> atualizarVenda(
            @PathVariable Long id, @RequestBody VendaRequest request) {
        Venda vendaAtualizada = vendaMapper.toEntity(request);
        Venda venda = vendaService.atualizarVenda(id, vendaAtualizada);
        VendaResponse response = vendaMapper.toResponse(venda);
        response = vendaAssembler.toModel(response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVenda(@PathVariable Long id) {
        vendaService.excluirVenda(id);
        return ResponseEntity.noContent().build();
    }
}