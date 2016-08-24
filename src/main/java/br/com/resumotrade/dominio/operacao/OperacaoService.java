package br.com.resumotrade.dominio.operacao;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.resumotrade.dominio.mercado.Esporte;
import br.com.resumotrade.dominio.operacao.comando.OperacaoComando;
import br.com.resumotrade.dominio.operacao.comando.OperacaoData;

@Service
@Transactional
public class OperacaoService {
	
	@Autowired
	private OperacaoRepositorio repositorio;
	
	public Operacao novaOperacao(OperacaoComando comando) {
		Operacao operacao = new Operacao(repositorio.novaIdentidade(), 
				                         Casa.buscarPorCasa(comando.getCasa()), 
						                 Esporte.buscarEsporte(comando.getEsporte()), 
						                 LocalDate.parse(comando.getData()),
						                 comando.getMandante(), 
						                 comando.getVisitante());
		repositorio.salvar(operacao);
		return operacao;
	}

	public Operacao alterarOperacao(OperacaoComando comando) {
		Operacao operacao = repositorio.buscarOperacaoPorId(new OperacaoId(comando.getOperacaoId()));
		operacao.alterarCasa(Casa.buscarPorCasa(comando.getCasa()));
		operacao.alterarData(comando.getData());
		operacao.alterarEsporte(Esporte.buscarEsporte(comando.getEsporte()));
		operacao.alterarMandante(comando.getMandante());
		operacao.alterarVisitante(comando.getVisitante());
		return operacao;
	}

	public OperacaoData buscarOperacaoPorId(OperacaoId operacaoId) {
		Operacao operacao = repositorio.buscarOperacaoPorId(operacaoId);
		if (operacao == null)
			throw new RuntimeException("Operacao não encontrada.");
		return construir(operacao);
	}

	private OperacaoData construir(Operacao operacao) {
		return new OperacaoData(operacao.operacaoId().id(), 
								operacao.casa().toString(), 
								operacao.esporte().toString(), 
								operacao.data().toString(), 
								operacao.mandante(), 
								operacao.visitante());
	}
	
	public Set<OperacaoData> buscaOperacoesEmAberto() {
		Set<OperacaoData> result = new HashSet<>();
		for (Operacao operacao : repositorio.buscaOperacoesEmAberto()) {
			result.add(construir(operacao));
		}
		return result;
	}

	public Set<OperacaoData> buscaOperacoesEncerradas() {
		Set<OperacaoData> result = new HashSet<>();
		for (Operacao operacao : repositorio.buscaOperacoesEncerradas()) {
			result.add(construir(operacao));
		}
		return result;
	}

}
