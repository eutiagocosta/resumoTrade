package br.com.resumotrade.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.resumotrade.dominio.aposta.Aposta;
import br.com.resumotrade.dominio.aposta.ApostaId;

public interface ApostaRepositorioSpringData extends CrudRepository<Aposta, Long>{

	Aposta findByApostaId(ApostaId apostaId);

}
