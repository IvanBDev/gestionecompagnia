package it.prova.gestionecompagnia.dao.compagnia;

import java.util.Date;
import java.util.List;

import it.prova.gestionecompagnia.dao.IBaseDAO;
import it.prova.gestionecompagnia.model.Compagnia;

public interface CompagniaDAO extends IBaseDAO<Compagnia>{
	
	public List<Compagnia> findAllWhereDateCreatedGreaterThan(Date dateCreatedInput) throws Exception;
	public List<Compagnia> findAllByCognome(String cognomeInput) throws Exception;
	public List<Compagnia> findAllByLoginIniziaCon(String caratteriInizialiInput) throws Exception;
	public Compagnia findByLoginAndPassword(String loginInput, String passwordInput) throws Exception;
	public List<Compagnia> findAllByPasswordIsNull() throws Exception;
	
}
