package br.com.resumotrade.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import br.com.resumotrade.AbstractTest;
import br.com.resumotrade.dominio.mercado.Esporte;
import br.com.resumotrade.dominio.mercado.MercadoData;

public class MercadoControllerTest extends AbstractTest {
	
	@Before
	public void setup(){
		super.setup();
	}
	
	@Test
	public void novo() throws Exception {
		
		String PAYLOAD = "{\"esporte\":\"ESPORTES A MOTOR\","
						 + "\"descricao\":\"GP ITALIA - VENCEDOR\"}";
				
		mockMvc.perform(post("/mercado/novo")
				.content(PAYLOAD)
				.contentType(MediaType.parseMediaType(JSON_MEDIA_TYPE)))
				.andExpect(jsonPath("$..mercadoId").exists());
			
		MercadoData mercado = mercadoServico.buscarMercadosPorEsporte(Esporte.ESPORTES_A_MOTOR).get(0);
		
		assertEquals("ESPORTES_A_MOTOR", mercado.getEsporte().toString());
		assertEquals("GP ITALIA - VENCEDOR", mercado.getDescricao());

	}
	
	@Test
	public void buscarPorEsporte() throws Exception{
		
		mockMvc.perform(get("/mercado/porEsporte")
				.param("esporte", "FUTEBOL")
				.contentType(MediaType.parseMediaType(JSON_MEDIA_TYPE)))
				.andExpect(jsonPath("$.total").value(5))
				.andExpect(jsonPath("$.success").value(true));
	}	

}
