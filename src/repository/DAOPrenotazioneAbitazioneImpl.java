package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;

import contabilità.Cliente;
import struttureEventi.classes.Abitazione;
import struttureEventi.classes.PrenotazioneAbitazione;

public class DAOPrenotazioneAbitazioneImpl implements DAOPrenotazioneAbitazione {
	
	private MySQLConnection connection;

	public DAOPrenotazioneAbitazioneImpl() {
		this.connection = new MySQLConnection();
	}

	public DAOPrenotazioneAbitazioneImpl(MySQLConnection connection) {
		super();
		this.connection = connection;
	}
	
	@Override
	public HashSet<PrenotazioneAbitazione> doRetrieveAll() {
		HashSet<PrenotazioneAbitazione> paCollection = new HashSet<PrenotazioneAbitazione>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM PRENOTAZIONIABITAZIONI");
			
			while (result.next()) {
				String id=result.getString("IdPrenotazioneAbitazione");
				String cliente=result.getString("Cliente");
				String abitazione=result.getString("Abitazione");
				/**String datai=result.getString("dataInizio");
				LocalDate datainizio = LocalDate.parse(datai, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String dataf=result.getString("dataFine");
				LocalDate datafine = LocalDate.parse(datai, DateTimeFormatter.ofPattern("yyyy-MM-dd"));*/
				Date datai=result.getDate("DataInizio");
				LocalDate dataInizio = datai.toLocalDate().parse(datai.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				Date dataf=result.getDate("DataFine");
				LocalDate dataFine = dataf.toLocalDate().parse(dataf.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				PrenotazioneAbitazione a = new PrenotazioneAbitazione( id,DAOFactory.getDAOCliente().doRetrieveByCf(cliente), DAOFactory.getDAOAbitazione().doRetrieveById(abitazione), dataInizio, dataFine);
				paCollection.add(a);
			

		}} catch (SQLException e) {
			e.printStackTrace();
		}
		return paCollection;
	}

	@Override
	public PrenotazioneAbitazione doRetrieveById(String id) {
		PrenotazioneAbitazione pa = null;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM PRENoTAZIONIABITAZIONI WHERE IDPrenotazioneAbitazione=\"" + id + "\"");

			while (result.next()) {
				String idPrenotazioneAbitazione = result.getString("IdAbitazione");
				String cliente=result.getString("Cliente");
				String abitazione=result.getString("Abitazione");
				String datai=result.getString("dataInizio");
				LocalDate datainizio = LocalDate.parse(datai, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String dataf=result.getString("dataFine");
				LocalDate datafine = LocalDate.parse(dataf, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				pa = new PrenotazioneAbitazione(idPrenotazioneAbitazione, DAOFactory.getDAOCliente().doRetrieveByCf(cliente), DAOFactory.getDAOAbitazione().doRetrieveById(abitazione), datainizio, datafine);
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pa;
	}

	@Override
	public void delete(String id) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement.executeUpdate("DELETE FROM PRENOTAZIONIABITAZIONI WHERE IDPrenotazioneAbitazione=\"" + id + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int updatePrenotazioneAbitazione(PrenotazioneAbitazione pa) {
		try {
			//delete(c.getCf());
			
			String query = " insert into PrenotazioniAbitazioni ( IdPrenotazioneAbitazione, Cliente, Abitazione, DataInizio, DataFine)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
			preparedStmt.setString(1, pa.getId());
			preparedStmt.setString(2, pa.getCliente().getCf());
			preparedStmt.setString(3, pa.getAbitazione().getIdAbitazione());
			LocalDate datai=pa.getDataInizio();
			Date dataInizio = Date.valueOf(datai);
			LocalDate dataf=pa.getDataFine();
			Date dataFine = Date.valueOf(datai);
			preparedStmt.setDate(4,  dataInizio);
			preparedStmt.setDate(5,  dataFine);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
