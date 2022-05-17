package it.prova.gestionecompagnia.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Impiegato {
	private Long id;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private Date dataNascita;
	private Date dataAssunzione;
	
	public Impiegato() {
		super();
	}

	public Impiegato(String nome, String cognome, String codiceFiscale, Date dataNascita, Date dataAssunzione) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Date getDataAssunzione() {
		return dataAssunzione;
	}

	public void setDataAssunzione(Date dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}
	
	@Override
	public String toString() {
		String dataNascitaString = dataNascita != null ? new SimpleDateFormat("dd/MM/yyyy").format(dataNascita)
				: " N.D.";
		String dataAssunzioneString = dataNascita != null ? new SimpleDateFormat("dd/MM/yyyy").format(dataAssunzione)
				: " N.D.";

		return "User [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codiceFiscale=" + codiceFiscale + ", dataNascita="+ dataNascitaString +", dataAssunzione="+ dataAssunzioneString +"]";
	}
}
