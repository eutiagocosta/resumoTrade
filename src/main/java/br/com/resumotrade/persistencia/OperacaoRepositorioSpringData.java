package br.com.resumotrade.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.resumotrade.dominio.operacao.Operacao;
import br.com.resumotrade.dominio.operacao.OperacaoId;

public interface OperacaoRepositorioSpringData extends CrudRepository<Operacao, Long>{
	
	Operacao findByOperacaoId(OperacaoId operacaoId);
	
}
