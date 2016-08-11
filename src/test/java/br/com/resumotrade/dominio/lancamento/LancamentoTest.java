package br.com.resumotrade.dominio.lancamento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.resumotrade.Application;
import br.com.resumotrade.dominio.mercado.MercadoId;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@Transactional
public class LancamentoTest {
	
	@Autowired
	private LancamentoRepositorio repositorio;
	
	@Test
	public void novo(){
		
		Lancamento lancamento = new Lancamento(new LancamentoId("3243"), new MercadoId("123"), new Double(1.5), new Double(100));
		
		assertEquals(new LancamentoId("3243"), lancamento.id());
		assertEquals("3243", lancamento.id().id());
		assertEquals(1.5, lancamento.odd(), 0.001);
		assertEquals(100, lancamento.stake(), 0.001);

	}
	
	@Test
	public void alterar(){
		
		Lancamento lancamento = new Lancamento(new LancamentoId("3243"), new MercadoId("123"), new Double(1.5), new Double(100));
		
		lancamento.alterarMercado(new MercadoId("432"));
		lancamento.alterarOdd(new Double(1.7));
		lancamento.alterarStake(new Double(200.50));
		
		assertEquals("432", lancamento.mercadoId().id());
		assertEquals(1.7, lancamento.odd(), 0.001);
		assertEquals(200.50, lancamento.stake(), 0.001);
		
	}
	
	@Test
	public void gravar(){
		
		Lancamento lancamento = new Lancamento(new LancamentoId("3243"), new MercadoId("123"), new Double(1.5), new Double(100));
		repositorio.salvar(lancamento);
		Lancamento aposta = repositorio.buscarLancamentoPorId(lancamento.id());
		
		assertEquals(lancamento.id(), aposta.id());
	}
	
	@Test
	public void remover(){
		
		Lancamento lancamento = new Lancamento(new LancamentoId("3243"), new MercadoId("123"), new Double(1.5), new Double(100));
		repositorio.salvar(lancamento);
		repositorio.remover(lancamento.id());
		
		assertNull(repositorio.buscarLancamentoPorId(lancamento.id()));
		
	}
}
