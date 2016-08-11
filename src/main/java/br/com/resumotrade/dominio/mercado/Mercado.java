package br.com.resumotrade.dominio.mercado;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name="SEQ", sequenceName="SQ_MERCADO")
@Table(name="MERCADO")
public class Mercado {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ")
	private Long id;
	
	@Embedded
	private MercadoId mercadoId;
	
	@Enumerated(EnumType.STRING)
	private Esporte esporte;
	
	private String descricao;
	
	private Mercado(MercadoId mercadoId, Esporte esporte, String descricao) {
		this.mercadoId = mercadoId;
		this.esporte = esporte;
		this.setDescricao(descricao);
	}
	
	private Mercado(){}
	
	public String descricao() {
		return descricao;
	}
	
	public static Mercado novoFutebol(MercadoId mercadoId, String descricao) {
		return new Mercado(mercadoId, Esporte.FUTEBOL, descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void alterarDescricao(String string) {
		this.setDescricao(string);
	}

	public MercadoId id() {
		return mercadoId;
	}

}
