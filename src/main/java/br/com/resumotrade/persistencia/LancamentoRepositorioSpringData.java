package br.com.resumotrade.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.resumotrade.dominio.lancamento.Lancamento;
import br.com.resumotrade.dominio.lancamento.LancamentoId;

public interface LancamentoRepositorioSpringData extends CrudRepository<Lancamento, Long>{

	Lancamento findByLancamentoId(LancamentoId lancamentoId);

}
