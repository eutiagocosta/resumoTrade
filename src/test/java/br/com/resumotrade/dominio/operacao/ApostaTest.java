package br.com.resumotrade.dominio.operacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.resumotrade.AbstractTest;
import br.com.resumotrade.dominio.mercado.MercadoId;
import br.com.resumotrade.dominio.operacao.aposta.Aposta;
import br.com.resumotrade.dominio.operacao.aposta.ApostaComando;

public class ApostaTest extends AbstractTest{
	
	@Test
	public void novaAposta(){
		Operacao operacao = construirOperacao();
		
		apostaServico.novaAposta(new ApostaComando(operacao.operacaoId().id(), 
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

		apostaServico.novaAposta(new ApostaComando(operacao.operacaoId().id(), 
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
		
		apostaServico.novaAposta(new ApostaComando(operacao.operacaoId().id(), 
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
		
		
		apostaServico.informarResultadoDaAposta(new ApostaComando(operacao.operacaoId().id(), 
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
		
		apostaServico.novaAposta(new ApostaComando(operacao.operacaoId().id(), 
				 "1111", 
				 new Double(1.5),
				 new Double(100),
			     new Double(150)));
		
		apostaServico.novaAposta(new ApostaComando(operacao.operacaoId().id(), 
											 "2222", 
											 new Double(1.3),
											 new Double(100),
										     new Double(130)));
		
		apostaServico.novaAposta(new ApostaComando(operacao.operacaoId().id(), 
											 "3333", 
											 new Double(1.2),
											 new Double(100),
										     new Double(120)));
		
		apostaServico.novaAposta(new ApostaComando(operacao.operacaoId().id(), 
											 "4444", 
											 new Double(1.8),
											 new Double(100),
										     new Double(180)));
		
		apostaServico.removerAposta(operacao.operacaoId().id(), "4444");
		
		assertEquals(3, operacaoRepositorio.buscarTodasApostas(operacao).size());
	}

	private void flushAndClear(){
		em.flush();
		em.clear();
	}

}
