package br.com.resumotrade.dominio.mercado;

import java.util.List;

public interface MercadoRepositorio {
	
	void salvar(Mercado mercado);

	void remover(MercadoId mercadoId);

	MercadoId novaIdentidade();;

	Mercado buscarMercadoPorId(MercadoId id);

	List<Mercado> buscarMercadosPorEsporte(Esporte futebol);
	
}
