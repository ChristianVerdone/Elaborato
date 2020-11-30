package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

import struttureEventi.classes.PrenotazioneEvento;
import struttureEventi.classes.Tavolo;

public class DAOTavoloRistoranteImp implements DAOTavoloRistorante {
	private MySQLConnection connection;

	public DAOTavoloRistoranteImp() {
		this.connection = new MySQLConnection();
	}

	public DAOTavoloRistoranteImp(MySQLConnection connection) {
		super();
		this.connection = connection;
	}
	
	@Override
	public HashMap<Integer, Tavolo> doRetrieveAll() {
		HashMap<Integer, Tavolo> tavoliCollection = new HashMap<>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM TAVOLIRISTORANTE");
			 
			while (result.next()) {
				int nTavolo=result.getInt("NumeroTavolo");
				int nPosti=result.getInt("NumeroPosti");
				Tavolo t= new Tavolo(nTavolo, nPosti);
				tavoliCollection.put(nTavolo, t);
			

		}} catch (SQLException e) {
			e.printStackTrace();
		}
		return tavoliCollection;
	}

	@Override
	public Tavolo doRetrieveByNTavolo(int n) {
		Tavolo t=null;;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM TAVOLI WHERE NumeroTavolo=\""+ n + "\"");
			 
			while (result.next()) {
				int nTavolo=result.getInt("NumeroTavolo");
				int nPosti=result.getInt("NumeroPosti");
				t= new Tavolo(nTavolo, nPosti);
	
			

		}} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
	@Override
	public void delete(int nTavolo) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement.executeUpdate("DELETE FROM  TAVOLI WHERE NumeroTavolo=\""+ nTavolo + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int updateTavolo(Tavolo t) {
		try {
			
			
			String query = " insert into Tavoli ( NumeroTavolo, NumeroPosti)"
					+ " values (?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
			preparedStmt.setInt(1, t.getnTavolo());
			preparedStmt.setInt(2, t.getPosti());
			

			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
