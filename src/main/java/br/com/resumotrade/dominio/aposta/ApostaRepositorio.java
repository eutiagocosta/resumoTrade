package br.com.resumotrade.dominio.aposta;

import java.util.List;

public interface ApostaRepositorio {

	Aposta buscarApostaPorId(ApostaId apostaId);
	
	void salvar(Aposta  aposta);
	
	void remover(ApostaId apostaId);
	
	ApostaId novaIdentidade();

	List<Aposta> buscarTodos();
	
}
