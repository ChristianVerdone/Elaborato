package repository;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import struttureEventi.classes.Evento;


public class DAOEventoImpl implements DAOEvento {
	private MySQLConnection connection;
	public DAOEventoImpl() {
		this.connection = new MySQLConnection();
	}

	public DAOEventoImpl(MySQLConnection connection) {
		super();
		this.connection = connection;
	}
	@Override
	public HashMap<String, Evento> doRetrieveAll() {
		HashMap<String, Evento> eventiCollection = new HashMap<String, Evento>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM EVENTI");
			while (result.next()) {
				String id = result.getString("IdEvento");
				String nome=result.getString("Nome");
				String tipo=result.getString("Tipo");
				String descrizione=result.getString("Descrizione");
				String data=result.getString("dataEvento");
				//LocalDate dataPrenotazione = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String ora=result.getString("oraEvento");
				LocalDateTime dt=LocalDateTime.parse(data+" " + ora, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				Evento e = new Evento(id, nome, tipo, descrizione, dt);
				eventiCollection.put(id, e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventiCollection;
	}
	
	@Override
	public Evento doRetrieveById(String id) {
		Evento ev = null;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM EVENTI WHERE IdEvento=\"" + id + "\"");

			while (result.next()) {
				String idEvento = result.getString("IdEvento");
				String nome=result.getString("Nome");
				String tipo=result.getString("Tipo");
				String descrizione=result.getString("Descrizione");
				String data=result.getString("dataEvento");
				//LocalDate dataPrenotazione = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String ora=result.getString("oraEvento");
				LocalDateTime dt=LocalDateTime.parse(data+" " + ora, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				ev= new Evento(idEvento, nome, tipo, descrizione, dt);
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ev;
	}

	@Override
	public void delete(String id) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement.executeUpdate("DELETE FROM EVENTI WHERE IdEvento=\"" + id + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	


	@Override
	public int updateEvento(Evento ev) {
		try {
			//delete(c.getCf());
			
			String query = " insert into eventi (IdEvento, Nome, Tipo, Descrizione, DataEvento, OraEvento)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
			preparedStmt.setString(1, ev.getIdEvento());
			preparedStmt.setString(2, ev.getNome());
			preparedStmt.setString(3, ev.getTipo());
			preparedStmt.setString(4, ev.getDescrizione());
			LocalDateTime d=ev.getDataEvento();
			String data = d.getYear() + "-" + d.getMonthValue() + "-" +d.getDayOfMonth();
			Date dataEvento = Date.valueOf(data);
			preparedStmt.setDate(5, dataEvento);
			//String o= d.toString().substring(11, 15);
			//LocalTime hour= LocalTime.parse(d.getHour()+":"+d.getMinute()+":" + d.getSecond() , DateTimeFormatter.ofPattern("HH:mm:ss"));
			//Time ora= Time.valueOf(hour);
			String str= d.getHour()+":"+d.getMinute()+":" + d.getSecond();
			Time ora = Time.valueOf(str);
			preparedStmt.setTime(6, ora);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
