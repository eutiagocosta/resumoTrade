package br.com.resumotrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.resumotrade.dominio.mercado.Esporte;
import br.com.resumotrade.dominio.mercado.MercadoComando;
import br.com.resumotrade.dominio.mercado.MercadoId;
import br.com.resumotrade.dominio.mercado.MercadoService;

//@Controller
@RestController
@RequestMapping("/mercado")
public class MercadoController {
	
	@Autowired
	private MercadoService servico;

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	@ResponseBody 
	public Retorno novoMercado(@RequestBody MercadoComando comando) throws Exception{
		return new Retorno(servico.novoMercado(comando));
	}
	
	@RequestMapping(value = "/excluir", method = RequestMethod.POST)
	public void excluirMercado(@RequestBody MercadoComando comando) throws Exception{
		servico.excluirMercado(new MercadoId(comando.getMercadoId()));
	}
	
	@RequestMapping(value = "/porEsporte", method=RequestMethod.GET)
	@ResponseBody 
	public Retorno buscarMercadosPorEsporte(@RequestParam(value="esporte")String esporte) throws Exception{
		return new Retorno(servico.buscarMercadosPorEsporte(Esporte.buscarEsporte(esporte.toUpperCase())));
	}
	
}
