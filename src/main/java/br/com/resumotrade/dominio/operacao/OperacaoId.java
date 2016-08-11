package br.com.resumotrade.dominio.operacao;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OperacaoId {

	@Column(name="OPERACAO_ID")
	private String id;

	public OperacaoId(String id){
		this.id = id;
	}
	
	public String id() {
		return this.id;
	}
	
	@SuppressWarnings("unused")
	private OperacaoId(){}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		OperacaoId other = (OperacaoId) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OperacaoId [id=" + id + "]";
	}
	
	
}
