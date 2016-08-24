package br.com.resumotrade.dominio.mercado;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.resumotrade.Application;
import br.com.resumotrade.dominio.mercado.data.MercadoData;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@Transactional
public class MercadoTest {
	
	@Autowired
	private MercadoService servico;
	
	@Autowired
	protected EntityManager em;

	@Test
	public void novoMercado(){
		
		Mercado futebol = Mercado.novoFutebol(servico.novaIdentidade(), "Over 1.5 HT");
		assertEquals("Over 1.5 HT", futebol.descricao());
	}
	
	@Test
	public void renomear(){
		
		Mercado mercado = Mercado.novoFutebol(servico.novaIdentidade(), "Over 1.5 HT");
		mercado.alterarDescricao("Over 2.5 HT");
		
		assertEquals("Over 2.5 HT", mercado.descricao());
		
	}
	
	@Test
	public void gravarNovoMercado(){
		Mercado mercado = Mercado.novoFutebol(servico.novaIdentidade(), "Probabilidades Back");
		
		servico.salvar(mercado);
		
		Mercado mercadoOld = servico.buscarMercadoPorId(mercado.mercadoId());
		
		assertEquals(mercadoOld.mercadoId(), mercado.mercadoId());
	}
	
	@Test
	public void buscarTodosMercadosPorEsporte(){
		
		Mercado mercado1 = Mercado.novoFutebol(servico.novaIdentidade(), "Probabilidades Back");
		servico.salvar(mercado1);
		Mercado mercado2 = Mercado.novoFutebol(servico.novaIdentidade(), "Probabilidades Lay");
		servico.salvar(mercado2);
		Mercado mercado3 = Mercado.novoFutebol(servico.novaIdentidade(), "Probabilidades Under");
		servico.salvar(mercado3);
		Mercado mercado4 = Mercado.novoFutebol(servico.novaIdentidade(), "Probabilidades Over");
		servico.salvar(mercado4);
		
		Mercado mercado5 = Mercado.novoBasquete(servico.novaIdentidade(), "Probabilidades Back");
		servico.salvar(mercado5);
		Mercado mercado6 = Mercado.novoBasquete(servico.novaIdentidade(), "Probabilidades Back");
		servico.salvar(mercado6);
		
		List<MercadoData> listaFutebol = servico.buscarMercadosPorEsporte(Esporte.buscarEsporte("FUTEBOL"));
		List<MercadoData> listaBasquete = servico.buscarMercadosPorEsporte(Esporte.buscarEsporte("BASQUETE"));
		
		assertEquals(4, listaFutebol.size());
		assertEquals(2, listaBasquete.size());
	}
	
	@Test
	public void excluirMercado(){
		Mercado mercado = Mercado.novoFutebol(servico.novaIdentidade(), "Over 1.5 HT");
		servico.salvar(mercado);
		
		flushAndClear();
		
		servico.remover(mercado.mercadoId());
		
		mercado = servico.buscarMercadoPorId(mercado.mercadoId());
		
		assertNull(mercado);
	}
	
	private void flushAndClear(){
		em.flush();
		em.clear();
	}

}
