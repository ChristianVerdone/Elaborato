package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import struttureEventi.classes.ContoRistorante;
import struttureEventi.classes.Evento;

public class DAOContoRistoranteImpl implements DAOContoRistorante {
	private MySQLConnection connection;
	public DAOContoRistoranteImpl() {
		this.connection = new MySQLConnection();
	}

	public DAOContoRistoranteImpl(MySQLConnection connection) {
		super();
		this.connection = connection;
	}
	@Override
	public HashMap<String, ContoRistorante> doRetrieveAll() {
		HashMap<String, ContoRistorante> contiCollection = new HashMap<String, ContoRistorante>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM CONTIRISTORANTE");
			while (result.next()) {
				String id = result.getString("IdConto");
				String idPrenotazione=result.getString("PrenotazioneRistorante");
				float conto=result.getFloat("ContoTotale");
				ContoRistorante cr = new ContoRistorante(id, conto, DAOFactory.getDAOPrenotazioneRistorante().doRetrieveById(idPrenotazione));
				contiCollection.put(id, cr);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contiCollection;
	}
	
	@Override
	public ContoRistorante doRetrieveById(String id) {
		ContoRistorante cr = null;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM CONTIRISTORANTE WHERE IdConto=\"" + id + "\"");

			while (result.next()) {
				String idConto = result.getString("IdConto");
				String idPrenotazione=result.getString("PrenotazioneRistorante");
				float costo=result.getFloat("ContoTotale");
				
				cr = new ContoRistorante(idConto, costo, DAOFactory.getDAOPrenotazioneRistorante().doRetrieveById(idPrenotazione));
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cr;
	}


	public void delete(String id) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement.executeUpdate("DELETE FROM CONTIRISTORANTE WHERE IdConto=\"" + id + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	



	public int updateContoRistorante(ContoRistorante cr) {
		try {
			
			String query = " insert into contiristorante (IdConto, PrenotazioneRistorante, ContoTotale)"
					+ " values (?, ?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
			preparedStmt.setString(1, cr.getIdConto());
			preparedStmt.setString(2, cr.getPrenotazioneRistorante().getIdPrenotazione());
			preparedStmt.setFloat(3, cr.getCosto());
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
