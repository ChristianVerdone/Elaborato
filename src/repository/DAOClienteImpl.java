package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import contabilita.Cliente;

public class DAOClienteImpl implements DAOCliente {
	private MySQLConnection connection;

	public DAOClienteImpl() {
		this.connection = new MySQLConnection();
	}

	public DAOClienteImpl(MySQLConnection connection) {
		super();
		this.connection = connection;
	}
	@Override
	public HashMap<String, Cliente> doRetrieveAll() {
		HashMap<String, Cliente> clientiCollection = new HashMap<String, Cliente>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM CLIENTI");

			while (result.next()) {
				String codf = result.getString("codicefiscale");
				String nome=result.getString("nome");
				String cognome=result.getString("cognome");
				Cliente c = new  Cliente(codf, nome, cognome);
				clientiCollection.put(codf, c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientiCollection;
	}
	
	/*
	 * Recupera solo i clienti con una prenotazione valida e con soggiorno già iniziato
	 * Se li vuoi recuperare anche se il loro soggiorno non è ancora cominciato, basta togliere: "and (curdate() >= p.datainizio)"
	 * */
	@Override
	public HashMap<String, Cliente> doRetrieveClientiPrenotati() {
		HashMap<String, Cliente> clientiCollection = new HashMap<String, Cliente>();
		Statement statement = null;
		try {
			
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("select CodiceFiscale, Nome, Cognome from CLIENTI as C "
					                                + "inner join PrenotazioniAbitazioni as P on C.CodiceFiscale = P.Cliente "
					                                + "where (curdate() <= p.datafine) and (curdate() >= p.datainizio)");

			while (result.next()) {
				String codf = result.getString("codicefiscale");
				String nome=result.getString("nome");
				String cognome=result.getString("cognome");
				Cliente c = new  Cliente(codf, nome, cognome);
				clientiCollection.put(codf, c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientiCollection;
	}

	@Override
	public Cliente doRetrieveByCf(String cf) {
		Cliente c = null;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM CLIENTI WHERE CodiceFiscale=\"" + cf + "\"");

			while (result.next()) {
				String codF = result.getString("codiceFiscale");
				String nome=result.getString("nome");
				String cognome=result.getString("cognome");
				c= new Cliente(codF, nome, cognome); 
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public void delete(String cf) {
		try {
			System.out.println(cf);
			Statement statement = connection.getConnection().createStatement();
			int result = statement.executeUpdate("DELETE FROM CLIENTI WHERE CodiceFiscale=\"" + cf + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	


	@Override
	public int updateCliente(Cliente c) {
		try {
			//delete(c.getCf());
			
			String query = " insert into clienti (CodiceFiscale, Nome, Cognome)"
					+ " values (?, ?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
			preparedStmt.setString(1, c.getCf());
			preparedStmt.setString(2, c.getNome());
			preparedStmt.setString(3, c.getCognome());
		
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
