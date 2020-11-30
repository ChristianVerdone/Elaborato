package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import struttureEventi.classes.Biglietto;
import struttureEventi.classes.Evento;

public class DAOBigliettoImpl implements DAOBiglietto {
	

	private MySQLConnection connection;
	public DAOBigliettoImpl() {
		this.connection = new MySQLConnection();
	}

	public DAOBigliettoImpl(MySQLConnection connection) {
		super();
		this.connection = connection;
	}
	@Override
	public HashMap<String, Biglietto> doRetrieveAll() {
		HashMap<String, Biglietto> bigliettiCollection = new HashMap<String, Biglietto>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM BIGLIETTI");
			while (result.next()) {
				String id = result.getString("IdBiglietto");
				float costo=result.getFloat("Costo");
				boolean disponibilit�=result.getBoolean("Disponibilit�");
				String evento=result.getString("NomeEvento");
				
				Biglietto b = new Biglietto(id, costo, disponibilit�, evento);
				bigliettiCollection.put(id, b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bigliettiCollection;
	}

	@Override
	public Biglietto doRetrieveById(String id) {
		Biglietto b = null;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM BIGLIETTI WHERE IdBiglietto=\"" + id + "\"");

			while (result.next()) {
				String idBiglietto = result.getString("IdBiglietto");
				float costo=result.getFloat("Costo");
				boolean disponibilit�=result.getBoolean("Disponibilit�");
				String evento=result.getString("NomeEvento");
				b= new Biglietto(idBiglietto, costo, disponibilit�, evento);
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public void delete(String id) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement.executeUpdate("DELETE FROM BIGLIETTI WHERE IdBiglietto=\"" + id + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	


	@Override
	public int updateBiglietto(Biglietto b) {
		try {
			//delete(c.getCf());
			
			String query = " insert into biglietti (IdBiglietto, Costo, Disponibilit�, NomeEvento)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
			preparedStmt.setString(1, b.getIdBiglietto());
			preparedStmt.setFloat(2, b.getCosto());
			preparedStmt.setBoolean(3, b.isDisponibilit�());
			preparedStmt.setString(1, b.getNomeEvento());
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	}