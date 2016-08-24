package br.com.resumotrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.resumotrade.dominio.operacao.OperacaoId;
import br.com.resumotrade.dominio.operacao.OperacaoService;
import br.com.resumotrade.dominio.operacao.aposta.ApostaComando;
import br.com.resumotrade.dominio.operacao.aposta.ApostaService;
import br.com.resumotrade.dominio.operacao.comando.OperacaoComando;

@RestController
@RequestMapping("/operacao")
public class OperacaoController {
	
	@Autowired
	private OperacaoService operacaoServico;
	
	@Autowired
	private ApostaService apostaServico;

	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	@ResponseBody 
	public Retorno novaOperacao(@RequestBody OperacaoComando comando)throws Exception{
		return new Retorno(operacaoServico.novaOperacao(comando));
	}
	
	@RequestMapping(value = "/alterar", method = RequestMethod.POST)
	@ResponseBody 
	public Retorno alterarOperacao(@RequestBody OperacaoComando comando)throws Exception{
		return new Retorno(operacaoServico.alterarOperacao(comando));
	}
	
	@RequestMapping(value = "/buscarPeloId", method = RequestMethod.GET)
	@ResponseBody
	public Retorno buscarOperacaoPorId(@RequestBody String id) throws Exception{
		return new Retorno(operacaoServico.buscarOperacaoPorId(new OperacaoId(id)));
	}
	
	@RequestMapping(value = "/buscar_abertos", method = RequestMethod.GET)
	@ResponseBody
	public Retorno buscarOperacoesEmAberto()throws Exception{
		return new Retorno(operacaoServico.buscaOperacoesEmAberto());
	}
	
	@RequestMapping(value = "/buscar_encerrados", method = RequestMethod.GET)
	@ResponseBody
	public Retorno buscarOperacoesEncerradas() throws Exception{
		return new Retorno(operacaoServico.buscaOperacoesEncerradas());
	}
	
	@RequestMapping(value = "/aposta/nova", method = RequestMethod.POST)
	public Retorno novaAposta(@RequestBody ApostaComando comando){
		return new Retorno(apostaServico.novaAposta(comando));
	}
	
	@RequestMapping(value = "/aposta/informar_resultado", method = RequestMethod.POST)
	public Retorno informarResultado(@RequestBody ApostaComando comando){
		return new Retorno(apostaServico.novaAposta(comando));
	}	

	@RequestMapping(value = "/aposta/remover", method = RequestMethod.DELETE)
	public void removerAposta(@RequestParam(value="operacaoId") String operacaoId, @RequestParam(value="mercadoId")String mercadoId){
		apostaServico.removerAposta(operacaoId, mercadoId);
	}
}
