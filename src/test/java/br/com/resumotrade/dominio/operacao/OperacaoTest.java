package br.com.resumotrade.dominio.operacao;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.resumotrade.Application;
import br.com.resumotrade.dominio.mercado.Esporte;
import br.com.resumotrade.dominio.operacao.aposta.ApostaComando;
import br.com.resumotrade.dominio.operacao.aposta.ApostaService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@Transactional
public class OperacaoTest {
	
	@Autowired
	private ApostaService apostaServico;
	
	@Autowired
	private OperacaoRepositorio repositorio;

	@Test
	public void novaOperacao(){
		Operacao operacao = constroiOperacao();
		repositorio.salvar(operacao);
		
		operacao = repositorio.buscarOperacaoPorId(operacao.operacaoId());
		
		assertEquals("BETFAIR", operacao.casa().toString());
		assertEquals("FUTEBOL", operacao.esporte().toString());
		assertEquals("2016-08-24", operacao.data().toString());
		assertEquals("REAL MADRID", operacao.mandante());
		assertEquals("BARCELONA", operacao.visitante());
	}
	
	@Test
	public void editarOperacao(){
		
		Operacao operacao = constroiOperacao();
		
		operacao.alterarMandante("ROMA");
		operacao.alterarVisitante("PSG");
		operacao.alterarEsporte(Esporte.buscarEsporte("BASQUETE"));
		operacao.alterarCasa(Casa.buscarPorCasa("BET365"));
		operacao.alterarData("2016-08-15");
		
		repositorio.salvar(operacao);
		
		operacao = repositorio.buscarOperacaoPorId(operacao.operacaoId());

		assertEquals("PSG", operacao.visitante());
		assertEquals("ROMA", operacao.mandante());
		assertEquals("BASQUETE", operacao.esporte().toString());
		assertEquals("BET365", operacao.casa().toString());
		assertEquals("2016-08-15", operacao.data().toString());

	}

	@Test
	public void alterarStatusOperacaoParaEncerrado(){
		
		Operacao operacao = constroiOperacao();
		repositorio.salvar(operacao);
		
		apostaServico.novaAposta(new ApostaComando(operacao.operacaoId().id(), "1111", new Double(1.5),new Double(100),new Double(150)));
		apostaServico.novaAposta(new ApostaComando(operacao.operacaoId().id(), "2222", new Double(1.3),new Double(100),new Double(130)));
		apostaServico.novaAposta(new ApostaComando(operacao.operacaoId().id(), "3333", new Double(1.2),new Double(100),new Double(120)));
		apostaServico.novaAposta(new ApostaComando(operacao.operacaoId().id(), "4444", new Double(1.8),new Double(100),new Double(180)));

		apostaServico.informarResultadoDaAposta(new ApostaComando(operacao.operacaoId().id(), 
																  "1111", 
																  new Double(1.5),
																  new Double(100),
																  new Double(150),
																  new Double(150),
																  false));
		apostaServico.informarResultadoDaAposta(new ApostaComando(operacao.operacaoId().id(), 
																  "2222", 
																  new Double(1.3),
																  new Double(100),
																  new Double(130),
																  new Double(100),
																  false));
		apostaServico.informarResultadoDaAposta(new ApostaComando(operacao.operacaoId().id(), 
																  "3333", 
																  new Double(1.2),
																  new Double(100),
																  new Double(120),
																  new Double(-40),
																  false));
		apostaServico.informarResultadoDaAposta(new ApostaComando(operacao.operacaoId().id(), 
																  "4444", 
																  new Double(1.8),
																  new Double(100),
																  new Double(180),
																  new Double(-150),
																  false));
								
		assertEquals(Status.ENCERRADO, operacao.status());
	}
	
	@Test
	public void obterOperacoesEmAberto(){
		construirOperacoesEmAberto();
		Set<Operacao> operacoes = repositorio.buscaOperacoesEmAberto();
		assertEquals(3, operacoes.size());
	}

	@Test
	public void obterOperacoesEncerradas(){
		construirOperacoesEncerradas();
		Set<Operacao> operacoes = repositorio.buscaOperacoesEncerradas();
		assertEquals(3, operacoes.size());
	}
	
	public Operacao constroiOperacao(){
		Operacao operacao = new Operacao(repositorio.novaIdentidade(), 
										 Casa.BETFAIR, 
										 Esporte.FUTEBOL, 
										 LocalDate.now(), 
										 "REAL MADRID", 
										 "BARCELONA");
		return operacao;
	}
	
