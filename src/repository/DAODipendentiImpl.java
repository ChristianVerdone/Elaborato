package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import javax.swing.JOptionPane;

import personale.model.Account;
import personale.model.Dipendente;
import personale.model.Account.Permessi;

public class DAODipendentiImpl implements DAODipendenti {

	private MySQLConnection connection;

	public DAODipendentiImpl() {
		this.connection = new MySQLConnection();
	}

	public DAODipendentiImpl(MySQLConnection connection) {
		super();
		this.connection = connection;
	}
	
	@Override
	public HashSet<Dipendente> doRetrieveAll() {
		HashSet<Dipendente> dipendentiSet = new HashSet<Dipendente>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM dipendenti");
			
			while (result.next()) {
				String cf = result.getString("CFiscale");
				String nome = result.getString("Nome");
				String cognome = result.getString("Cognome");
				String mansione = result.getString("Mansione");
				Integer stipendio = result.getInt("Stipendio");
				dipendentiSet.add(new Dipendente(cf, nome, cognome, mansione, stipendio));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dipendentiSet;
	}

	@Override
	public Dipendente doRetrieveByCf(String cf) {
		Dipendente dip = null;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM dipendenti WHERE CFiscale=\"" + cf + "\"");

			while (result.next()) {
				String cod = result.getString("CFiscale");
				String nome = result.getString("Nome");
				String cognome = result.getString("Cognome");
				String mansione = result.getString("Mansione");
				Integer stipendio = result.getInt("Stipendio");
				dip = new Dipendente(cod, nome, cognome, mansione, stipendio);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dip;
	}

	@Override
	public int delete(String tipo) {
		return 0;
		
	}

	@Override
	public int update(Dipendente dip) {
		try {
			
			String query = "INSERT INTO dipendenti (CFiscale, Nome, Cognome, Mansione, Stipendio)" + " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);

			preparedStmt.setString(1, dip.getCf());
			preparedStmt.setString(2, dip.getNome());
			preparedStmt.setString(3, dip.getCognome());
			preparedStmt.setString(4, dip.getMansione());
			preparedStmt.setInt(5, dip.getStipendio());
			
			//Ritorna il numero di righe manipolate
			return preparedStmt.executeUpdate();
			
			
			/*Statement stm = connection.getConnection().createStatement();
			stm.executeUpdate("INSERT INTO dipendenti VALUES" + 
					"(\""+ dip.getCf() + "\", \"" + dip.getNome() + 
					"\", \"" + dip.getCognome() + "\", \""+ dip.getMansione() +
					"\", "+dip.getStipendio()+"),");*/
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return 0;
	}
}
