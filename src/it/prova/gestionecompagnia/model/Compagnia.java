package it.prova.gestionecompagnia.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Compagnia {
	private Long id;
	private String ragioneSociale;
	private int fattualeAnnuo;
	private Date dataFondazione;
	private List<Impiegato> impiegato;
	
	public Compagnia() {
		super();
	}

	public Compagnia(String ragioneSociale, int fattualeAnnuo, Date dataFondazione) {
		super();
		this.ragioneSociale = ragioneSociale;
		this.fattualeAnnuo = fattualeAnnuo;
		this.dataFondazione = dataFondazione;
	}

	public Compagnia(Long id, String ragioneSociale, int fattualeAnnuo, Date dataFondazione) {
		super();
		this.id = id;
		this.ragioneSociale = ragioneSociale;
		this.fattualeAnnuo = fattualeAnnuo;
		this.dataFondazione = dataFondazione;
	}

	public Compagnia(String ragioneSociale, int fattualeAnnuo, Date dataFondazione, List<Impiegato> impiegato) {
		super();
		this.ragioneSociale = ragioneSociale;
		this.fattualeAnnuo = fattualeAnnuo;
		this.dataFondazione = dataFondazione;
		this.impiegato = impiegato;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public int getFattualeAnnuo() {
		return fattualeAnnuo;
	}

	public void setFattualeAnnuo(int fattualeAnnuo) {
		this.fattualeAnnuo = fattualeAnnuo;
	}

	public Date getDataFondazione() {
		return dataFondazione;
	}

	public void setDataFondazione(Date dataFondazione) {
		this.dataFondazione = dataFondazione;
	}
	
	public List<Impiegato> getImpiegato() {
		return impiegato;
	}

	public void setImpiegato(List<Impiegato> impiegato) {
		this.impiegato = impiegato;
	}

	@Override
	public String toString() {
		String dataFondazioneString = dataFondazione != null ? new SimpleDateFormat("dd/MM/yyyy").format(dataFondazione)
				: " N.D.";

		return "User [id=" + id + ", ragioneSociale=" + ragioneSociale + ", fattualeAnnuo=" + fattualeAnnuo + ", dataFondazione=" + dataFondazioneString + ", impiegati="+ impiegato +"]";
	}
	
	
	
	
	
	
	
}
