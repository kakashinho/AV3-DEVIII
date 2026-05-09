package com.autobots.automanager.servico;

import com.autobots.automanager.entidade.Venda;
import java.util.List;

public interface VendaService {
    Venda criarVenda(Venda venda);
    List<Venda> listarVendas();
    Venda obterVenda(Long id);
    Venda atualizarVenda(Long id, Venda vendaAtualizada);
    void excluirVenda(Long id);
}