package br.com.resumotrade.persistencia;

import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.resumotrade.dominio.mercado.MercadoId;
import br.com.resumotrade.dominio.operacao.Operacao;
import br.com.resumotrade.dominio.operacao.OperacaoId;
import br.com.resumotrade.dominio.operacao.OperacaoRepositorio;
import br.com.resumotrade.dominio.operacao.Status;
import br.com.resumotrade.dominio.operacao.aposta.Aposta;

@Repository
@Transactional
public class OperacaoRepositorioJpa implements OperacaoRepositorio{

	@Autowired
	private OperacaoRepositorioSpringData repositorio;
	
	@Autowired
	private ApostaRepositorioSpringData apostaRepositorio;
	
	@Override
	public void salvar(Operacao operacao) {
		repositorio.save(operacao);
	}

	@Override
	public Operacao buscarOperacaoPorId(OperacaoId operacaoId) {
		return repositorio.findByOperacaoId(operacaoId);
	}

	@Override
	public Set<Operacao> buscaOperacoesEmAberto() {
		return repositorio.findByStatus(Status.ABERTO);
	}

	@Override
	public Set<Operacao> buscaOperacoesEncerradas() {
		return repositorio.findByStatus(Status.ENCERRADO);
	}

	@Override
	public OperacaoId novaIdentidade() {
		return new OperacaoId(UUID.randomUUID().toString().toUpperCase());
	}

	@Override
	public Aposta buscarApostaPorOperacaoEMercado(Operacao operacao, MercadoId mercadoId) {
		return apostaRepositorio.findByOperacaoAndMercadoId(operacao, mercadoId);
	}

	@Override
	public Set<Aposta> buscarTodasApostas(Operacao operacao) {
		return apostaRepositorio.findByOperacao(operacao);
	}

}
