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
import java.util.ArrayList;
import java.util.HashMap;
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
	public ArrayList<PrenotazioneRistorante> doRetrieveAll() {
		ArrayList<PrenotazioneRistorante> prCollection = new ArrayList<PrenotazioneRistorante>();
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
	
	@Override
	public HashSet<PrenotazioneRistorante> doRetrieveByCliente(String cf) {
		HashSet<PrenotazioneRistorante> pr = new HashSet<>();
		Statement statement = null;

		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement
					.executeQuery("SELECT * FROM PRENOTAZIONIRISTORANTE WHERE CLIENTE=\"" + cf + "\"");

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
				PrenotazioneRistorante pren = new PrenotazioneRistorante(idPrenotazione, DAOFactory.getDAOCliente().doRetrieveByCf(cliente),
						tavolo, dt, t);
				pr.add(pren);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pr;
	}
	
	/*
	 * Controlla se cliente è prenotato al ristorante per data e periodo di servizio (pranzo o cena)
	 * */
	@Override
	public boolean isClientePrenotatoRistorante(String cf, LocalDate date, LocalTime time) {
		HashSet<PrenotazioneRistorante> pren =  this.doRetrieveByCliente(cf);
		boolean periodo_input = this.isPranzo(time.getHour());
		
		for(PrenotazioneRistorante p : pren) {
			if(!date.isEqual(p.getData())) continue;
			boolean periodo_pren = this.isPranzo(p.getOra().getHour());
			if(periodo_input == periodo_pren) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * True: Pranzo 
	 * False: Cena
	 * */
	private boolean isPranzo(int x) {
		if(x <= 14) return true;
		return false;
	}

	public int updatePrenotazioneRistorante(PrenotazioneRistorante pr) {
		
		if(!DAOFactory.getDAOPrenotazioneAbitazione().isPrenotazioneGenericaPossibile(pr.getCliente().getCf(), pr.getData()))
			return -1;
		
		if(this.isClientePrenotatoRistorante(pr.getCliente().getCf(), pr.getData(), pr.getOra())) {
			return -2;
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
	public ArrayList<PrenotazioneRistorante> doRetrievePrenotazioniNonRegistrate() {
		ArrayList<PrenotazioneRistorante> prCollection = new ArrayList<PrenotazioneRistorante>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("select * from prenotazioniristorante as p "
					+ "where p.IdPrenotazioneRistorante not in "
					+ "(select c.PrenotazioneRistorante from contiristorante as c);");

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

	@Override
	public void deleteByCliente(String cf) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement.executeUpdate("DELETE FROM PRENOTAZIONIRISTORANTE WHERE Cliente=\"" + cf + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int deletePrenotazioniNonRegistrate(String cf) {
		int result = 0;
		try {
			Statement statement = connection.getConnection().createStatement();
			result = statement.executeUpdate("delete p.* from prenotazioniristorante as p "
					+ "where p.IdPrenotazioneRistorante not in "
					+ "(select c.PrenotazioneRistorante from contiristorante as c);");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}