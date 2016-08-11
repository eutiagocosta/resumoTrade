package br.com.resumotrade.dominio.operacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.resumotrade.dominio.mercado.Esporte;
import br.com.resumotrade.dominio.operacao.aposta.Aposta;

@Entity
@SequenceGenerator(name="SEQ", sequenceName="SQ_OPERACAO")
@Table(name="OPERACAO")
public class Operacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ")
	private Long id;
	
	@Embedded
	private OperacaoId operacaoId;
	
	@Enumerated(EnumType.STRING)
	private Casa casa;
	
	@Enumerated(EnumType.STRING)
	private Esporte esporte;
	
	private LocalDate data;
	private String mandante;
	private String visitante;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(orphanRemoval=true,cascade=CascadeType.ALL)
	@JoinColumn
	private List<Aposta> aposta;

	public Operacao(Casa casa, Esporte esporte, LocalDate data, String mandante, String visitante) {
		this.casa = casa;
		this.esporte = esporte;
		this.data = data;
		this.mandante = mandante;
		this.visitante = visitante;
		this.aposta = new ArrayList<>();
		this.status = Status.ABERTO;
	}
	
	public void novaAposta(Aposta aposta) {
		this.aposta.add(aposta);
	}
	
	public Status status() {
		return status;
	}
	
	public void alterarParaEncerrado() {
		this.status = Status.ENCERRADO;
	}
	
	public OperacaoId id() {
		return operacaoId;
	}
	
	public List<Aposta> apostas() {
		return aposta;
	}
	
	public String mandante() {
		return this.mandante;
	}
	
	public String visitante(){
		return this.visitante;
	}
	
	public LocalDate data(){
		return this.data;
	}
	
	public Esporte esporte(){
		return this.esporte;
	}
	
	public Casa casa(){
		return this.casa;
	}
	
	public void alterarVisitante(String visitante) {
		this.setVisitante(visitante);
	}

	public void alterarMandante(String mandante) {
		this.setMandante(mandante);
	}

	public void alterarEsporte(Esporte esporte) {
		this.setEsporte(esporte);
	}

	public void alterarCasa(Casa casa) {
		this.setCasa(casa);
	}
	
	@SuppressWarnings("unused")
	private Operacao(){}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}

	public void setEsporte(Esporte esporte) {
		this.esporte = esporte;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public void setMandante(String mandante) {
		this.mandante = mandante;
	}

	public void setVisitante(String visitante) {
		this.visitante = visitante;
	}

	public void setAposta(List<Aposta> aposta) {
		this.aposta = aposta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operacaoId == null) ? 0 : operacaoId.hashCode());
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
		Operacao other = (Operacao) obj;
		if (operacaoId == null) {
			if (other.operacaoId != null)
				return false;
		} else if (!operacaoId.equals(other.operacaoId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Operacao [id=" + id + ", operacaoId=" + operacaoId + ", casa=" + casa + ", esporte=" + esporte
				+ ", data=" + data + ", mandante=" + mandante + ", visitante=" + visitante + ", aposta=" + aposta + "]";
	}
}
