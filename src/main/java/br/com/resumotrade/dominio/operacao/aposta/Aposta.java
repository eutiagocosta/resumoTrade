package br.com.resumotrade.dominio.operacao.aposta;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.resumotrade.dominio.mercado.MercadoId;
import br.com.resumotrade.dominio.operacao.Operacao;

@Entity
@SequenceGenerator(name="SEQ", sequenceName="SQ_APOSTA")
@Table(name="APOSTA")
public class Aposta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ")
	private Long id;
	
	@Embedded
	private MercadoId mercadoId;
	
	private Double odd;
	private Double stake;
	private Double potencial;
	private Double profit;
	private boolean liquidada;
	
	@ManyToOne
    @JoinColumn(name="ID_OPERACAO")
	private Operacao operacao;
	
	public Aposta(MercadoId mercadoId, Double odd, Double stake, Double potencial, Operacao operacao) {
		setMercadoId(mercadoId);
		setOdd(odd);
		setStake(stake);
		setPotencial(potencial);
		setLiquidada(false);
		setOperacao(operacao);
	}
	
	public void informarResultado(Double profit) {
		if (profit > this.odd * this.stake)
			throw new RuntimeException("Resultado de Lucro/Perda Inválido!");
		this.profit = profit;
		this.setLiquidada(true);
	}
	
	public boolean liquidada(){
		return liquidada;
	}
	
	public MercadoId mercadoId(){
		return this.mercadoId;
	}
	
	public Long id(){
		return id;
	}
	
	public Double potencial(){
		return potencial;
	}
	
	public Double profit(){
		return profit;
	}

	public Double odd() {
		return odd;
	}

	public Double stake() {
		return stake;
	}
	
	public Operacao operacao() {
		return operacao;
	}

	public void alterarMercado(MercadoId mercadoId) {
		this.setMercadoId(mercadoId);
	}

	public void alterarStake(Double valor) {
		this.setStake(valor);
	}

	public void alterarOdd(Double valor) {
		this.setOdd(valor);
	}
	
	public void alterarPotencial(Double potencial) {
		this.setPotencial(potencial);
	}
	
	/**
	 * Somente para JPA -----------------------------------------------------------------------------------------------------------------
	 */
	@SuppressWarnings("unused")
	private Aposta(){}

	public void setMercadoId(MercadoId mercadoId) {
		this.mercadoId = mercadoId;
	}

	public void setOdd(Double odd) {
		this.odd = odd;
	}

	public void setStake(Double stake) {
		this.stake = stake;
	}

	public void setLiquidada(boolean liquidada) {
		this.liquidada = liquidada;
	}

	public void setPotencial(Double potencial) {
		this.potencial = potencial;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

}
