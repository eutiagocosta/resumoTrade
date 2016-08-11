package br.com.resumotrade.dominio.operacao.aposta;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.resumotrade.dominio.mercado.MercadoId;

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
	
	public Aposta(MercadoId mercadoId, Double odd, Double stake, Double potencial) {
		this.mercadoId = mercadoId;
		this.odd = odd;
		this.stake = stake;
		this.potencial = potencial;
		this.liquidada = false;
	}
	
	public void informarResultado(Double profit) {
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
		return this.odd;
	}

	public Double stake() {
		return this.stake;
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

}
