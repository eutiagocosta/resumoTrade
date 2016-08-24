package br.com.resumotrade.persistencia;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import br.com.resumotrade.dominio.operacao.Operacao;
import br.com.resumotrade.dominio.operacao.OperacaoId;
import br.com.resumotrade.dominio.operacao.Status;

public interface OperacaoRepositorioSpringData extends CrudRepository<Operacao, Long>{
	
	Operacao findByOperacaoId(OperacaoId operacaoId);

	Set<Operacao> findByStatus(Status status);
	
}
