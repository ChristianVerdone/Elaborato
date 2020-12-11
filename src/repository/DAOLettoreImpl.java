package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import struttureEventi.classes.Lettore;

public class DAOLettoreImpl implements DAOLettore {

	private MySQLConnection connection;
	public DAOLettoreImpl() {
		this.connection = new MySQLConnection();
	}

	public DAOLettoreImpl(MySQLConnection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public HashMap<String, Lettore> doRetrieveAll() {
		HashMap<String, Lettore> lettoriCollection = new HashMap<String, Lettore>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM LETTORI");
			while (result.next()) {
				String id = result.getString("IdLettore");
				String descrizione=result.getString("DescrizioneLettore");
				String struttura=result.getString("StrutturaVillaggio");
				Lettore l = new Lettore(id, descrizione, struttura);
				lettoriCollection.put(l.getIdLettore(), l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lettoriCollection;
	}

	@Override
	public Lettore doRetrieveById(String lettore) {
		Lettore l = null;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM LETTORI WHERE IdLettore=\"" + lettore + "\"");

			while (result.next()) {
				String idLettore = result.getString("IdLettore");
				String descrizione=result.getString("DescrizioneLettore");
				String struttura=result.getString("StrutturaVillaggio");
				l = new Lettore(idLettore, descrizione, struttura);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
}