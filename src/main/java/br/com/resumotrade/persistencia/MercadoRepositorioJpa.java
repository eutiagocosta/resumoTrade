package br.com.resumotrade.persistencia;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.resumotrade.dominio.mercado.Esporte;
import br.com.resumotrade.dominio.mercado.Mercado;
import br.com.resumotrade.dominio.mercado.MercadoId;
import br.com.resumotrade.dominio.mercado.MercadoRepositorio;

@Repository
@Transactional
public class MercadoRepositorioJpa implements MercadoRepositorio{
	
	@Autowired
	private MercadoRepositorioSpringData repositorio;

	@Override
	public void salvar(Mercado mercado) {
		repositorio.save(mercado);
	}

	@Override
	public void remover(MercadoId mercadoId) {
		Mercado mercado = this.buscarMercadoPorId(mercadoId);
		if (mercado != null){
			repositorio.delete(mercado);
		}
	}

	@Override
	public MercadoId novaIdentidade() {
		return new MercadoId(UUID.randomUUID().toString().toUpperCase());
	}

	@Override
	public Mercado buscarMercadoPorId(MercadoId mercadoId) {
		return repositorio.findByMercadoId(mercadoId);
	}

	@Override
	public List<Mercado> buscarMercadosPorEsporte(Esporte esporte) {
		return repositorio.findByEsporte(esporte);
	}
}
