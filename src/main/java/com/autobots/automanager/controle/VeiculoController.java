package com.autobots.automanager.controle;

import com.autobots.automanager.dto.requisicao.VeiculoRequest;
import com.autobots.automanager.dto.resposta.VeiculoResponse;
import com.autobots.automanager.entidade.Usuario;
import com.autobots.automanager.entidade.Veiculo;
import com.autobots.automanager.excecao.UsuarioNaoEncontradoException;
import com.autobots.automanager.hateaos.VeiculoAssembler;
import com.autobots.automanager.mapeador.VeiculoMapper;
import com.autobots.automanager.repositorio.RepositorioUsuario;
import com.autobots.automanager.servico.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;
    @Autowired
    private VeiculoMapper veiculoMapper;
    @Autowired
    private VeiculoAssembler veiculoAssembler;
    @Autowired
    private RepositorioUsuario usuarioRepo;

    @PostMapping
    public ResponseEntity<VeiculoResponse> criarVeiculo(@RequestBody VeiculoRequest request) {
        Usuario proprietario = usuarioRepo.findById(request.getProprietarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(request.getProprietarioId()));
        Veiculo veiculo = veiculoMapper.toEntity(request, proprietario);
        Veiculo salvo = veiculoService.criarVeiculo(veiculo);
        VeiculoResponse response = veiculoMapper.toResponse(salvo);
        response = veiculoAssembler.toModel(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponse>> listarVeiculos() {
        List<VeiculoResponse> responses = veiculoService.listarVeiculos().stream()
                .map(veiculoMapper::toResponse)
                .map(veiculoAssembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponse> obterVeiculo(@PathVariable Long id) {
        Veiculo veiculo = veiculoService.obterVeiculo(id);
        VeiculoResponse response = veiculoMapper.toResponse(veiculo);
        response = veiculoAssembler.toModel(response);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponse> atualizarVeiculo(
            @PathVariable Long id, @RequestBody VeiculoRequest request) {
        Usuario proprietario = null;
        if (request.getProprietarioId() != null) {
            proprietario = usuarioRepo.findById(request.getProprietarioId())
                    .orElseThrow(() -> new UsuarioNaoEncontradoException(request.getProprietarioId()));
        }
        Veiculo veiculoAtualizado = veiculoMapper.toEntity(request, proprietario);
        Veiculo veiculo = veiculoService.atualizarVeiculo(id, veiculoAtualizado);
        VeiculoResponse response = veiculoMapper.toResponse(veiculo);
        response = veiculoAssembler.toModel(response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id) {
        veiculoService.excluirVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}