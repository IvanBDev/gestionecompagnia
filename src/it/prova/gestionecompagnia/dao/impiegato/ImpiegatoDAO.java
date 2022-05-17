package it.prova.gestionecompagnia.dao.impiegato;

import java.util.Date;
import java.util.List;

import it.prova.gestionecompagnia.dao.IBaseDAO;
import it.prova.gestionecompagnia.model.Impiegato;

public interface ImpiegatoDAO extends IBaseDAO<Impiegato>{
	
	public List<Impiegato> findAllWhereDateCreatedGreaterThan(Date dateCreatedInput) throws Exception;
	public List<Impiegato> findAllByCognome(String cognomeInput) throws Exception;
	public List<Impiegato> findAllByLoginIniziaCon(String caratteriInizialiInput) throws Exception;
	public Impiegato findByLoginAndPassword(String loginInput, String passwordInput) throws Exception;
	public List<Impiegato> findAllByPasswordIsNull() throws Exception;
	
}
