package br.com.resumotrade.persistencia;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.resumotrade.dominio.lancamento.Lancamento;
import br.com.resumotrade.dominio.lancamento.LancamentoId;
import br.com.resumotrade.dominio.lancamento.LancamentoRepositorio;

@Repository
@Transactional
public class LancamentoRepositorioJpa implements LancamentoRepositorio{
	
	@Autowired
	private LancamentoRepositorioSpringData repositorio;

	@Override
	public Lancamento buscarLancamentoPorId(LancamentoId lancamentoId) {
		return repositorio.findByLancamentoId(lancamentoId);
	}

	@Override
	public void salvar(Lancamento lancamento) {
		repositorio.save(lancamento);
	}

	@Override
	public void remover(LancamentoId lancamentoId) {
		Lancamento lancamento = repositorio.findByLancamentoId(lancamentoId);
		if (lancamento != null){
			repositorio.delete(lancamento);
		}
	}

	@Override
	public LancamentoId novaIdentidade() {
		return null;
	}

	@Override
	public List<Lancamento> buscarTodos() {
		return null;
	}

}
