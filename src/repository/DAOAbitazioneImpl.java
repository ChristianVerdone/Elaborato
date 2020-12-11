package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import struttureEventi.classes.Abitazione;

public class DAOAbitazioneImpl implements DAOAbitazione {
	private MySQLConnection connection;

	public DAOAbitazioneImpl() {
		this.connection = new MySQLConnection();
	}

	public DAOAbitazioneImpl(MySQLConnection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public HashMap<String, Abitazione> doRetrieveAll() {
		HashMap<String, Abitazione> abitazioniCollection = new HashMap<String, Abitazione>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM ABITAZIONI");

			while (result.next()) {
				String idAbitazione = result.getString("IdAbitazione");
				int postiLetto=result.getInt("PostiLetto");
				float tariffa=(float) result.getDouble("Tariffa");
				String descrizione=result.getString("Descrizione");
				Abitazione a = new Abitazione(idAbitazione, postiLetto, tariffa, descrizione);
				abitazioniCollection.put(idAbitazione, a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return abitazioniCollection;
	}

	@Override
	public Abitazione doRetrieveById(String id) {
		Abitazione a = null;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM ABITAZIONI WHERE IdAbitazione=\"" + id + "\"");

			while (result.next()) {
				String idAbitazione = result.getString("IdAbitazione");
				int postiLetto=result.getInt("PostiLetto");
				float tariffa=(float) result.getDouble("Tariffa");
				String descrizione=result.getString("Descrizione");
				a = new Abitazione(idAbitazione, postiLetto, tariffa, descrizione);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public void delete(String id) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement.executeUpdate("DELETE FROM ABITAZIONI WHERE IdAbitazione=\"" + id + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}