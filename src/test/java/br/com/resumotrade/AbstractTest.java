package br.com.resumotrade;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import br.com.resumotrade.dominio.mercado.Esporte;
import br.com.resumotrade.dominio.mercado.Mercado;
import br.com.resumotrade.dominio.mercado.MercadoRepositorio;
import br.com.resumotrade.dominio.mercado.MercadoService;
import br.com.resumotrade.dominio.operacao.Casa;
import br.com.resumotrade.dominio.operacao.Operacao;
import br.com.resumotrade.dominio.operacao.OperacaoRepositorio;
import br.com.resumotrade.dominio.operacao.OperacaoService;
import br.com.resumotrade.dominio.operacao.aposta.ApostaRepositorio;
import br.com.resumotrade.dominio.operacao.aposta.ApostaService;
import br.com.resumotrade.util.Application;
import br.com.resumotrade.util.DataUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@WebAppConfiguration
public abstract class AbstractTest {
	
//	@Value("${local.server.port}")
//  int port;

	public static final String JSON_MEDIA_TYPE = "application/json;charset=UTF-8";

	protected RestTemplate restTemplate;
	
	protected MockMvc mockMvc;
	
	@Autowired
	protected WebApplicationContext wac;
	
	@Autowired
	protected OperacaoRepositorio operacaoRepositorio;
	
	@Autowired
	protected MercadoRepositorio mercadoRepositorio;
	
	@Autowired
	protected ApostaRepositorio apostaRepositorio;
	
	@Autowired
	protected ApostaService apostaServico;
	
	@Autowired
	protected MercadoService mercadoServico;
	
	@Autowired
	protected OperacaoService operacaoServico;
	
	@Autowired
	protected EntityManager em;
	
	@Before
	public void setup(){
		
		restTemplate = new RestTemplate();

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
			.alwaysExpect(status().isOk())
			.alwaysExpect(content().contentType(JSON_MEDIA_TYPE))
			.build();
	}
	
	public Operacao construirOperacao(){
		Operacao operacao = new Operacao(operacaoRepositorio.novaIdentidade(), 
										 Casa.BETFAIR, 
										 Esporte.FUTEBOL, 
										 DataUtil.agora(), 
										 "REAL MADRID", 
										 "BARCELONA");
		
		operacaoRepositorio.salvar(operacao);
		
		return operacao;
	}
	
	protected List<Mercado> construirMercados() {
		
		List<Mercado> lista = new ArrayList<>();
		Mercado backHT = Mercado.novoFutebol(mercadoRepositorio.novaIdentidade(), "BACK HT");
		Mercado layHT = Mercado.novoFutebol(mercadoRepositorio.novaIdentidade(), "LAY HT");
		Mercado Over2 = Mercado.novoFutebol(mercadoRepositorio.novaIdentidade(), "OVER 2.5 HT");
		lista.add(backHT);
		lista.add(layHT);
		lista.add(Over2);
		
		return lista;
	}
	
//	private String buildUrl(String url){
//	return "http://localhost:"+port+"/"+url;
//}
}
