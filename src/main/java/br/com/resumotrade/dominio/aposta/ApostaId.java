package br.com.resumotrade.dominio.aposta;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ApostaId {
	
	@Column(name="APOSTA_ID")
	private String id;

	public ApostaId(String id) {
		this.id = id;
	}
	
	public String id() {
		return this.id;
	}
	
	@SuppressWarnings("unused")
	private ApostaId(){}

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
		ApostaId other = (ApostaId) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
