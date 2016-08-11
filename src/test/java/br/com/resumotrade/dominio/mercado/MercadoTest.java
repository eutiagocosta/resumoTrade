package br.com.resumotrade.dominio.mercado;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.resumotrade.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@Transactional
public class MercadoTest {
	
	@Autowired
	private MercadoRepositorio repositorio;

	@Test
	public void novoMercado(){
		
		Mercado futebol = Mercado.novoFutebol(repositorio.novaIdentidade(), "Over 1.5 HT");
		assertEquals("Over 1.5 HT", futebol.descricao());
	}
	
	@Test
	public void renomear(){
		
		Mercado mercado = Mercado.novoFutebol(repositorio.novaIdentidade(), "Over 1.5 HT");
		mercado.alterarDescricao("Over 2.5 HT");
		
		assertEquals("Over 2.5 HT", mercado.descricao());
		
	}
	
	@Test
	public void gravarNovoMercado(){
		Mercado mercado = Mercado.novoFutebol(repositorio.novaIdentidade(), "Probabilidades Back");
		
		repositorio.salvar(mercado);
		
		Mercado mercadoOld = repositorio.buscarMercadoPorId(mercado.id());
		
		assertEquals(mercadoOld.id(), mercado.id());
	}
	
	@Test
	public void buscarTodosMercadosPorEsporte(){
		
		Mercado mercado1 = Mercado.novoFutebol(repositorio.novaIdentidade(), "Probabilidades Back");
		repositorio.salvar(mercado1);
		Mercado mercado2 = Mercado.novoFutebol(repositorio.novaIdentidade(), "Probabilidades Lay");
		repositorio.salvar(mercado2);
		Mercado mercado3 = Mercado.novoFutebol(repositorio.novaIdentidade(), "Probabilidades Under");
		repositorio.salvar(mercado3);
		Mercado mercado4 = Mercado.novoFutebol(repositorio.novaIdentidade(), "Probabilidades Over");
		repositorio.salvar(mercado4);
		
		Mercado mercado5 = Mercado.novoBasquete(repositorio.novaIdentidade(), "Probabilidades Back");
		repositorio.salvar(mercado5);
		Mercado mercado6 = Mercado.novoBasquete(repositorio.novaIdentidade(), "Probabilidades Back");
		repositorio.salvar(mercado6);
		
		List<Mercado> listaFutebol = repositorio.buscarMercadosPorEsporte(Esporte.buscarEsporte("FUTEBOL"));
		List<Mercado> listaBasquete = repositorio.buscarMercadosPorEsporte(Esporte.buscarEsporte("BASQUETE"));
		
		assertEquals(4, listaFutebol.size());
		assertEquals(2, listaBasquete.size());
	}

}
