package br.com.resumotrade.dominio.aposta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.resumotrade.Application;
import br.com.resumotrade.dominio.aposta.Aposta;
import br.com.resumotrade.dominio.aposta.ApostaId;
import br.com.resumotrade.dominio.aposta.ApostaRepositorio;
import br.com.resumotrade.dominio.mercado.MercadoId;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@Transactional
public class ApostaTest {
	
	@Autowired
	private ApostaRepositorio repositorio;
	
	@Test
	public void novo(){
		
		Aposta aposta = new Aposta(new ApostaId("3243"), new MercadoId("123"), new Double(1.5), new Double(100));
		
		assertEquals(new ApostaId("3243"), aposta.id());
		assertEquals("3243", aposta.id().id());
		assertEquals(1.5, aposta.odd(), 0.001);
		assertEquals(100, aposta.stake(), 0.001);

	}
	
	@Test
	public void alterar(){
		
		Aposta aposta = new Aposta(new ApostaId("3243"), new MercadoId("123"), new Double(1.5), new Double(100));
		
		aposta.alterarMercado(new MercadoId("432"));
		aposta.alterarOdd(new Double(1.7));
		aposta.alterarStake(new Double(200.50));
		
		assertEquals("432", aposta.mercadoId().id());
		assertEquals(1.7, aposta.odd(), 0.001);
		assertEquals(200.50, aposta.stake(), 0.001);
		
	}
	
	@Test
	public void gravar(){
		
		Aposta novaAposta = new Aposta(new ApostaId("3243"), new MercadoId("123"), new Double(1.5), new Double(100));
		repositorio.salvar(novaAposta);
		Aposta apostaObtida = repositorio.buscarApostaPorId(novaAposta.id());
		
		assertEquals(novaAposta.id(), apostaObtida.id());
	}
	
	@Test
	public void remover(){
		
		Aposta aposta = new Aposta(new ApostaId("3243"), new MercadoId("123"), new Double(1.5), new Double(100));
		repositorio.salvar(aposta);
		repositorio.remover(aposta.id());
		
		assertNull(repositorio.buscarApostaPorId(aposta.id()));
		
	}
}
