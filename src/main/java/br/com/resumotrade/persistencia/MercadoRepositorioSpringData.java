package br.com.resumotrade.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.resumotrade.dominio.mercado.Esporte;
import br.com.resumotrade.dominio.mercado.Mercado;
import br.com.resumotrade.dominio.mercado.MercadoId;

public interface MercadoRepositorioSpringData extends CrudRepository<Mercado, Long> {
	
	Mercado findByMercadoId(MercadoId mercadoId);
	List<Mercado> findByEsporte(Esporte esporte);
	
}
