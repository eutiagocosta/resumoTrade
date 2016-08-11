package br.com.resumotrade.dominio.lancamento;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.resumotrade.dominio.mercado.MercadoId;

@Entity
@SequenceGenerator(name="SEQ", sequenceName="SQ_LANCAMENTO")
@Table(name="LANCAMENTO")
public class Lancamento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ")
	private Long id;
	
	@Embedded
	private LancamentoId lancamentoId;
	
	@Embedded
	private MercadoId mercadoId;
	
	private Double odd;
	private Double stake;

	public Lancamento(LancamentoId lancamentoId, MercadoId mercadoId, Double odd, Double stake) {
		this.lancamentoId = lancamentoId;
		this.mercadoId = mercadoId;
		this.odd = odd;
		this.stake = stake;
	}

	public LancamentoId id() {
		return lancamentoId;
	}
	
	public MercadoId mercadoId(){
		return this.mercadoId;
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
	private Lancamento(){}

	public void setMercadoId(MercadoId mercadoId) {
		this.mercadoId = mercadoId;
	}

	public void setOdd(Double odd) {
		this.odd = odd;
	}

	public void setStake(Double stake) {
		this.stake = stake;
	}

}
