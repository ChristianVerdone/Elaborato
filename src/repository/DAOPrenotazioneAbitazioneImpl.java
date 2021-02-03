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
				String id=result.getString("IdPrenotazioneAbitazione");
				String cliente=result.getString("Cliente");
				String abitazione=result.getString("Abitazione");
				/**String datai=result.getString("dataInizio");
				LocalDate datainizio = LocalDate.parse(datai, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String dataf=result.getString("dataFine");
				LocalDate datafine = LocalDate.parse(datai, DateTimeFormatter.ofPattern("yyyy-MM-dd"));*/
				Date datai=result.getDate("DataInizio");
				LocalDate dataInizio = datai.toLocalDate().parse(datai.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				Date dataf=result.getDate("DataFine");
				LocalDate dataFine = dataf.toLocalDate().parse(dataf.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				PrenotazioneAbitazione a = new PrenotazioneAbitazione( id,DAOFactory.getDAOCliente().doRetrieveByCf(cliente), DAOFactory.getDAOAbitazione().doRetrieveById(abitazione), dataInizio, dataFine);
				paCollection.add(a);


			}} catch (SQLException e) {
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
			ResultSet result = statement.executeQuery("SELECT * FROM PRENoTAZIONIABITAZIONI WHERE IDPrenotazioneAbitazione=\"" + id + "\"");

			while (result.next()) {
				String idPrenotazioneAbitazione = result.getString("IdPrenotazioneAbitazione");
				String cliente=result.getString("Cliente");
				String abitazione=result.getString("Abitazione");
				String datai=result.getString("dataInizio");
				LocalDate datainizio = LocalDate.parse(datai, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String dataf=result.getString("dataFine");
				LocalDate datafine = LocalDate.parse(dataf, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				pa = new PrenotazioneAbitazione(idPrenotazioneAbitazione, DAOFactory.getDAOCliente().doRetrieveByCf(cliente), DAOFactory.getDAOAbitazione().doRetrieveById(abitazione), datainizio, datafine);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pa;
	}

	/* Controlla se il cliente ha ancora una prenotazione ad una struttura valida. Se sì, non ne può fare altre.
	 * return null = non ci sono prenotazioni valide per quel cliente, sono tutte scadute o non ce n'è neanche una registrata in passato.
	 * return object = c'è già una prenotazione valida */
	@Override
	public PrenotazioneAbitazione doRetrivePrenotazioneValidaCliente(String cf) {
		PrenotazioneAbitazione pa = null;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("select * from PRENOTAZIONIABITAZIONI where Cliente =\"" + cf + "\"");

			while (result.next()) {
				String idPrenotazioneAbitazione = result.getString("IdPrenotazioneAbitazione");
				String cliente=result.getString("Cliente");
				String abitazione=result.getString("Abitazione");
				String datai=result.getString("dataInizio");
				LocalDate datainizio = LocalDate.parse(datai, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String dataf=result.getString("dataFine");
				LocalDate datafine = LocalDate.parse(dataf, DateTimeFormatter.ofPattern("yyyy-MM-dd"));


				//(fine <= oggi)   ==   not (fine > oggi) 
				//(oggi >= inizio) ==   not (oggi < inizio)
				if(!datafine.isAfter(LocalDate.now()) && !datafine.isBefore(LocalDate.now()))
					return new PrenotazioneAbitazione(idPrenotazioneAbitazione, DAOFactory.getDAOCliente().doRetrieveByCf(cliente), 
							DAOFactory.getDAOAbitazione().doRetrieveById(abitazione), datainizio, datafine);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* Da usare per registrare altre prenotazioni (ristorante, eventi, ...). 
	 * Controlla se il cliente ha ancora una prenotazione valida e se la prenotazione dell'evento cade nella prenotazione della struttura.
	 * return true = la prenotazione dell'evento può essere registrata
	 * return false = la prenotazione dell'evento non può essere registrata */
	@Override
	public boolean isPrenotazioneGenericaPossibile(String cf, LocalDate dataEvento) {
		PrenotazioneAbitazione pa = this.doRetrivePrenotazioneValidaCliente(cf);
		if(pa == null) return false;

		LocalDate datainizio = pa.getDataInizio();
		LocalDate datafine = pa.getDataFine();
		
		//(fine <= evento)   ==   not (fine > evento) 
		//(evento >= inizio) ==   not (evento < inizio)
		if(!datafine.isAfter(dataEvento) && !datafine.isBefore(dataEvento))
			return true;
		return false;
	}
	
	/*
	 * Serve a controllare se una struttura ha un numero di disponibilità > 0 per un certo intervallo di date.
	 * Ho usato questo controllo: (start_vecchia_pren <= end_nuova_pren) 
	 *                             and 
	 *                            (end_vecchia_pren >= start_nuova_pren)
	 *                            
	 * return true --> Se si può prenotare quella struttura ad un cliente in quella data (quindi se non c'è un numero di overlap uguale alle disponibilità)
	 * return false --> Quella struttura non ha disponibilità in quell'intervallo di date
	 * */
	@Override
	public boolean isPrenotazioneStrutturaPossibile(PrenotazioneAbitazione pa) {
		Statement statement = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("select count(IdPrenotazioneAbitazione) from PRENOTAZIONIABITAZIONI "
					                                + "where abitazione =\"" + pa.getAbitazione().getIdAbitazione() + "\""
					                                + "and ((datainizio <= '"+ pa.getDataFine().format(dtf) +"') "
					                                + "and  (datafine >= '" + pa.getDataInizio() .format(dtf)+ "'))");
			if(!result.next()) return false;
			int count = result.getInt(1);
			int dispo = pa.getAbitazione().getAbitazioniDisponibili();
			
			if((dispo - count) > 0) return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void delete(String id) {
		try {
			Statement statement = connection.getConnection().createStatement();
			int result = statement.executeUpdate("DELETE FROM PRENOTAZIONIABITAZIONI WHERE IDPrenotazioneAbitazione=\"" + id + "\"");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* Codici di errore:
	 * -2: Tipologia struttura al completo in quel periodo di tempo
	 * -1: Cliente ha già una prenotazione valida registrata, non può effettuarne altre. 
	 *  0: Errore generico */
	public int updatePrenotazioneAbitazione(PrenotazioneAbitazione pa) {
		try {
			if(this.doRetrivePrenotazioneValidaCliente(pa.getCliente().getCf()) != null) return -1;
			if(!this.isPrenotazioneStrutturaPossibile(pa)) return -2;

			String query = " insert into PrenotazioniAbitazioni ( IdPrenotazioneAbitazione, Cliente, Abitazione, DataInizio, DataFine)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
			preparedStmt.setString(1, pa.getIdPrenotazione());
			preparedStmt.setString(2, pa.getCliente().getCf());
			preparedStmt.setString(3, pa.getAbitazione().getIdAbitazione());
			LocalDate datai=pa.getDataInizio();
			Date dataInizio = Date.valueOf(datai);
			LocalDate dataf=pa.getDataFine();
			Date dataFine = Date.valueOf(dataf);
			preparedStmt.setDate(4,  dataInizio);
			preparedStmt.setDate(5,  dataFine);
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
}
