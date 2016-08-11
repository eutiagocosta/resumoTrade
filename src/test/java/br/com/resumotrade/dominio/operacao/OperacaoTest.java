package br.com.resumotrade.dominio.operacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.resumotrade.Application;
import br.com.resumotrade.dominio.mercado.Esporte;
import br.com.resumotrade.dominio.mercado.MercadoId;
import br.com.resumotrade.dominio.operacao.aposta.Aposta;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@Transactional
public class OperacaoTest {
	
	@Autowired
	private OperacaoRepositorio repositorio;
	
	private Operacao operacao;
	
	@Before
	public void setUp(){
		
		this.operacao = new Operacao(Casa.BETFAIR, Esporte.FUTEBOL, LocalDate.now(), "REAL MADRID", "BARCELONA");
	    this.operacao.novaAposta(new Aposta(new MercadoId("123"), new Double(1.5), new Double(100), new Double(150)));
	    this.operacao.novaAposta(new Aposta(new MercadoId("1343"), new Double(1.5), new Double(100), new Double(150)));
	    this.operacao.novaAposta(new Aposta(new MercadoId("333"), new Double(1.5), new Double(100), new Double(150)));
	    this.operacao.novaAposta(new Aposta(new MercadoId("99878"), new Double(1.5), new Double(100),new Double(150)));
	}

	@Test
	public void novaOperacao(){
		
		assertEquals("REAL MADRID", operacao.mandante());
		assertEquals("BARCELONA", operacao.visitante());
	}
	
	@Test
	public void editarOperacao(){
	
		operacao.alterarVisitante("PSG");
		operacao.alterarMandante("ROMA");
		operacao.alterarEsporte(Esporte.buscarEsporte("BASQUETE"));
		operacao.alterarCasa(Casa.buscarPorCasa("BET365"));
		//operacao.alterarData();
		
		assertEquals("PSG", operacao.visitante());
		assertEquals("ROMA", operacao.mandante());
		assertEquals("BASQUETE", operacao.esporte().toString());
		assertEquals("BET365", operacao.casa().toString());

	}
	
	@Test
	public void salvarOperacao(){
	    
	    repositorio.salvar(operacao);
	    Operacao resgatada = repositorio.buscarOperacaoPorId(operacao.id());
	    assertEquals(resgatada.id(), operacao.id());
	}
	
	@Test
	public void removerApostaDeUmaOperacao(){
		
		repositorio.salvar(operacao);
		Aposta aposta = operacao.apostas().get(1);
		repositorio.removerApostaDeUmaOperacao(operacao.id(), aposta.id());
		assertEquals(3, operacao.apostas().size());
		
	}
	
	@Test
	public void adicionarResultadoDeUmaAposta(){
		
		repositorio.salvar(operacao);
		Aposta aposta = operacao.apostas().get(0);
		repositorio.informarResultadoDaAposta(operacao.id(), aposta.id(), new Double(150));
		assertEquals(150, operacao.apostas().get(0).profit(), 0.001);
		assertTrue(operacao.apostas().get(0).liquidada());
	}
	
	@Test
	public void alterarStatusOperacaoParaEncerrado(){
		
		repositorio.salvar(operacao);
		
//		Aposta aposta1 = operacao.apostas().get(0);
//		repositorio.informarResultadoDaAposta(operacao.id(), aposta1.id(), new Double(150));
//
//		Aposta aposta2 = operacao.apostas().get(1);
//		repositorio.informarResultadoDaAposta(operacao.id(), aposta2.id(), new Double(150));
//		
//		Aposta aposta3 = operacao.apostas().get(2);
//		repositorio.informarResultadoDaAposta(operacao.id(), aposta3.id(), new Double(150));
//		
//		Aposta aposta4 = operacao.apostas().get(3);
//		repositorio.informarResultadoDaAposta(operacao.id(), aposta4.id(), new Double(150));
		
		for (Aposta ap : operacao.apostas()) {
			repositorio.informarResultadoDaAposta(operacao.id(), ap.id(), new Double(150));
		}
		//assertEquals(Status.ENCERRADO, operacao.status());
		
	}
}
