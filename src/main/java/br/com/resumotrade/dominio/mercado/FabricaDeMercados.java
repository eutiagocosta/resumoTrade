package br.com.resumotrade.dominio.mercado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FabricaDeMercados {

	@Autowired
	private MercadoService servico;
	
	public Mercado novoMercado(MercadoComando comando) {
		switch (comando.getEsporte().toUpperCase()) {
		case "FUTEBOL":
			return Mercado.novoFutebol(servico.novaIdentidade(), comando.getDescricao());
		case "BASQUETE":
			return Mercado.novoBasquete(servico.novaIdentidade(), comando.getDescricao());
		case "ESPORTES A MOTOR":
			return Mercado.novoMotor(servico.novaIdentidade(), comando.getDescricao());
		default:
			throw new RuntimeException("ERRO AO CRIAR NOVO MERCADO");
		}
	}

}
