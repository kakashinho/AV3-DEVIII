package com.autobots.automanager.servico;

import com.autobots.automanager.entidade.Veiculo;
import com.autobots.automanager.excecao.ResourceNotFoundException;
import com.autobots.automanager.repositorio.RepositorioVeiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VeiculoServiceImpl implements VeiculoService {

    @Autowired
    private RepositorioVeiculo repositorioVeiculo;

    @Override
    public Veiculo criarVeiculo(Veiculo veiculo) {
        return repositorioVeiculo.save(veiculo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Veiculo> listarVeiculos() {
        return repositorioVeiculo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Veiculo obterVeiculo(Long id) {
        return repositorioVeiculo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado com id: " + id));
    }

    @Override
    public Veiculo atualizarVeiculo(Long id, Veiculo veiculoAtualizado) {
        Veiculo veiculo = obterVeiculo(id);
        veiculo.setTipo(veiculoAtualizado.getTipo());
        veiculo.setModelo(veiculoAtualizado.getModelo());
        veiculo.setPlaca(veiculoAtualizado.getPlaca());
        return repositorioVeiculo.save(veiculo);
    }

    @Override
    public void excluirVeiculo(Long id) {
        Veiculo veiculo = obterVeiculo(id);
        if (veiculo.getProprietario() != null) {
            veiculo.getProprietario().getVeiculos().remove(veiculo);
        }
        repositorioVeiculo.delete(veiculo);
    }
}