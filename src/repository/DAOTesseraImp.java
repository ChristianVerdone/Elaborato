package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import struttureEventi.classes.Tessera;

public class DAOTesseraImp implements DAOTessera {
	private MySQLConnection connection;

	public DAOTesseraImp() {
		this.connection = new MySQLConnection();
	}

	public DAOTesseraImp(MySQLConnection connection) {
		super();
		this.connection = connection;
	}
	
	@Override
	public HashMap<String, Tessera> doRetrieveAll() {
		HashMap<String, Tessera> tessereCollection = new HashMap<>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM TESSERE");
			 
			while (result.next()) {
				String id=result.getString("IdTessera");
				String descrizione=result.getString("DescrizioneTessera");
				Tessera t = new Tessera(id, descrizione);
				tessereCollection.put(id, t);
			

		}} catch (SQLException e) {
			e.printStackTrace();
		}
		return tessereCollection;
	}

	@Override
	public Tessera doRetrieveById(String id) {
		Tessera t=null;;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM TESSERE WHERE IdTessera=\""+ id + "\"");
			 
			while (result.next()) {
				String idTessera=result.getString("IdTessera");
				String descrizione=result.getString("DescrizioneTessera");
				t = new Tessera(idTessera, descrizione);
			

		}} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	@Override
	public HashMap<String, Tessera> doRetriveTessereByCf(String cf) {
		HashMap<String, Tessera> tessereCollection = new HashMap<>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("select t.* from TESSERE as t "
					+ "inner join PRENOTAZIONISTRUTTURE as p on t.idtessera = p.Tessera "
					+ "where p.Cliente = '" + cf +"';");

			while (result.next()) {
				String id=result.getString("IdTessera");
				String descrizione=result.getString("DescrizioneTessera");
				Tessera t = new Tessera(id, descrizione);
				tessereCollection.put(id, t);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tessereCollection;
	}
	
	@Override
	public void delete(String id) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement.executeUpdate("DELETE FROM  TESSERE WHERE IdTessera=\""+ id + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public int deleteTessereByCf(String cf) {
		int result = 0;
		try {
			Statement statement = connection.getConnection().createStatement();
			result = statement.executeUpdate("delete t.* from TESSERE as t "
					+ "inner join PRENOTAZIONISTRUTTURE as p on t.idtessera = p.Tessera "
					+ "where p.Cliente = '" +cf +"';");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateTessera(Tessera t) {
		try {
			
			
			String query = " insert into Tessere ( IdTessera, DescrizioneTessera)"
					+ " values (?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
			preparedStmt.setString(1, t.getId());
			preparedStmt.setString(2, t.getDescrizione());
			

			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}