package it.prova.gestionecompagnia.dao.impiegato;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.prova.gestionecompagnia.dao.AbstractMySQLDAO;
import it.prova.gestionecompagnia.model.Compagnia;
import it.prova.gestionecompagnia.model.Impiegato;
import it.prova.model.User;

public class ImpiegatoDAOImpl  extends AbstractMySQLDAO implements ImpiegatoDAO{
	// la connection stavolta fa parte del this, quindi deve essere 'iniettata'
		// dall'esterno
		public ImpiegatoDAOImpl(Connection connection) {
			super(connection);
		}
		
		public List<Impiegato> list() throws Exception {
			// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
			if (isNotActive())
				throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

			ArrayList<Impiegato> result = new ArrayList<Impiegato>();
			Impiegato impiegatoTemp = null;
			Compagnia compagniaTemp = null;

			try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("SELECT * FROM impiegato i INNER JOIN compagnia c ON i.id_compagnia = c.id;")) {

				while (rs.next()) {
					impiegatoTemp = new Impiegato();
					impiegatoTemp.setNome(rs.getString("i.nome"));
					impiegatoTemp.setCognome(rs.getString("i.cognome"));
					impiegatoTemp.setCodiceFiscale(rs.getString("i.codiceFiscale"));
					impiegatoTemp.setDataNascita(rs.getDate("i.dataNascita"));
					impiegatoTemp.setDataAssunzione(rs.getDate("i.dataAssunzione"));
					impiegatoTemp.setId(rs.getLong("i.id_compagnia"));
					
					compagniaTemp = new Compagnia();
					compagniaTemp.setId(rs.getLong("c.id"));
					compagniaTemp.setRagioneSociale(rs.getString("c.ragioneSociale"));
					compagniaTemp.setFattualeAnnuo(rs.getInt("c.fattualeAnnuo"));
					compagniaTemp.setDataFondazione(rs.getDate("c.dataFondazione"));
					
					impiegatoTemp.setCompagnia(compagniaTemp);
					result.add(impiegatoTemp);
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return result;
		}
		
		@Override
		public Impiegato get(Long idInput) throws Exception {
			// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
			if (isNotActive())
				throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

			if (idInput == null || idInput < 1)
				throw new Exception("Valore di input non ammesso.");

			Impiegato result = null;
			try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM impiegato WHERE id = ?")) {

				ps.setLong(1, idInput);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						result = new Impiegato();
						result.setId(rs.getLong("id"));
						result.setNome(rs.getString("nome"));
						result.setCognome(rs.getString("cognome"));
						result.setCodiceFiscale(rs.getString("codiceFiscale"));
						result.setDataNascita(rs.getDate("dataNascita"));
						result.setDataAssunzione(rs.getDate("dataAssunzione"));
						result.setId(rs.getLong("id_compagnia"));
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
		public int insert(Impiegato impiegatoInput) throws Exception {
			// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
			if (isNotActive())
				throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

			if (impiegatoInput == null)
				throw new Exception("Valore di input non ammesso.");

			int result = 0;
			try (PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO impiegato (nome, cognome, codiceFiscale, dataNascita, dataAssunzione, id_compagnia) VALUES (?, ?, ?, ?, ?, ?);")) {
				ps.setString(1, impiegatoInput.getNome());
				ps.setString(2, impiegatoInput.getCognome());
				ps.setString(3, impiegatoInput.getCodiceFiscale());
				ps.setDate(4, new java.sql.Date(impiegatoInput.getDataNascita().getTime()));
				ps.setDate(5, new java.sql.Date(impiegatoInput.getDataAssunzione().getTime()));
				ps.setLong(6, impiegatoInput.getCompagnia().getId());
				result = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return result;
		}
		
		@Override
		public int update(Impiegato impiegatoInput) throws Exception {
			// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
			if (isNotActive())
				throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

			if (impiegatoInput == null || impiegatoInput.getId() == null || impiegatoInput.getId() < 1)
				throw new Exception("Valore di input non ammesso.");

			int result = 0;
			try (PreparedStatement ps = connection.prepareStatement(
					"UPDATE impiegato SET nome = ?, cognome = ?, codiceFiscale = ?, dataNascita = ?, dataAssunzione=? where id=?;")) {
				ps.setString(1, impiegatoInput.getNome());
				ps.setString(2, impiegatoInput.getCognome());
				ps.setString(3, impiegatoInput.getCodiceFiscale());
				ps.setDate(4, new java.sql.Date(impiegatoInput.getDataNascita().getTime()));
				ps.setDate(5, new java.sql.Date(impiegatoInput.getDataAssunzione().getTime()));
				ps.setLong(6, impiegatoInput.getCompagnia().getId());
				result = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return result;
		}
		
		@Override
		public int delete(Impiegato impiegatoInput) throws Exception {
			// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
			if (isNotActive())
				throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

			if (impiegatoInput == null || impiegatoInput.getId() == null || impiegatoInput.getId() < 1)
				throw new Exception("Valore di input non ammesso.");

			int result = 0;
			try (PreparedStatement ps = connection.prepareStatement("DELETE FROM impiegato WHERE id = ?")) {
				ps.setLong(1, impiegatoInput.getId());
				result = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return result;
		}
		
		@Override
		public List<Impiegato> findByExample(Impiegato input) throws Exception {
			// TODO Auto-generated method stub

			if (isNotActive())
				throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

			if (input == null)
				throw new Exception("Valore di input non ammesso.");

			ArrayList<Impiegato> result = new ArrayList<Impiegato>();
			Impiegato userTemp = null;

			String query = "SELECT * FROM user WHERE 1=1 ";
			if (input.getCognome() != null && !input.getCognome().isEmpty()) {
				query += " AND cognome like '" + input.getCognome() + "%' ";
			}
			if (input.getNome() != null && !input.getNome().isEmpty()) {
				query += " AND nome like '" + input.getNome() + "%' ";
			}
			if (input.getDataNascita() != null) {
				query += " AND dateaNascita = '" + new java.sql.Date(input.getDataNascita().getTime()) + "' ";
			}
			if (input.getDataAssunzione() != null) {
				query += " AND dataAssunzione = '" + new java.sql.Date(input.getDataAssunzione().getTime()) + "' ";
			}

			try (Statement ps = connection.createStatement()) {
				ResultSet rs = ps.executeQuery(query);

				while (rs.next()) {
					userTemp = new Impiegato();
					userTemp.setNome(rs.getString("nome"));
					userTemp.setCognome(rs.getString("cognome"));
					userTemp.setCodiceFiscale(rs.getString("codiceFiscale"));
					userTemp.setDataNascita(rs.getDate("dataNascita"));
					userTemp.setDataAssunzione(rs.getDate("dataAssunzione"));
					
					result.add(userTemp);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return result;
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
