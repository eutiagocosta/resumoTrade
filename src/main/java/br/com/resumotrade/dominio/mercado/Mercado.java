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
	
	public static Mercado novoFutebol(MercadoId mercadoId, String descricao) {
		return new Mercado(mercadoId, Esporte.FUTEBOL, descricao);
	}
	
	public static Mercado novoBasquete(MercadoId mercadoId, String descricao) {
		return new Mercado(mercadoId, Esporte.BASQUETE, descricao);
	}

	private Mercado(){}
	
	public String descricao() {
		return descricao;
	}
	
	public MercadoId mercadoId() {
		return mercadoId;
	}
	
	public Esporte esporte(){
		return esporte;
	}
	
	public Long id(){
		return id;
	}

	public void alterarEsporte(Esporte sporte) {
		setEsporte(esporte);
	}

	public void alterarDescricao(String descricao) {
		setDescricao(descricao);
	}

	public void setEsporte(Esporte esporte) {
		this.esporte = esporte;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mercadoId == null) ? 0 : mercadoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mercado other = (Mercado) obj;
		if (mercadoId == null) {
			if (other.mercadoId != null)
				return false;
		} else if (!mercadoId.equals(other.mercadoId))
			return false;
		return true;
	}

}
