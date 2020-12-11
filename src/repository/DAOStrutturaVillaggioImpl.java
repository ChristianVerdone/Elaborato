package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.sql.SQLException;
import java.sql.Statement;

import struttureEventi.classes.StrutturaVillaggio;

public class DAOStrutturaVillaggioImpl implements DAOStrutturraVillaggio {

	private MySQLConnection connection;

	public DAOStrutturaVillaggioImpl() {
		this.connection = new MySQLConnection();
	}

	public DAOStrutturaVillaggioImpl(MySQLConnection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public HashMap<String, StrutturaVillaggio> doRetrieveAll() {
		HashMap<String, StrutturaVillaggio> struttureCollection = new HashMap<>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM STRUTTUREVILLAGGIO");
			while (result.next()) {
				String id=result.getString("IdStruttura");
				String tipo=result.getString("Tipo");
				float tariffa=result.getFloat("Tariffa");
				StrutturaVillaggio s = new StrutturaVillaggio(id, tariffa, tipo);
				struttureCollection.put(id, s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return struttureCollection;
	}

	@Override
	public StrutturaVillaggio doRetrieveById(String id) {
		StrutturaVillaggio s=null;;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM STRUTTUREVILLAGGIO WHERE IdStruttura=\""+ id + "\"");

			while (result.next()) {
				String idStruttura=result.getString("IdStruttura");
				String tipo=result.getString("Tipo");
				float tariffa=result.getFloat("Tariffa");
				s = new StrutturaVillaggio(idStruttura, tariffa, tipo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	@Override
	public void delete(String  id) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement.executeUpdate("DELETE FROM  STRUTTUREVILLAGGIO WHERE IdStruttura=\""+ id + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int updateStruttura(StrutturaVillaggio s) {
		try {
			String query = " insert into Strutture ( IdStruttura, Tariffa, Tipo)"
					+ " values (?, ?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
			preparedStmt.setString(1, s.getIdStruttura());
			preparedStmt.setFloat(2, s.getTariffaOraria());
			preparedStmt.setString(2, s.getTipo());

			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}