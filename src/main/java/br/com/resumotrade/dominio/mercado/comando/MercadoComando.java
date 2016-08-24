package br.com.resumotrade.dominio.mercado.comando;

public class MercadoComando {

	private String mercadoId;
	private String esporte;
	private String descricao;
	
	public MercadoComando(String mercadoId, String esporte, String descricao) {
		setMercadoId(mercadoId);
		setEsporte(esporte);
		setDescricao(descricao);
	}
	
	public MercadoComando(String esporte, String descricao) {
		setEsporte(esporte);
		setDescricao(descricao);
	}
	
	public MercadoComando(){}
	
	public String getEsporte() {
		return esporte;
	}
	public void setEsporte(String esporte) {
		this.esporte = esporte;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMercadoId() {
		return mercadoId;
	}

	public void setMercadoId(String mercadoId) {
		this.mercadoId = mercadoId;
	}
	
}
