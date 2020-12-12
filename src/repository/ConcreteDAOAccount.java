package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import javax.swing.JOptionPane;

import personale.model.Account;
import personale.model.Account.Permessi;

public class ConcreteDAOAccount implements DAOAccount {
	
	private MySQLConnection connection;

	public ConcreteDAOAccount() {
		this.connection = new MySQLConnection();
	}

	public ConcreteDAOAccount(MySQLConnection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public HashSet<Account> doRetrieveAll() {
		HashSet<Account> accountSet = new HashSet<Account>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM accounts");
			
			while (result.next()) {
				String usn = result.getString("Username");
				String pwd = result.getString("PassDipendente");
				Permessi prm = Account.getPermessiFromInt(result.getInt("Permessi"));			
				accountSet.add(new Account(usn, pwd, prm));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountSet;
	}

	@Override
	public Account doRetrieveByUsername(String username) {
		Account acc = null;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM accounts WHERE username=\"" + username + "\"");

			while (result.next()) {
				String usn = result.getString("Username");
				String pwd = result.getString("PassDipendente");
				Permessi prm = Account.getPermessiFromInt(result.getInt("Permessi"));			
				acc = new Account(usn, pwd, prm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acc;
	}

	/* Per ora non considero la possibilità di rimuovere un account */
	@Override
	public int delete(String username) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement.executeUpdate("DELETE FROM accounts WHERE Username=" + username);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(String cf, Account account) {	
		try {
			
			String query = "INSERT INTO accounts (Username, Dipendente, PassDipendente, Permessi)" + " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);

			preparedStmt.setString(1, account.getUsername());
			preparedStmt.setString(2, cf);
			preparedStmt.setString(3, account.getPassword());
			preparedStmt.setInt(4, Account.getIntFromPermessi(account.getTipologiaPermessi()));
			
			//Ritorna il numero di righe manipolate
			return preparedStmt.executeUpdate();
			
			/*Statement stm = connection.getConnection().createStatement();
			stm.executeUpdate("INSERT INTO ACCOUNTS " + 
					"VALUES (\""+ account.getUsername() +" \", \"" +  cf + "\", \"" +
					account.getPassword()+"\", " + getIntFromPermessi(account.getTipologiaPermessi()) +"),");*/
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return 0;
	}
}
