package br.com.resumotrade.persistencia;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.resumotrade.dominio.operacao.Operacao;
import br.com.resumotrade.dominio.operacao.OperacaoId;
import br.com.resumotrade.dominio.operacao.OperacaoRepositorio;
import br.com.resumotrade.dominio.operacao.aposta.Aposta;

@Repository
@Transactional
public class OperacaoRepositorioJpa implements OperacaoRepositorio{

	@Autowired
	private OperacaoRepositorioSpringData repositorio;
	
	@Override
	public void salvar(Operacao operacao) {
		repositorio.save(operacao);
	}

	@Override
	public Operacao buscarOperacaoPorId(OperacaoId operacaoId) {
		return repositorio.findByOperacaoId(operacaoId);
	}

	@Override
	public void removerApostaDeUmaOperacao(OperacaoId operacaoId, Long id) {
		Operacao operacao = this.buscarOperacaoPorId(operacaoId);
		
		for (Aposta aposta : operacao.apostas()) {
			if (aposta.id() == id){
				operacao.apostas().remove(aposta);
				break;
			}
		}
		salvar(operacao);
	}

	@Override
	public void informarResultadoDaAposta(OperacaoId operacaoId, Long id, Double profit) {
		Operacao operacao = this.buscarOperacaoPorId(operacaoId);
		
		for (Aposta aposta : operacao.apostas()) {
			if (aposta.id() == id){
				aposta.informarResultado(profit);
				if (!possuiMaisApostasPendentes(operacao.apostas()))
					operacao.alterarParaEncerrado();
				break;
			}
		}
		salvar(operacao);
	}

	private boolean possuiMaisApostasPendentes(List<Aposta> apostas) {
		for (Aposta aposta : apostas) {
			if (!aposta.liquidada())
				return true;
		}
		return false;
	}

}
