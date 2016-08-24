package br.com.resumotrade.dominio.operacao.aposta;

public class ApostaData {
	
	private String mercadoId;
	private Double odd;
	private Double stake;
	private Double potencial;
	
	public ApostaData(){}
	
	public ApostaData(String mercadoId, Double odd, Double stake, Double potencial) {
		this.mercadoId = mercadoId;
		this.odd = odd;
		this.stake = stake;
		this.potencial = potencial;
	}
	
	public String getMercadoId() {
		return mercadoId;
	}
	public void setMercado(String mercadoId) {
		this.mercadoId = mercadoId;
	}
	public Double getOdd() {
		return odd;
	}
	public void setOdd(Double odd) {
		this.odd = odd;
	}
	public Double getStake() {
		return stake;
	}
	public void setStake(Double stake) {
		this.stake = stake;
	}
	public Double getPotencial() {
		return potencial;
	}
	public void setPotencial(Double potencial) {
		this.potencial = potencial;
	}

}
