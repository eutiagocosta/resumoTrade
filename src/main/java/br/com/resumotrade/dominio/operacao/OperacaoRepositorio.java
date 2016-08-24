package br.com.resumotrade.dominio.operacao;

import java.util.Set;

import br.com.resumotrade.dominio.mercado.MercadoId;
import br.com.resumotrade.dominio.operacao.aposta.Aposta;

public interface OperacaoRepositorio {

	void salvar(Operacao operacao);

	Operacao buscarOperacaoPorId(OperacaoId id);

	Set<Operacao> buscaOperacoesEmAberto();

	Set<Operacao> buscaOperacoesEncerradas();

	OperacaoId novaIdentidade();

	Aposta buscarApostaPorOperacaoEMercado(Operacao operacao, MercadoId mercadoId);

	Set<Aposta> buscarTodasApostas(Operacao operacao);

}
