package br.com.resumotrade.persistencia;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.resumotrade.dominio.operacao.aposta.Aposta;
import br.com.resumotrade.dominio.operacao.aposta.ApostaRepositorio;

@Repository
@Transactional
public class ApostaRepositorioJpa implements ApostaRepositorio {

	@Autowired
	private ApostaRepositorioSpringData repositorio;
	
	@Override
	public void salvar(Aposta aposta) {
		repositorio.save(aposta);
	}

	@Override
	public void remover(Aposta aposta) {
		repositorio.delete(aposta);
	}

}
