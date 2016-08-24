	package br.com.resumotrade.dominio.operacao.aposta;

public class ApostaComando {
	
	private String operacaoId;
	private String mercadoId;
	private Double odd;
	private Double stake;
	private Double potencial;
	private Double profit;
	private boolean liquidada;
	
	public ApostaComando(String operacaoId, String mercadoId, Double odd, Double stake, Double potencial, Double profit,
			boolean liquidada) {
		
		setOperacaoId(operacaoId);
		setMercadoId(mercadoId);
		setOdd(odd);
		setStake(stake);
		setPotencial(potencial);
		setProfit(profit);
		setLiquidada(liquidada);
	}
	
	public ApostaComando(){}
	
	public ApostaComando(String operacaoId, String mercadoId, Double odd, Double stake, Double potencial) {
		setOperacaoId(operacaoId);
		setMercadoId(mercadoId);
		setOdd(odd);
		setStake(stake);
		setPotencial(potencial);
	}

	public String getMercadoId() {
		return mercadoId;
	}
	public void setMercadoId(String mercadoId) {
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
	public Double getProfit() {
		return profit;
	}
	public void setProfit(Double profit) {
		this.profit = profit;
	}
	public boolean isLiquidada() {
		return liquidada;
	}
	public void setLiquidada(boolean liquidada) {
		this.liquidada = liquidada;
	}

	public String getOperacaoId() {
		return operacaoId;
	}

	public void setOperacaoId(String operacaoId) {
		this.operacaoId = operacaoId;
	}
}
