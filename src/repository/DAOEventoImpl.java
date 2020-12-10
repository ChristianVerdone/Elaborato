package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import struttureEventi.classes.Evento;

public class DAOEventoImpl implements DAOEvento {
	private MySQLConnection connection;
	public DAOEventoImpl() {
		this.connection = new MySQLConnection();
	}

	public DAOEventoImpl(MySQLConnection connection) {
		super();
		this.connection = connection;
	}
	
	@Override
	public HashMap<String, Evento> doRetrieveAll() {
		HashMap<String, Evento> eventiCollection = new HashMap<String, Evento>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM EVENTI");
			while (result.next()) {
				String id = result.getString("IdEvento");
				String nome=result.getString("Nome");
				String tipo=result.getString("Tipo");
				String descrizione=result.getString("Descrizione");
				Evento e = new Evento(id, nome, tipo, descrizione);
				eventiCollection.put(id, e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventiCollection;
	}

	@Override
	public Evento doRetrieveById(String id) {
		Evento ev = null;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM EVENTI WHERE IdBiglietto=\"" + id + "\"");

			while (result.next()) {
				String idEvento = result.getString("IdEvento");
				String nome=result.getString("Nome");
				String tipo=result.getString("Tipo");
				String descrizione=result.getString("Descrizione");
				ev= new Evento(idEvento, nome, tipo, descrizione);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ev;
	}

	@Override
	public void delete(String id) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement.executeUpdate("DELETE FROM EVENTI WHERE IdEvento=\"" + id + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int updateEvento(Evento ev) {
		try {
			//delete(c.getCf());

			String query = " insert into eventi (IdEvento, Nome, Tipo, Descrizione)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
			preparedStmt.setString(1, ev.getIdEvento());
			preparedStmt.setString(2, ev.getNome());
			preparedStmt.setString(3, ev.getTipo());
			preparedStmt.setString(4, ev.getDescrizione());
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}