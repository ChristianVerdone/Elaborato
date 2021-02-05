package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

import javax.swing.JOptionPane;

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
				String idPrenotazione = result.getString("IdPrenotazioneRistorante");
				String cliente = result.getString("Cliente");
				int tavolo = result.getInt("Tavolo");
				String data = result.getString("dataPrenotazione");
				// LocalDate dataPrenotazione = LocalDate.parse(data,
				// DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String ora = result.getString("oraPrenotazione");
				LocalDate dt = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				LocalTime t = LocalTime.parse(ora, DateTimeFormatter.ofPattern("HH:mm:ss"));
				PrenotazioneRistorante pr = new PrenotazioneRistorante(idPrenotazione,
						DAOFactory.getDAOCliente().doRetrieveByCf(cliente), tavolo, dt, t);
				prCollection.add(pr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prCollection;
	}

	public PrenotazioneRistorante doRetrieveById(String id) {
		PrenotazioneRistorante pr = null;
		Statement statement = null;

		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement
					.executeQuery("SELECT * FROM PRENOTAZIONIRISTORANTE WHERE IDPRENOTAZIONERISTORANTE=\"" + id + "\"");

			while (result.next()) {
				String idPrenotazione = result.getString("IdPrenotazioneRistorante");

				String cliente = result.getString("Cliente");

				int tavolo = result.getInt("Tavolo");

				String data = result.getString("dataPrenotazione");
				// LocalDate dataPrenotazione = LocalDate.parse(data,
				// DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String ora = result.getString("oraPrenotazione");
				LocalDate dt = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				LocalTime t = LocalTime.parse(ora, DateTimeFormatter.ofPattern("HH:mm:ss"));
				pr = new PrenotazioneRistorante(idPrenotazione, DAOFactory.getDAOCliente().doRetrieveByCf(cliente),
						tavolo, dt, t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pr;
	}

	@Override
	public void delete(String id) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement
					.executeUpdate("DELETE FROM PRENOTAZIONIRISTORANTE WHERE IDPrenotazioneRistorante=\"" + id + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int updatePrenotazioneRistorante(PrenotazioneRistorante pr) {
		
		if(!DAOFactory.getDAOPrenotazioneAbitazione().isPrenotazioneGenericaPossibile(pr.getCliente().getCf(), pr.getData())) {
			JOptionPane.showMessageDialog(null, "Il cliente selezionato non risulta soggiornante in data: " + pr.getData());
			return 0;
		}
			
		try {

			String query = " insert into PrenotazioniRistorante ( IdPrenotazioneRistorante, Cliente, Tavolo, DataPrenotazione, OraPrenotazione)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
			preparedStmt.setString(1, pr.getIdPrenotazione());
			preparedStmt.setString(2, pr.getCliente().getCf());
			preparedStmt.setInt(3, pr.getnTavolo());
			LocalDate d = pr.getData();
			LocalTime ora = pr.getOra();
			String data = d.getYear() + "-" + d.getMonthValue() + "-" + d.getDayOfMonth();
			Date dataPrenotazione = Date.valueOf(data);
			preparedStmt.setDate(4, dataPrenotazione);
			Time t = Time.valueOf(ora);
			preparedStmt.setTime(5, t);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void deleteByCliente(String cf) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement.executeUpdate("DELETE FROM PRENOTAZIONIRISTORANTE WHERE Cliente=\"" + cf + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}