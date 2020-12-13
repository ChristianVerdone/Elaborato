package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import struttureEventi.classes.Evento;
import struttureEventi.classes.Movimento;

public class DAOMovimentoImpl implements DAOMovimento {

	private MySQLConnection connection;

	public DAOMovimentoImpl() {
		this.connection = new MySQLConnection();
	}

	public DAOMovimentoImpl(MySQLConnection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public int updateMovimento(Movimento m) {
		try {
			String query = " insert into movimenti (Tessera, Lettore, Tipo)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
			preparedStmt.setString(1, m.getIdTessera());
			preparedStmt.setString(2, m.getIdLettore());
			preparedStmt.setBoolean(3, m.getTipo());
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<Movimento> doRetrieveAll() {
		ArrayList<Movimento> movimentiCollection = new ArrayList<Movimento>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM MOVIMENTI");
			while (result.next()) {
				String tessera = result.getString("Tessera");
				String lettore=result.getString("Lettore");
				boolean tipo=result.getBoolean("Tipo");
				Movimento m = new Movimento(tessera, lettore, tipo);
				movimentiCollection.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movimentiCollection;
	}
}