package br.com.resumotrade.dominio.mercado;

public class MercadoData {
	
	private String mercadoId;
	private Long id;
	private String esporte;
	private String descricao;
	
	public MercadoData(Long id, String esporte, String descricao) {
		setId(id);
		setEsporte(esporte);
		setDescricao(descricao);
	}
	
	public MercadoData(String mercadoId, Long id, String esporte, String descricao) {
		setMercadoId(mercadoId);
		setId(id);
		setEsporte(esporte);
		setDescricao(descricao);
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

	public String getMercadoId() {
		return mercadoId;
	}

	public void setMercadoId(String mercadoId) {
		this.mercadoId = mercadoId;
	}

}
