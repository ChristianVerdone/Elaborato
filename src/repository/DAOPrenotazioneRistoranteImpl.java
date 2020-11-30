package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import contabilità.Cliente;
import struttureEventi.classes.PrenotazioneEvento;
import struttureEventi.classes.PrenotazioneRistorante;

public class DAOPrenotazioneRistoranteImpl implements DAOPrenotazioneRistorante {
	private MySQLConnection connection;

	public DAOPrenotazioneRistoranteImpl() {
		this.connection = new MySQLConnection();
	}

	public DAOPrenotazioneRistoranteImpl(MySQLConnection connection) {
		super();
		this.connection = connection;
	}
	
	@Override
	public HashSet<PrenotazioneRistorante> doRetrieveAll() {
		HashSet<PrenotazioneRistorante> prCollection = new HashSet<PrenotazioneRistorante>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM PRENOTAZIONIRISTORANTE");
			
			while (result.next()) {
				String idPrenotazione=result.getString("IdPrenotazioneRistorante");
				String cliente=result.getString("Cliente");
				int tavolo=result.getInt("Tavolo");
				PrenotazioneRistorante pr = new PrenotazioneRistorante(idPrenotazione, DAOFactory.getDAOCliente().doRetrieveByCf(cliente), tavolo);
				
				prCollection.add(pr);
		}} catch (SQLException e) {
			e.printStackTrace();
		}
		return prCollection;
	}

	@Override
	public PrenotazioneRistorante doRetrieveById(String id) {
		PrenotazioneRistorante pr=null;;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM PRENOTAZIONIRISTORANTE WHERE IDPRENOTAZIONERISTORANTE\"" + id + "\"");
			 
			while (result.next()) {
				String idPrenotazione=result.getString("IdPrenotazioneRistorante");
				String cliente=result.getString("Cliente");
				int tavolo=result.getInt("Tavolo");
				 pr = new PrenotazioneRistorante(idPrenotazione, DAOFactory.getDAOCliente().doRetrieveByCf(cliente), tavolo);
				
			

		}} catch (SQLException e) {
			e.printStackTrace();
		}
		return pr;
	}
	@Override
	public void delete(String id) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement.executeUpdate("DELETE FROM PRENOTAZIONIRISTORANTE WHERE IDPrenotazioneRistorante=\"" + id + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int updatePrenotazioneRistorante(PrenotazioneRistorante pr) {
		try {
			
			
			String query = " insert into PrenotazioniRistorante ( IdPrenotazioneRistorante, Cliente, Tavolo)"
					+ " values (?, ?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
			preparedStmt.setString(1, pr.getIdPrenotazione());
			preparedStmt.setString(2, pr.getCliente().getCf());
			preparedStmt.setInt(3, pr.getnTavolo());
			

			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}



