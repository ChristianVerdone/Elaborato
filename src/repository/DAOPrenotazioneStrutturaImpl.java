package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import struttureEventi.classes.PrenotazioneSv;

public class DAOPrenotazioneStrutturaImpl implements DAOPrenotazioneStruttura {
	private MySQLConnection connection;

	public DAOPrenotazioneStrutturaImpl() {
		this.connection = new MySQLConnection();
	}

	public DAOPrenotazioneStrutturaImpl(MySQLConnection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public HashSet<PrenotazioneSv> doRetrieveAll() {
		HashSet<PrenotazioneSv> psCollection = new HashSet<PrenotazioneSv>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM PRENOTAZIONISTRUTTURE");

			while (result.next()) {
				String idPrenotazione=result.getString("IdPrenotazioneStruttura");
				String cliente=result.getString("Cliente");
				String struttura=result.getString("StrutturaVillaggio");
				String tessera=result.getString("Tessera");
				PrenotazioneSv ps = new PrenotazioneSv(idPrenotazione, DAOFactory.getDAOCliente().doRetrieveByCf(cliente), DAOFactory.getDAOStrutturaVillaggio().doRetrieveById(struttura), DAOFactory.getDAOTessera().doRetrieveById(tessera));
				psCollection.add(ps);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return psCollection;
	}

	@Override
	public PrenotazioneSv doRetrieveById(String id) {
		PrenotazioneSv ps=null;;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM PRENOTAZIONISTRUTTURE WHERE IDPRENOTAZIONISTRUTTURE\"" + id + "\"");

			while (result.next()) {
				String idPrenotazione=result.getString("IdPrenotazioneStruttura");
				String cliente=result.getString("Cliente");
				String struttura=result.getString("StrutturaVillaggio");
				String tessera=result.getString("Tessera");
				ps =new PrenotazioneSv(idPrenotazione, DAOFactory.getDAOCliente().doRetrieveByCf(cliente), DAOFactory.getDAOStrutturaVillaggio().doRetrieveById(struttura), DAOFactory.getDAOTessera().doRetrieveById(tessera));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	@Override
	public void delete(String id) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement.executeUpdate("DELETE FROM PRENOTAZIONISTRUTTURE WHERE IDPrenotazioneStruttura=\"" + id + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int updatePrenotazioneStruttura(PrenotazioneSv ps ){
		try {
			String query = " insert into PrenotazioniStrutture ( IdPrenotazioneStruttura, Cliente, Struttura, Tessera)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
			preparedStmt.setString(1, ps.getIdPrenotazione());
			preparedStmt.setString(2, ps.getCliente().getCf());
			preparedStmt.setString(3, ps.getStruttura().getIdStruttura());
			preparedStmt.setString(4, ps.getTessera().getId());
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}