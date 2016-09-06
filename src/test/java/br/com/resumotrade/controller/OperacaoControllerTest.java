package br.com.resumotrade.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import br.com.resumotrade.AbstractTest;
import br.com.resumotrade.dominio.mercado.Mercado;
import br.com.resumotrade.dominio.operacao.Operacao;

public class OperacaoControllerTest extends AbstractTest{
	
	@Before
	public void setUp(){
		super.setup();
	}
	
	@Test
	public void nova() throws Exception{

		String payload = "{\"casa\":\"BETFAIR\","
						 + "\"esporte\":\"FUTEBOL\","
						 + "\"data\":\"2016-07-26T16:33:13.505-03:00\","
						 + "\"mandante\":\"REAL MADRID\","
						 + "\"visitante\":\"BARCELONA\"}";
		
		mockMvc.perform(post("/operacao/nova")
				.content(payload)
				.contentType(MediaType.parseMediaType(JSON_MEDIA_TYPE)))
				.andExpect(jsonPath("$..operacaoId").exists())
				.andExpect(jsonPath("$.success").value(true));
	}
	
	@Test
	public void alterar() throws Exception{
		
		Operacao operacao = operacaoParaTest();
		
		String payload = "{\n" 
						 + "\"operacaoId\":\""+operacao.operacaoId().id()+"\",\n" 
						 + "\"casa\":\"BETFAIR\","
						 + "\"esporte\":\"FUTEBOL\","
						 + "\"data\":\"2016-07-26T16:33:13.505-03:00\","
						 + "\"mandante\":\"BORUSSIA\","
						 + "\"visitante\":\"BARCELONA\"}";
		
		mockMvc.perform(post("/operacao/alterar")
				.content(payload)
				.contentType(MediaType.parseMediaType(JSON_MEDIA_TYPE)))
				.andExpect(jsonPath("$.success").value(true));
		
		operacao = operacaoRepositorio.buscarOperacaoPorId(operacao.operacaoId());
		
		assertEquals("BORUSSIA", operacao.mandante());
	}
	
	@Test
	public void buscarPorId() throws Exception{
		
		Operacao operacao = operacaoParaTest();
		
		mockMvc.perform(get("/operacao/buscarPeloId")
				.param("operacaoId", operacao.operacaoId().id())
				.contentType(MediaType.parseMediaType(JSON_MEDIA_TYPE)))
				.andExpect(jsonPath("$..operacaoId").value(operacao.operacaoId().id()))
				.andExpect(jsonPath("$.success").value(true));
	}
	
	@Test
	public void novaAposta() throws Exception{
		
		Operacao operacao = operacaoParaTest();
		List<Mercado> mercados = construirMercadosParaTest();
		
		String payload = "{\"operacaoId\":\""+operacao.operacaoId().id()+"\","
						+ "\"mercadoId\":\""+mercados.get(0).mercadoId().id()+"\","
						+ "\"odd\":\"1.9\","
						+ "\"stake\":\"100\","
						+ "\"potencial\":\"190\"}";

		mockMvc.perform(post("/operacao/aposta/nova")
				.content(payload)
				.contentType(MediaType.parseMediaType(JSON_MEDIA_TYPE)))
				.andExpect(jsonPath("$.success").value(true));
	}

	private Operacao operacaoParaTest() {
		return construirOperacao();
	}
	
	private List<Mercado> construirMercadosParaTest(){
		return construirMercados();
	}
	
}
