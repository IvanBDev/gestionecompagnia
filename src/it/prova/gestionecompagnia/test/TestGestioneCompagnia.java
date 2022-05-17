package it.prova.gestionecompagnia.test;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import it.prova.gestionecompagnia.connection.MyConnection;
import it.prova.gestionecompagnia.dao.Constants;
import it.prova.gestionecompagnia.dao.compagnia.CompagniaDAO;
import it.prova.gestionecompagnia.dao.compagnia.CompagniaDAOImpl;
import it.prova.gestionecompagnia.dao.impiegato.ImpiegatoDAO;
import it.prova.gestionecompagnia.dao.impiegato.ImpiegatoDAOImpl;
import it.prova.gestionecompagnia.model.Compagnia;
import it.prova.gestionecompagnia.model.Impiegato;

public class TestGestioneCompagnia {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CompagniaDAO compagniaDAOInstance = null;
		ImpiegatoDAO impiegatoDAOInstance = null;
		
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			// ecco chi 'inietta' la connection: il chiamante
			compagniaDAOInstance = new CompagniaDAOImpl(connection);
			impiegatoDAOInstance = new ImpiegatoDAOImpl(connection);
			
			System.out.println("Nella tabella Compagnia ci sono: "+ compagniaDAOInstance.list().size() +" elementi");
			System.out.println("Nella tabella Impiegato ci sono: "+ impiegatoDAOInstance.list().size() +" elementi");
			
			//testInsertCompagnia(compagniaDAOInstance);
			//testInsertImpiegato(impiegatoDAOInstance, compagniaDAOInstance);
			System.out.println("Nella tabella Compagnia ci sono: "+ compagniaDAOInstance.list().size() +" elementi");
			System.out.println("Nella tabella Impiegato ci sono: "+ impiegatoDAOInstance.list().size() +" elementi");
			
			//testUpdateCompagnia(compagniaDAOInstance);
			//testUpdateImpiegato(impiegatoDAOInstance, compagniaDAOInstance);
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void testInsertCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception{
		System.out.println("---------------------testInsertCompagnia: Inizio---------------------------------------");
		
		Date dataFondazioneString = null;
		try {
			dataFondazioneString = new SimpleDateFormat("dd/MM/yyyy").parse("21/09/1980");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int quantiElementiInseriti = compagniaDAOInstance
				.insert(new Compagnia("Power Star", 2700000, dataFondazioneString));
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testInsertCompagnia : FAILED");
		
		
		System.out.println("---------------------testInsertCompagnia: Fine---------------------------------------");
	}
	
	public static void testInsertImpiegato(ImpiegatoDAO impiegatoDAOInstance, CompagniaDAO compagniaDAOInstance) throws Exception{
		System.out.println("---------------------testInsertImpiegato: Inizio---------------------------------------");
		
		Date dataNascitaString = null;
		Date dataAssunzioneString = null;
		try {
			dataNascitaString = new SimpleDateFormat("dd/MM/yyyy").parse("14/01/1971");
			dataAssunzioneString = new SimpleDateFormat("dd/MM/yyyy").parse("21/10/2013");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException("testInsertArticolo : FAILED, non ci sono negozi sul DB");
		
		Compagnia compagnie = elencoCompagniePresenti.get(1);
		
		int quantiElementiInseriti = impiegatoDAOInstance
				.insert(new Impiegato("Flavio", "Marra", "MRAFVL21A71O479H", dataNascitaString, dataAssunzioneString, compagnie));
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testInsertImpiegato : FAILED");
		
		System.out.println("---------------------testInsertImpiegato: Fine---------------------------------------");
	}
	
	public static void testUpdateCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception{
		System.out.println("---------------------testUpdateCompagnia: Inizio---------------------------------------");
		
		Date modificaDataString= null;
		try {
			modificaDataString = new SimpleDateFormat("dd/MM/yyyy").parse("18/06/2000");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Compagnia compagniaUpdate = new Compagnia(1L, "Team Solving s.p.a", 3200000, modificaDataString);
		
		if(compagniaDAOInstance.update(compagniaUpdate) != 0) {
			System.out.println("Compagnia modificata con successo!");
		}
		else {
			System.out.println("Si e' verificcato un problema");
		}
		
		System.out.println("---------------------testUpdateCompagnia: Fine---------------------------------------");
	}
	
	public static void testUpdateImpiegato(ImpiegatoDAO impiegatoDAOInstance, CompagniaDAO compagniaDAOInstance) throws Exception{
		System.out.println("---------------------testUpdateImpiegato: Inizio---------------------------------------");
		
		List<Compagnia> listaCompagnia = compagniaDAOInstance.list();
		Compagnia idCompagnia = listaCompagnia.get(1);
		
		Date dataNascitaString = null;
		Date dataAssunzioneString = null;
		try {
			dataNascitaString = new SimpleDateFormat("dd/MM/yyyy").parse("20/06/1970");
			dataAssunzioneString = new SimpleDateFormat("dd/MM/yyyy").parse("12/07/2009");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Impiegato impiegatoDaModificare = new Impiegato(5L, "Mario", "Rossi", "RSOMRA21R70A436E", dataNascitaString, dataAssunzioneString, idCompagnia);
		if(impiegatoDAOInstance.update(impiegatoDaModificare) != 0) {
			System.out.println("Impiegato modificato con successo!");
		}
		else {
			System.out.println("Si e' verificcato un problema");
		}
		
		System.out.println("---------------------testUpdateImpiegato: Fine---------------------------------------");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
