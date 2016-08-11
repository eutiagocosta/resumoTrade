package br.com.resumotrade.dominio.operacao;

public interface OperacaoRepositorio {

	void salvar(Operacao operacao);

	Operacao buscarOperacaoPorId(OperacaoId id);

	void removerApostaDeUmaOperacao(OperacaoId operacaoId, Long id);

	void informarResultadoDaAposta(OperacaoId operacaoId, Long id, Double profit);

}
