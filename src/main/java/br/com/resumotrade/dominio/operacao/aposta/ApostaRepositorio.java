package br.com.resumotrade.dominio.operacao.aposta;

public interface ApostaRepositorio {
	
	void salvar(Aposta aposta);

	void remover(Aposta aposta);

}
