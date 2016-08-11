package br.com.resumotrade.persistencia;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.resumotrade.dominio.aposta.Aposta;
import br.com.resumotrade.dominio.aposta.ApostaId;
import br.com.resumotrade.dominio.aposta.ApostaRepositorio;

@Repository
@Transactional
public class ApostaRepositorioJpa implements ApostaRepositorio{
	
	@Autowired
	private ApostaRepositorioSpringData repositorio;

	@Override
	public Aposta buscarApostaPorId(ApostaId apostaId) {
		return repositorio.findByApostaId(apostaId);
	}

	@Override
	public void salvar(Aposta aposta) {
		repositorio.save(aposta);
	}

	@Override
	public void remover(ApostaId apostaId) {
		Aposta aposta = repositorio.findByApostaId(apostaId);
		if (aposta != null){
			repositorio.delete(aposta);
		}
	}

	@Override
	public ApostaId novaIdentidade() {
		return new ApostaId(UUID.randomUUID().toString().toUpperCase());
	}

	@Override
	public List<Aposta> buscarTodos() {
		return null;
	}

}
