package br.com.resumotrade.persistencia;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.resumotrade.dominio.mercado.MercadoId;
import br.com.resumotrade.dominio.operacao.Operacao;
import br.com.resumotrade.dominio.operacao.aposta.Aposta;

public interface ApostaRepositorioSpringData extends JpaRepository<Aposta, Long>{
	
	Aposta findByOperacaoAndMercadoId(Operacao operacao, MercadoId mercadoId);
	
	Set<Aposta> findByOperacao(Operacao operacao);
}
