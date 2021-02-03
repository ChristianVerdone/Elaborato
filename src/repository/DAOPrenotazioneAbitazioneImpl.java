package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

import javax.swing.JOptionPane;

import struttureEventi.classes.PrenotazioneAbitazione;

public class DAOPrenotazioneAbitazioneImpl implements DAOPrenotazioneAbitazione {

	private MySQLConnection connection;

	public DAOPrenotazioneAbitazioneImpl() {
		this.connection = new MySQLConnection();
	}

	public DAOPrenotazioneAbitazioneImpl(MySQLConnection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public HashSet<PrenotazioneAbitazione> doRetrieveAll() {
		HashSet<PrenotazioneAbitazione> paCollection = new HashSet<PrenotazioneAbitazione>();
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM PRENOTAZIONIABITAZIONI");
			while (result.next()) {
				String id = result.getString("IdPrenotazioneAbitazione");
				String cliente = result.getString("Cliente");
				String abitazione = result.getString("Abitazione");
				/**
				 * String datai=result.getString("dataInizio"); LocalDate datainizio =
				 * LocalDate.parse(datai, DateTimeFormatter.ofPattern("yyyy-MM-dd")); String
				 * dataf=result.getString("dataFine"); LocalDate datafine =
				 * LocalDate.parse(datai, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				 */
				Date datai = result.getDate("DataInizio");
				LocalDate dataInizio = datai.toLocalDate().parse(datai.toString(),
						DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				Date dataf = result.getDate("DataFine");
				LocalDate dataFine = dataf.toLocalDate().parse(dataf.toString(),
						DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				PrenotazioneAbitazione a = new PrenotazioneAbitazione(id,
						DAOFactory.getDAOCliente().doRetrieveByCf(cliente),
						DAOFactory.getDAOAbitazione().doRetrieveById(abitazione), dataInizio, dataFine);
				paCollection.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paCollection;
	}

	@Override
	public PrenotazioneAbitazione doRetrieveById(String id) {
		PrenotazioneAbitazione pa = null;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement
					.executeQuery("SELECT * FROM PRENoTAZIONIABITAZIONI WHERE IDPrenotazioneAbitazione=\"" + id + "\"");
			while (result.next()) {
				String idPrenotazioneAbitazione = result.getString("IdPrenotazioneAbitazione");
				String cliente = result.getString("Cliente");
				String abitazione = result.getString("Abitazione");
				String datai = result.getString("dataInizio");
				LocalDate datainizio = LocalDate.parse(datai, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String dataf = result.getString("dataFine");
				LocalDate datafine = LocalDate.parse(dataf, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				pa = new PrenotazioneAbitazione(idPrenotazioneAbitazione,
						DAOFactory.getDAOCliente().doRetrieveByCf(cliente),
						DAOFactory.getDAOAbitazione().doRetrieveById(abitazione), datainizio, datafine);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pa;
	}

	/*
	 * Controlla se il cliente ha ancora una prenotazione ad una abitazione valida.
	 * Se s�, non ne pu� fare altre. return null = non ci sono prenotazioni
	 * valide per quel cliente, sono tutte scadute o non ce n'� neanche una
	 * registrata in passato. return object = c'� gi� una prenotazione valida
	 */
	@Override
	public PrenotazioneAbitazione doRetrivePrenotazioneValidaCliente(String cf) {
		PrenotazioneAbitazione pa = null;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement
					.executeQuery("select * from PRENOTAZIONIABITAZIONI where Cliente =\"" + cf + "\"");

			while (result.next()) {
				String idPrenotazioneAbitazione = result.getString("IdPrenotazioneAbitazione");
				String cliente = result.getString("Cliente");
				String abitazione = result.getString("Abitazione");
				String datai = result.getString("dataInizio");
				LocalDate datainizio = LocalDate.parse(datai, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String dataf = result.getString("dataFine");
				LocalDate datafine = LocalDate.parse(dataf, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

				// (oggi <= fine) == not (oggi > fine)
				if (!LocalDate.now().isAfter(datafine))
					return new PrenotazioneAbitazione(idPrenotazioneAbitazione,
							DAOFactory.getDAOCliente().doRetrieveByCf(cliente),
							DAOFactory.getDAOAbitazione().doRetrieveById(abitazione), datainizio, datafine);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Da usare per registrare altre prenotazioni (ristorante, eventi, ...).
	 * Controlla se il cliente ha ancora una prenotazione valida e se la
	 * prenotazione dell'evento cade nella prenotazione dell'abitazione. return true
	 * = la prenotazione dell'evento pu� essere registrata return false = la
	 * prenotazione dell'evento non pu� essere registrata
	 */
	@Override
	public boolean isPrenotazioneGenericaPossibile(String cf, LocalDate dataEvento) {
		PrenotazioneAbitazione pa = this.doRetrivePrenotazioneValidaCliente(cf);
		if (pa == null)
			return false;

		LocalDate datainizio = pa.getDataInizio();
		LocalDate datafine = pa.getDataFine();

		// (evento <= fine) == not (evento > fine) --> Data evento sta prima della fine
		// del soggiorno
		// (evento >= inizio) == not (evento < inizio) --> Data evento sta dopo l'inizio
		// del soggiorno
		if (!dataEvento.isAfter(datafine) && !dataEvento.isBefore(datainizio))
			return true;
		return false;
	}

	/*
	 * Serve a controllare se una struttura ha un numero di disponibilit� > 0 per
	 * un certo intervallo di date. Ho usato questo controllo: (start_vecchia_pren
	 * <= end_nuova_pren) and (end_vecchia_pren >= start_nuova_pren)
	 * 
	 * return true --> Se si pu� prenotare quella struttura ad un cliente in
	 * quella data (quindi se non c'� un numero di overlap uguale alle
	 * disponibilit�) return false --> Quella struttura non ha disponibilit� in
	 * quell'intervallo di date
	 */
	@Override
	public boolean isPrenotazioneAbitazionePossibile(PrenotazioneAbitazione pa) {
		Statement statement = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement
					.executeQuery("select count(IdPrenotazioneAbitazione) from PRENOTAZIONIABITAZIONI "
							+ "where abitazione =\"" + pa.getAbitazione().getIdAbitazione() + "\""
							+ "and ((datainizio <= '" + pa.getDataFine().format(dtf) + "') " + "and  (datafine >= '"
							+ pa.getDataInizio().format(dtf) + "'))");
			if (!result.next())
				return false;
			int count = result.getInt(1);
			int dispo = pa.getAbitazione().getAbitazioniDisponibili();

			if ((dispo - count) > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void delete(String id) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement
					.executeUpdate("DELETE FROM PRENOTAZIONIABITAZIONI WHERE IDPrenotazioneAbitazione=\"" + id + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Codici di errore: -2: Tipologia abitazione al completo in quel periodo di
	 * tempo -1: Cliente ha gi� una prenotazione valida registrata, non pu�
	 * effettuarne altre. 0: Errore generico
	 */
	public int updatePrenotazioneAbitazione(PrenotazioneAbitazione pa) {
		try {
			if (this.doRetrivePrenotazioneValidaCliente(pa.getCliente().getCf()) != null)
				return -1;
			if (!this.isPrenotazioneAbitazionePossibile(pa))
				return -2;

			String query = " insert into PrenotazioniAbitazioni ( IdPrenotazioneAbitazione, Cliente, Abitazione, DataInizio, DataFine)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
			preparedStmt.setString(1, pa.getIdPrenotazione());
			preparedStmt.setString(2, pa.getCliente().getCf());
			preparedStmt.setString(3, pa.getAbitazione().getIdAbitazione());
			LocalDate datai = pa.getDataInizio();
			Date dataInizio = Date.valueOf(datai);
			LocalDate dataf = pa.getDataFine();
			Date dataFine = Date.valueOf(dataf);
			preparedStmt.setDate(4, dataInizio);
			preparedStmt.setDate(5, dataFine);
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
			int result = statement.executeUpdate("DELETE FROM PRENOTAZIONIABITAZIONI WHERE Cliente=\"" + cf + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PrenotazioneAbitazione doRetrieveByCF(String cf) {
		PrenotazioneAbitazione pa = null;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement
					.executeQuery("select * from PRENOTAZIONIABITAZIONI where Cliente =\"" + cf + "\"");

			while (result.next()) {
				String idPrenotazioneAbitazione = result.getString("IdPrenotazioneAbitazione");
				String cliente = result.getString("Cliente");
				String abitazione = result.getString("Abitazione");
				String datai = result.getString("dataInizio");
				LocalDate datainizio = LocalDate.parse(datai, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String dataf = result.getString("dataFine");
				LocalDate datafine = LocalDate.parse(dataf, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				pa = new PrenotazioneAbitazione(idPrenotazioneAbitazione, 
						DAOFactory.getDAOCliente().doRetrieveByCf(cliente),
						DAOFactory.getDAOAbitazione().doRetrieveById(abitazione), datainizio, datafine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pa;
	}
}