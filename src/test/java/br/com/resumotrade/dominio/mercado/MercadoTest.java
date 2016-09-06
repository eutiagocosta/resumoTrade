package br.com.resumotrade.dominio.mercado;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import br.com.resumotrade.AbstractTest;

public class MercadoTest extends AbstractTest{

	@Test
	public void novoMercado(){
		
		Mercado futebol = Mercado.novoFutebol(mercadoServico.novaIdentidade(), "Over 1.5 HT");
		assertEquals("Over 1.5 HT", futebol.descricao());
	}
	
	@Test
	public void renomear(){
		
		Mercado mercado = Mercado.novoFutebol(mercadoServico.novaIdentidade(), "Over 1.5 HT");
		mercado.alterarDescricao("Over 2.5 HT");
		
		assertEquals("Over 2.5 HT", mercado.descricao());
		
	}
	
	@Test
	public void gravarNovoMercado(){
		Mercado mercado = Mercado.novoFutebol(mercadoServico.novaIdentidade(), "Probabilidades Back");
		
		mercadoServico.salvar(mercado);
		
		Mercado mercadoOld = mercadoServico.buscarMercadoPorId(mercado.mercadoId());
		
		assertEquals(mercadoOld.mercadoId(), mercado.mercadoId());
	}
	
	@Test
	public void buscarTodosMercadosPorEsporte(){
		
		Mercado mercado1 = Mercado.novoFutebol(mercadoServico.novaIdentidade(), "Probabilidades Back");
		mercadoServico.salvar(mercado1);
		Mercado mercado2 = Mercado.novoFutebol(mercadoServico.novaIdentidade(), "Probabilidades Lay");
		mercadoServico.salvar(mercado2);
		Mercado mercado3 = Mercado.novoFutebol(mercadoServico.novaIdentidade(), "Probabilidades Under");
		mercadoServico.salvar(mercado3);
		Mercado mercado4 = Mercado.novoFutebol(mercadoServico.novaIdentidade(), "Probabilidades Over");
		mercadoServico.salvar(mercado4);
		
		Mercado mercado5 = Mercado.novoBasquete(mercadoServico.novaIdentidade(), "Probabilidades Back");
		mercadoServico.salvar(mercado5);
		Mercado mercado6 = Mercado.novoBasquete(mercadoServico.novaIdentidade(), "Probabilidades Back");
		mercadoServico.salvar(mercado6);
		
		List<MercadoData> listaFutebol = mercadoServico.buscarMercadosPorEsporte(Esporte.buscarEsporte("FUTEBOL"));
		List<MercadoData> listaBasquete = mercadoServico.buscarMercadosPorEsporte(Esporte.buscarEsporte("BASQUETE"));
		
		assertEquals(4, listaFutebol.size());
		assertEquals(2, listaBasquete.size());
	}
	
	@Test
	public void excluirMercado(){
		Mercado mercado = Mercado.novoFutebol(mercadoServico.novaIdentidade(), "Over 1.5 HT");
		mercadoServico.salvar(mercado);
		
		flushAndClear();
		
		mercadoServico.remover(mercado.mercadoId());
		
		mercado = mercadoServico.buscarMercadoPorId(mercado.mercadoId());
		
		assertNull(mercado);
	}
	
	private void flushAndClear(){
		em.flush();
		em.clear();
	}

}