	private void construirOperacoesEmAberto() {
		
		Operacao operacao1 = constroiOperacao();
		repositorio.salvar(operacao1);
		apostaServico.novaAposta(new ApostaComando(operacao1.operacaoId().id(), "1111", new Double(1.5),new Double(100),new Double(150)));
		apostaServico.novaAposta(new ApostaComando(operacao1.operacaoId().id(), "2222", new Double(1.3),new Double(100),new Double(130)));
		apostaServico.novaAposta(new ApostaComando(operacao1.operacaoId().id(), "3333", new Double(1.2),new Double(100),new Double(120)));
		
		apostaServico.informarResultadoDaAposta(new ApostaComando(operacao1.operacaoId().id(), 
																  "1111", 
																  new Double(1.5),
																  new Double(100),
																  new Double(150),
																  new Double(150),
																  false));
		apostaServico.informarResultadoDaAposta(new ApostaComando(operacao1.operacaoId().id(), 
																  "2222", 
																  new Double(1.3),
																  new Double(100),
																  new Double(130),
																  new Double(-20),
																  false));
		
		Operacao operacao2 = constroiOperacao();
		repositorio.salvar(operacao2);
		
		apostaServico.novaAposta(new ApostaComando(operacao2.operacaoId().id(), "1111", new Double(1.5),new Double(100),new Double(150)));
		apostaServico.novaAposta(new ApostaComando(operacao2.operacaoId().id(), "5555", new Double(1.3),new Double(100),new Double(130)));
		apostaServico.novaAposta(new ApostaComando(operacao2.operacaoId().id(), "3333", new Double(1.2),new Double(100),new Double(120)));
		
		apostaServico.informarResultadoDaAposta(new ApostaComando(operacao2.operacaoId().id(), 
																  "1111", 
																  new Double(1.5),
																  new Double(100),
																  new Double(150),
																  new Double(110),
																  false));
		
		Operacao operacao3 = constroiOperacao();
		repositorio.salvar(operacao3);
		apostaServico.novaAposta(new ApostaComando(operacao3.operacaoId().id(), "1111", new Double(1.5),new Double(100),new Double(150)));
		apostaServico.novaAposta(new ApostaComando(operacao3.operacaoId().id(), "9999", new Double(1.3),new Double(100),new Double(130)));
		apostaServico.novaAposta(new ApostaComando(operacao2.operacaoId().id(), "7777", new Double(1.2),new Double(100),new Double(120)));
	}
	
	private void construirOperacoesEncerradas() {
		
		Operacao operacao1 = constroiOperacao();
		repositorio.salvar(operacao1);
		apostaServico.novaAposta(new ApostaComando(operacao1.operacaoId().id(), 
												   "1111", 
												   new Double(1.5),
												   new Double(100),
											       new Double(150)));
		apostaServico.novaAposta(new ApostaComando(operacao1.operacaoId().id(), 
												   "2222", 
												   new Double(1.3),
												   new Double(100),
											       new Double(130)));
		apostaServico.novaAposta(new ApostaComando(operacao1.operacaoId().id(), 
												   "3333", 
												   new Double(1.2),
												   new Double(100),
											       new Double(120)));
		
		apostaServico.informarResultadoDaAposta(new ApostaComando(operacao1.operacaoId().id(), 
																  "1111", 
																  new Double(1.5),
																  new Double(100),
																  new Double(150),
																  new Double(150),
																  false));
		apostaServico.informarResultadoDaAposta(new ApostaComando(operacao1.operacaoId().id(), 
																  "2222", 
																  new Double(1.3),
																  new Double(100),
																  new Double(130),
																  new Double(100),
																  false));
		apostaServico.informarResultadoDaAposta(new ApostaComando(operacao1.operacaoId().id(), 
																  "3333", 
																  new Double(1.2),
																  new Double(100),
																  new Double(120),
																  new Double(-55),
																  false));
		
		Operacao operacao2 = constroiOperacao();
		repositorio.salvar(operacao2);
		apostaServico.novaAposta(new ApostaComando(operacao2.operacaoId().id(), 
												   "1111", 
												   new Double(1.5),
												   new Double(100),
											       new Double(150)));
		apostaServico.novaAposta(new ApostaComando(operacao2.operacaoId().id(), 
												   "5555", 
												   new Double(1.3),
												   new Double(100),
											       new Double(130)));
		apostaServico.novaAposta(new ApostaComando(operacao2.operacaoId().id(), 
												   "3333", 
												   new Double(1.2),
												   new Double(100),
											       new Double(120)));

		apostaServico.informarResultadoDaAposta(new ApostaComando(operacao2.operacaoId().id(), 
																  "1111", 
																  new Double(1.5),
																  new Double(100),
																  new Double(150),
																  new Double(-50),
																  false));
		apostaServico.informarResultadoDaAposta(new ApostaComando(operacao2.operacaoId().id(), 
																  "5555", 
																  new Double(1.3),
																  new Double(100),
																  new Double(130),
																  new Double(-30),
																  false));
		apostaServico.informarResultadoDaAposta(new ApostaComando(operacao2.operacaoId().id(), 
																  "3333", 
																  new Double(1.2),
																  new Double(100),
																  new Double(120),
																  new Double(-30),
																  false));

		Operacao operacao3 = constroiOperacao();
		repositorio.salvar(operacao3);
		apostaServico.novaAposta(new ApostaComando(operacao3.operacaoId().id(), 
												   "1111", 
												   new Double(1.5),
												   new Double(100),
											       new Double(150)));
		apostaServico.novaAposta(new ApostaComando(operacao3.operacaoId().id(), 
												   "9999", 
												   new Double(1.3),
												   new Double(100),
											       new Double(130)));
		apostaServico.novaAposta(new ApostaComando(operacao3.operacaoId().id(), 
												   "7777", 
												   new Double(1.2),
												   new Double(100),
											       new Double(120)));

		apostaServico.informarResultadoDaAposta(new ApostaComando(operacao3.operacaoId().id(), 
																  "1111", 
																  new Double(1.5),
																  new Double(100),
																  new Double(150),
																  new Double(-50),
																  false));
		apostaServico.informarResultadoDaAposta(new ApostaComando(operacao3.operacaoId().id(), 
																  "9999", 
																  new Double(1.3),
																  new Double(100),
																  new Double(130),
																  new Double(-50),
																  false));
		apostaServico.informarResultadoDaAposta(new ApostaComando(operacao3.operacaoId().id(), 
																  "7777", 
																  new Double(1.2),
																  new Double(100),
																  new Double(120),
																  new Double(-50),
																  false));

	}
}
