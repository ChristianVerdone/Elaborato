package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

import struttureEventi.classes.PrenotazioneEvento;


public class DAOPrenotazioneEventoImpl implements DAOPrenotazioneEvento {

	private MySQLConnection connection;

	public DAOPrenotazioneEventoImpl() {
		this.connection = new MySQLConnection();
	}

	public DAOPrenotazioneEventoImpl(MySQLConnection connection) {
		super();
		this.connection = connection;
	}
	
	@Override
	public HashSet<PrenotazioneEvento> doRetrieveAll() {
		HashSet<PrenotazioneEvento> peCollection = new HashSet<PrenotazioneEvento>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM PRENOTAZIONIEVENTI");
			 
			while (result.next()) {
				String idPrenotazione=result.getString("IdPrenotazioneEvento");
				String cliente=result.getString("Cliente");
				String evento=result.getString("Evento");
				String biglietto=result.getString("Biglietto");
				PrenotazioneEvento p = new PrenotazioneEvento( idPrenotazione, DAOFactory.getDAOCliente().doRetrieveByCf(cliente), DAOFactory.getDAOEvento().doRetrieveById(evento), DAOFactory.getDAOBiglietto().doRetrieveById(biglietto));
				peCollection.add(p);
			

		}} catch (SQLException e) {
			e.printStackTrace();
		}
		return peCollection;
	}

	@Override
	public PrenotazioneEvento doRetrieveById(String id) {
		PrenotazioneEvento pe=null;;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM PRENOTAZIONIEVENTI");
			 
			while (result.next()) {
				String idPrenotazione=result.getString("IdPrenotazioneEvento");
				String cliente=result.getString("Cliente");
				String evento=result.getString("Evento");
				String biglietto=result.getString("Biglietto");
				PrenotazioneEvento p = new PrenotazioneEvento( idPrenotazione, DAOFactory.getDAOCliente().doRetrieveByCf(cliente), DAOFactory.getDAOEvento().doRetrieveById(evento), DAOFactory.getDAOBiglietto().doRetrieveById(biglietto));
	
			

		}} catch (SQLException e) {
			e.printStackTrace();
		}
		return pe;
	}
	@Override
	public void delete(String id) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement.executeUpdate("DELETE FROM PRENOTAZIONIEVENTI WHERE IDPrenotazioneEvento=\"" + id + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int updatePrenotazioneEvento(PrenotazioneEvento pe) {
		try {
			
			
			String query = " insert into PrenotazioniAbitazioni ( IdPrenotazioneEvento, Cliente, Biglietto, Evento)"
					+ " values (?, ?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
			preparedStmt.setString(1, pe.getIdPrenotazione());
			preparedStmt.setString(2, pe.getCliente().getCf());
			preparedStmt.setString(3, pe.getBiglietto().getIdBiglietto());
			preparedStmt.setString(4,  pe.getEvento().getIdEvento());

			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}

