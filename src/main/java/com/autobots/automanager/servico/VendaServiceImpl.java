package com.autobots.automanager.servico;

import com.autobots.automanager.entidade.Venda;
import com.autobots.automanager.excecao.ResourceNotFoundException;
import com.autobots.automanager.repositorio.RepositorioVenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VendaServiceImpl implements VendaService {

    @Autowired
    private RepositorioVenda repositorioVenda;

    @Override
    public Venda criarVenda(Venda venda) {
        return repositorioVenda.save(venda);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venda> listarVendas() {
        return repositorioVenda.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Venda obterVenda(Long id) {
        return repositorioVenda.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada com id: " + id));
    }

    @Override
    public Venda atualizarVenda(Long id, Venda vendaAtualizada) {
        Venda venda = obterVenda(id);
        venda.setIdentificacao(vendaAtualizada.getIdentificacao());
        if (vendaAtualizada.getMercadorias() != null) {
            venda.getMercadorias().clear();
            venda.getMercadorias().addAll(vendaAtualizada.getMercadorias());
        }
        if (vendaAtualizada.getServicos() != null) {
            venda.getServicos().clear();
            venda.getServicos().addAll(vendaAtualizada.getServicos());
        }
        return repositorioVenda.save(venda);
    }

    @Override
    public void excluirVenda(Long id) {
        Venda venda = obterVenda(id);
        repositorioVenda.delete(venda);
    }
}