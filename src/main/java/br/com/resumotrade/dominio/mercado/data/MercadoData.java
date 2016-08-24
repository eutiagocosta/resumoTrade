package br.com.resumotrade.dominio.mercado.data;

public class MercadoData {
	
	private Long id;
	private String esporte;
	private String descricao;
	
	public MercadoData(Long id, String esporte, String descricao) {
		this.id = id;
		this.esporte = esporte;
		this.descricao = descricao;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MercadoData(){}

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

}
