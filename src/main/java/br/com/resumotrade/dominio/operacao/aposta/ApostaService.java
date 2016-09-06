package br.com.resumotrade.dominio.operacao.aposta;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.resumotrade.dominio.mercado.MercadoId;
import br.com.resumotrade.dominio.operacao.Operacao;
import br.com.resumotrade.dominio.operacao.OperacaoId;
import br.com.resumotrade.dominio.operacao.OperacaoRepositorio;

@Service
@Transactional
public class ApostaService {
	
	@Autowired
	private OperacaoRepositorio operacaoRepositorio;
	
	@Autowired
	private ApostaRepositorio apostaRepositorio;

	public ApostaData novaAposta(ApostaComando comando) {
		Operacao operacao = operacaoRepositorio.buscarOperacaoPorId(new OperacaoId(comando.getOperacaoId()));
		Aposta aposta = criarAposta(new MercadoId(comando.getMercadoId()), comando.getOdd(), comando.getStake(), comando.getPotencial(), operacao);
		apostaRepositorio.salvar(aposta);
		return construir(aposta);
	}
	
	private ApostaData construir(Aposta aposta) {
		return new ApostaData(aposta.mercadoId().id(), aposta.odd(), aposta.stake(), aposta.potencial());
	}

	private Aposta criarAposta(MercadoId mercadoId, Double odd, Double stake, Double potencial, Operacao operacao) {
		return operacao.novaAposta(mercadoId, odd, stake, potencial);
	}
	
	public void informarResultadoDaAposta(ApostaComando comando) {
		Operacao operacao = operacaoRepositorio.buscarOperacaoPorId(new OperacaoId(comando.getOperacaoId()));
		Aposta aposta = operacaoRepositorio.buscarApostaPorOperacaoEMercado(operacao, new MercadoId(comando.getMercadoId()));
		aposta.informarResultado(comando.getProfit());
		if (!possuiMaisApostasPendentes(aposta.operacao())){
			operacao.alterarParaEncerrado();
			operacaoRepositorio.salvar(operacao);
		}
		apostaRepositorio.salvar(aposta);
	}
	
	private boolean possuiMaisApostasPendentes(Operacao operacao) {
		
		Set<Aposta> apostas = operacaoRepositorio.buscarTodasApostas(operacao);
		
		for (Aposta aposta : apostas) {
			if (!aposta.liquidada())
				return true;
		}
		return false;
	}

	public void removerAposta(String operacaoId, String mercadoId) {
		Operacao operacao = operacaoRepositorio.buscarOperacaoPorId(new OperacaoId(operacaoId));
		Aposta aposta = operacaoRepositorio.buscarApostaPorOperacaoEMercado(operacao, new MercadoId(mercadoId));
		apostaRepositorio.remover(aposta);
	}

}
