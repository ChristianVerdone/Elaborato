package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JOptionPane;

import personale.model.Servizio;
import personale.model.TurnoLavoro;

public class DAOTurniLavoroImpl implements DAOTurniLavoro{

	private MySQLConnection connection;

	public DAOTurniLavoroImpl() {
		this.connection = new MySQLConnection();
	}

	public DAOTurniLavoroImpl(MySQLConnection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public HashSet<TurnoLavoro> doRetrieveAll() {
		HashSet<TurnoLavoro> set_turni = new HashSet<TurnoLavoro>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT s.*, t.dipendente, t.datainizioturno FROM servizi s " +
					"INNER JOIN turnidilavoro t ON t.servizio = s.idservizio");

			while (result.next()) {
				String ser_id = result.getString("IdServizio");
				String ser_desc = result.getString("DescrizioneServizio");
				LocalTime ser_start = result.getTime("oraInizio").toLocalTime();
				LocalTime ser_end = result.getTime("oraFine").toLocalTime();
				String tl_dip = result.getString("dipendente");
				LocalDate tl_start = result.getDate("DataInizioTurno").toLocalDate();
				set_turni.add(new TurnoLavoro(tl_dip, tl_start, new Servizio(ser_id, ser_desc, ser_start, ser_end)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set_turni;
	}

	@Override
	public HashMap<String, Servizio> doRetrieveAllServizi() {
		HashMap<String, Servizio> map_ser = new HashMap<String, Servizio>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM servizi");

			while (result.next()) {
				String id = result.getString("idServizio");
				String desc = result.getString("DescrizioneServizio");
				LocalTime start = result.getTime("oraInizio").toLocalTime();
				LocalTime end = result.getTime("oraFine").toLocalTime();
				map_ser.put(id, new Servizio(id, desc, start, end));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map_ser;
	}

	@Override
	public HashSet<TurnoLavoro> doRetrieveByCf(String cf) {
		HashSet<TurnoLavoro> set_turni = new HashSet<TurnoLavoro>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT s.*, t.dipendente, t.datainizioturno FROM servizi s " +
					"INNER JOIN turnidilavoro t ON t.servizio = s.idservizio WHERE t.dipendente = '"+cf+"'");

			while (result.next()) {
				String ser_id = result.getString("IdServizio");
				String ser_desc = result.getString("DescrizioneServizio");
				LocalTime ser_start = result.getTime("oraInizio").toLocalTime();
				LocalTime ser_end = result.getTime("oraFine").toLocalTime();
				String tl_dip = result.getString("dipendente");
				LocalDate tl_start = result.getDate("DataInizioTurno").toLocalDate();
				set_turni.add(new TurnoLavoro(tl_dip, tl_start, new Servizio(ser_id, ser_desc, ser_start, ser_end)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set_turni;
	}
	
	@Override
	public boolean checkConflicts(TurnoLavoro tl) {
	
	Statement statement = null;
	try {
		statement = connection.getConnection().createStatement();
		ResultSet result = statement.executeQuery("select * from turnidilavoro where dipendente = '" + tl.getDip() + "'");

		while (result.next()) {
			LocalDate tl_start = result.getDate("DataInizioTurno").toLocalDate();
			if(tl.getInizio().isEqual(tl_start)) return true;
		}

	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
	}

	@Override
	public int update(TurnoLavoro tl) {
		
		try {
			String query_tl = "INSERT INTO turnidilavoro (Dipendente, Servizio, DataInizioTurno)" + " values (?, ?, ?)";
			PreparedStatement prepStm_tl = connection.getConnection().prepareStatement(query_tl);

			prepStm_tl.setString(1, tl.getDip());
			prepStm_tl.setString(2, tl.getServizio().getId());
			prepStm_tl.setDate(3, java.sql.Date.valueOf(tl.getInizio()));

			//Ritorna il numero di righe manipolate
			return prepStm_tl.executeUpdate();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return 0;
	}

	/* Si può usare anche con from = null */
	@Override
	public HashSet<TurnoLavoro> doRetrieveByUsernameAndDate(String username, LocalDate from) {
		HashSet<TurnoLavoro> set_turni = new HashSet<TurnoLavoro>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			String query = "SELECT s.*, t.dipendente, t.datainizioturno FROM servizi s "
					+ "INNER JOIN turnidilavoro t ON t.servizio = s.idservizio "
					+ "INNER JOIN accounts a ON a.dipendente = t.dipendente "
					+ "WHERE a.username = '" + username + "'";
			
			if(from != null) {
				query += " AND DataInizioTurno >= '" +from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
			}
			
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				String ser_id = result.getString("IdServizio");
				String ser_desc = result.getString("DescrizioneServizio");
				LocalTime ser_start = result.getTime("oraInizio").toLocalTime();
				LocalTime ser_end = result.getTime("oraFine").toLocalTime();
				String tl_dip = result.getString("dipendente");
				LocalDate tl_start = result.getDate("DataInizioTurno").toLocalDate();
				set_turni.add(new TurnoLavoro(tl_dip, tl_start, new Servizio(ser_id, ser_desc, ser_start, ser_end)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set_turni;
	}
	
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
	}

}
