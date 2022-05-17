package it.prova.gestionecompagnia.dao.compagnia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.prova.gestionecompagnia.connection.MyConnection;
import it.prova.gestionecompagnia.dao.AbstractMySQLDAO;
import it.prova.gestionecompagnia.model.Compagnia;

public class CompagniaDAOImpl extends AbstractMySQLDAO implements CompagniaDAO {
	// la connection stavolta fa parte del this, quindi deve essere 'iniettata'
	// dall'esterno
	public CompagniaDAOImpl(Connection connection) {
		super(connection);
	}

	@Override
	public List<Compagnia> list() throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();
		Compagnia compagniaTemp = null;

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("SELECT * FROM compagnia")) {

			while (rs.next()) {
				compagniaTemp = new Compagnia();
				compagniaTemp.setRagioneSociale(rs.getString("ragioneSociale"));
				compagniaTemp.setFattualeAnnuo(rs.getInt("fattualeAnnuo"));
				compagniaTemp.setDataFondazione(rs.getDate("dataFondazione"));
				compagniaTemp.setId(rs.getLong("id"));

				result.add(compagniaTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Compagnia get(Long idInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Compagnia result = null;
		try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {

					result = new Compagnia();
					result.setRagioneSociale(rs.getString("ragioneSociale"));
					result.setFattualeAnnuo(rs.getInt("fattualeAnnuo"));
					result.setDataFondazione(rs.getDate("dataFondazione"));
					result.setId(rs.getLong("id"));

				} else {
					result = null;
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Compagnia compagniaInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (compagniaInput == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO compagnia (ragioneSociale, fattualeAnnuo, dataFondazione) VALUES (?, ?, ?);")) {

			ps.setString(1, compagniaInput.getRagioneSociale());
			ps.setInt(2, compagniaInput.getFattualeAnnuo());
			ps.setDate(3, new java.sql.Date(compagniaInput.getDataFondazione().getTime()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int update(Compagnia compagniaInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (compagniaInput == null || compagniaInput.getId() == null || compagniaInput.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE compagnia SET ragioneSociale = ?, fattualeAnnuo = ?, dataFondazione = ? WHERE id = ?;")) {
			ps.setString(1, compagniaInput.getRagioneSociale());
			ps.setInt(2, compagniaInput.getFattualeAnnuo());
			ps.setDate(3, new java.sql.Date(compagniaInput.getDataFondazione().getTime()));
			ps.setLong(4, compagniaInput.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Compagnia compagniaInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (compagniaInput == null || compagniaInput.getId() == null || compagniaInput.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM compagnia WHERE id = ?")) {
			ps.setLong(1, compagniaInput.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Compagnia> findByExample(Compagnia input) throws Exception {
		// TODO Auto-generated method stub

		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();
		Compagnia compagniaTemp = null;

		String query = "SELECT * FROM compagnia WHERE 1=1 ";
		if (input.getRagioneSociale() != null && !input.getRagioneSociale().isEmpty()) {
			query += " AND cognome like '" + input.getRagioneSociale() + "%' ";
		}
		if (input.getFattualeAnnuo() > 0) {
			query += " AND nome like '" + input.getFattualeAnnuo() + "%' ";
		}
		if (input.getDataFondazione() != null) {
			query += " AND dateCreated = '" + new java.sql.Date(input.getDataFondazione().getTime()) + "' ";
		}

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				compagniaTemp = new Compagnia();
				compagniaTemp.setRagioneSociale("ragioneSociale");
				compagniaTemp.setFattualeAnnuo(rs.getInt("fattualeAnnuo"));
				compagniaTemp.setDataFondazione(rs.getDate("dataFondazione"));
				compagniaTemp.setId(rs.getLong("id"));
				result.add(compagniaTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return result;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
