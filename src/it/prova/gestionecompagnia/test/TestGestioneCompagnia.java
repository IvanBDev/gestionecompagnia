package it.prova.gestionecompagnia.test;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.prova.gestionecompagnia.connection.MyConnection;
import it.prova.gestionecompagnia.dao.Constants;
import it.prova.gestionecompagnia.dao.compagnia.CompagniaDAO;
import it.prova.gestionecompagnia.dao.compagnia.CompagniaDAOImpl;
import it.prova.gestionecompagnia.dao.impiegato.ImpiegatoDAO;
import it.prova.gestionecompagnia.dao.impiegato.ImpiegatoDAOImpl;
import it.prova.gestionecompagnia.model.Compagnia;

public class TestGestioneCompagnia {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CompagniaDAO compagniaDAOInstance = null;
		ImpiegatoDAO impiegatoDAOInstance = null;
		
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			// ecco chi 'inietta' la connection: il chiamante
			compagniaDAOInstance = new CompagniaDAOImpl(connection);
			impiegatoDAOInstance = new ImpiegatoDAOImpl(connection);
			
			testInsertCompagnia(compagniaDAOInstance);
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void testInsertCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception{
		System.out.println("---------------------testInsertCompagnia: Inizio---------------------------------------");
		
		Date dataFondazioneString = null;
		try {
			dataFondazioneString = new SimpleDateFormat("dd/MM/yyyy").parse("25/07/2008");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int quantiElementiInseriti = compagniaDAOInstance
				.insert(new Compagnia("Team Solvin s.p.a", 200000, dataFondazioneString));
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testInsertUser : FAILED");
		
		
		System.out.println("---------------------testInsertCompagnia: Fine---------------------------------------");
	}

}
