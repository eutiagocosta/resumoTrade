package br.com.resumotrade.dominio.operacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.resumotrade.Application;
import br.com.resumotrade.dominio.mercado.Esporte;
import br.com.resumotrade.dominio.mercado.MercadoId;
import br.com.resumotrade.dominio.operacao.aposta.Aposta;
import br.com.resumotrade.dominio.operacao.aposta.ApostaComando;
import br.com.resumotrade.dominio.operacao.aposta.ApostaService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@Transactional
public class ApostaTest {
	
	@Autowired
	private ApostaService servico;
	
	@Autowired
	private OperacaoRepositorio operacaoRepositorio;
	
	@Autowired
	protected EntityManager em;
	
	@Test
	public void novaAposta(){
		Operacao operacao = construirOperacao();
		operacaoRepositorio.salvar(operacao);
		
		servico.novaAposta(new ApostaComando(operacao.operacaoId().id(), 
											 "1234", 
											 new Double(1.5),
											 new Double(100),
										     new Double(150)));
		
		operacao = operacaoRepositorio.buscarOperacaoPorId(operacao.operacaoId());
		
		Aposta aposta = operacaoRepositorio.buscarApostaPorOperacaoEMercado(operacao, new MercadoId("1234"));
		
		assertEquals(operacao.operacaoId(), aposta.operacao().operacaoId());
		assertEquals("1234", aposta.mercadoId().id());
		assertEquals(1.5, aposta.odd(), 00001);
		assertEquals(100, aposta.stake(), 00001);
		assertEquals(150, aposta.potencial(), 00001);
		assertNull(aposta.profit());
		assertFalse(aposta.liquidada());
	}
	
	@Test
	public void alterarValoresDeUmaAposta(){
		Operacao operacao = construirOperacao();
		operacaoRepositorio.salvar(operacao);

		servico.novaAposta(new ApostaComando(operacao.operacaoId().id(), 
											 "1234", 
											 new Double(1.7),
											 new Double(200),
										     new Double(340)));
		flushAndClear();
		operacao = operacaoRepositorio.buscarOperacaoPorId(operacao.operacaoId());
		Aposta aposta = operacaoRepositorio.buscarApostaPorOperacaoEMercado(operacao, new MercadoId("1234"));
		aposta.alterarMercado(new MercadoId("4321"));
		aposta.alterarOdd(new Double(1.9));
		aposta.alterarStake(new Double(500));
		aposta.alterarPotencial(new Double(950));
		aposta.informarResultado(new Double(950));
		
		aposta = operacaoRepositorio.buscarApostaPorOperacaoEMercado(operacao, aposta.mercadoId());
		
		assertEquals(operacao.operacaoId(), aposta.operacao().operacaoId());
		assertEquals("4321", aposta.mercadoId().id());
		assertEquals(1.9, aposta.odd(), 00001);
		assertEquals(500, aposta.stake(), 00001);
		assertEquals(950, aposta.potencial(), 00001);
		assertEquals(950, aposta.profit(), 00001);
		assertTrue(aposta.liquidada());

	}
	
	@Test
	public void adicionarResultadoDeUmaAposta(){
		
		Operacao operacao = construirOperacao();
		operacaoRepositorio.salvar(operacao);
		
		servico.novaAposta(new ApostaComando(operacao.operacaoId().id(), 
											 "1234", 
											 new Double(1.7),
											 new Double(200),
										     new Double(340)));
		
		flushAndClear();
		
		operacao = operacaoRepositorio.buscarOperacaoPorId(operacao.operacaoId());
		
		Aposta aposta = operacaoRepositorio.buscarApostaPorOperacaoEMercado(operacao, new MercadoId("1234"));
		
		assertEquals(operacao.operacaoId(), aposta.operacao().operacaoId());
		assertEquals("1234", aposta.mercadoId().id());
		assertEquals(1.7, aposta.odd(), 00001);
		assertEquals(200, aposta.stake(), 00001);
		assertEquals(340, aposta.potencial(), 00001);
		assertNull(aposta.profit());
		assertFalse(aposta.liquidada());
		
		
		servico.informarResultadoDaAposta(new ApostaComando(operacao.operacaoId().id(), 
															"1234", 
															new Double(1.7),
															new Double(200),
														    new Double(340),
														    new Double(340),
														    false));
		
		assertEquals(operacao.operacaoId(), aposta.operacao().operacaoId());
		assertEquals("1234", aposta.mercadoId().id());
		assertEquals(1.7, aposta.odd(), 00001);
		assertEquals(200, aposta.stake(), 00001);
		assertEquals(340, aposta.potencial(), 00001);
		assertEquals(340, aposta.profit(), 0.001);
		assertTrue(aposta.liquidada());
	}
	
	@Test
	public void removerApostaDeUmaOperacao(){
		Operacao operacao = construirOperacao();
		operacaoRepositorio.salvar(operacao);
		
		servico.novaAposta(new ApostaComando(operacao.operacaoId().id(), 
				 "1111", 
				 new Double(1.5),
				 new Double(100),
			     new Double(150)));
		
		servico.novaAposta(new ApostaComando(operacao.operacaoId().id(), 
											 "2222", 
											 new Double(1.3),
											 new Double(100),
										     new Double(130)));
		
		servico.novaAposta(new ApostaComando(operacao.operacaoId().id(), 
											 "3333", 
											 new Double(1.2),
											 new Double(100),
										     new Double(120)));
		
		servico.novaAposta(new ApostaComando(operacao.operacaoId().id(), 
											 "4444", 
											 new Double(1.8),
											 new Double(100),
										     new Double(180)));
		
		servico.removerAposta(operacao.operacaoId().id(), "4444");
		
		assertEquals(3, operacaoRepositorio.buscarTodasApostas(operacao).size());
	}
	
	public Operacao construirOperacao(){
		Operacao operacao = new Operacao(operacaoRepositorio.novaIdentidade(), 
										 Casa.BETFAIR, 
										 Esporte.FUTEBOL, 
										 LocalDate.now(), 
										 "REAL MADRID", 
										 "BARCELONA");
		return operacao;
	}

	private void flushAndClear(){
		em.flush();
		em.clear();
	}

}
