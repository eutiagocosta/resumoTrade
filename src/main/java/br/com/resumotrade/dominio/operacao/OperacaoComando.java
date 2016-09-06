package br.com.resumotrade.dominio.operacao;

import java.util.Date;

public class OperacaoComando {

	private String operacaoId;
	private String casa;
	private String esporte;
	private Date data;
	private String mandante;
	private String visitante;
	
	public OperacaoComando(){}

	/*public OperacaoComando(String operacaoId, String casa, String esporte, Date data, String mandante, String visitante) {
		setOperacaoId(operacaoId);
		setCasa(casa);
		setEsporte(esporte);
		setData(data);
		setMandante(mandante);
		setVisitante(visitante);
	}*/
	
	public OperacaoComando(String casa, String esporte, Date data, String mandante, String visitante) {
		super();
		setCasa(casa);
		setEsporte(esporte);
		setData(data);
		setMandante(mandante);
		setVisitante(visitante);
	}

	public String getCasa() {
		return casa;
	}

	public void setCasa(String casa) {
		this.casa = casa;
	}

	public String getEsporte() {
		return esporte;
	}

	public void setEsporte(String esporte) {
		this.esporte = esporte;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getMandante() {
		return mandante;
	}

	public void setMandante(String mandante) {
		this.mandante = mandante;
	}

	public String getVisitante() {
		return visitante;
	}

	public void setVisitante(String visitante) {
		this.visitante = visitante;
	}

	public String getOperacaoId() {
		return operacaoId;
	}

	public void setOperacaoId(String operacaoId) {
		this.operacaoId = operacaoId;
	}
	
}
