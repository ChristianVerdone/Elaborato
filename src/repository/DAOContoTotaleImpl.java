package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

import contabilità.ContoTotale;

public class DAOContoTotaleImpl implements DAOContoTotale{
	private MySQLConnection connection;
	
	public DAOContoTotaleImpl() {
		this.connection = new MySQLConnection();
	}
	
	public DAOContoTotaleImpl(MySQLConnection connection) {
		super();
		this.connection = connection;
	}
	@Override
	public double doRetrieveContoAbitazioneByCf(String cf) {
		double contoAbitazione = 0;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("select distinct  abitazioni.Tariffa,prenotazioniabitazioni.DataInizio,prenotazioniabitazioni.DataFine\r\n"
					+ "from abitazioni,prenotazioniabitazioni,clienti\r\n"
					+ "where prenotazioniabitazioni.Cliente=(select clienti.CodiceFiscale from clienti where clienti.CodiceFiscale="+"'"+cf+"'"+")"
					+ "and prenotazioniabitazioni.Abitazione=abitazioni.IdAbitazione");
			
			while (result.next()) {
				double contoLetto=result.getDouble("Tariffa");
				String datai=result.getString("DataInizio");
				LocalDate datainizio = LocalDate.parse(datai, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String dataf=result.getString("DataFine");
				LocalDate datafine = LocalDate.parse(dataf, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				
				
				GregorianCalendar initialDate = new GregorianCalendar(datainizio.getYear(),datainizio.getMonthValue(),datainizio.getDayOfMonth());
				GregorianCalendar finalDate = new GregorianCalendar(datafine.getYear(),datafine.getMonthValue(),datafine.getDayOfMonth());
				long  giorniInMillisecondi=finalDate.getTimeInMillis()-initialDate.getTimeInMillis();
				double giorniSoggiornoDouble=giorniInMillisecondi/(24*60*60*1000);
				int giorniSoggiorno=(int) giorniSoggiornoDouble;
				contoAbitazione=giorniSoggiorno*contoLetto;
				
				try {
					if(datafine.isBefore(datainizio)) {
						throw new DateTimeException("Errore nell'inserimento della data");
					}
				}catch(DateTimeException e) {
					e.getMessage();
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contoAbitazione;
	}
	
	

	@Override
	public double doRetrieveContoRistoranteCf(String cf) {
		double contoRistorante = 0;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("select distinct contiristorante.ContoTotale\r\n"
					+ "from contiristorante,prenotazioniristorante,tavoliristorante\r\n"
					+ "where prenotazioniristorante.Cliente=(select clienti.CodiceFiscale from clienti where clienti.CodiceFiscale="+"'"+cf+"'"+") and\r\n"
					+ "prenotazioniristorante.ContoRistorante=contiristorante.IdConto and prenotazioniristorante.TavoloRistorante=tavoliristorante.NumeroTavolo");
			double contoParziale=0;
			while (result.next()) {
				double contoLetto=result.getDouble("ContoTotale");
				contoParziale=contoParziale+contoLetto;
			}
			contoRistorante=contoParziale;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contoRistorante;
	}

	@Override
	public double doRetrieveContoEventoByCf(String cf) {
		double contoEvento = 0;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("select distinct sum(biglietti.Costo)"
					+ "	from biglietti, prenotazionieventi, eventi "
					+ "	where prenotazionieventi.Cliente=(select clienti.CodiceFiscale from clienti where clienti.CodiceFiscale="+"'"+cf+"'"+" )and prenotazionieventi.Biglietto=biglietti.IdBiglietto and prenotazionieventi.Evento=eventi.IdEvento");
			double contoParziale=0;
			while (result.next()) {
				double contoLetto=result.getDouble("sum(biglietti.Costo)");
				contoParziale=contoParziale+contoLetto;
			}
			contoEvento=contoParziale;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contoEvento;
	}

	@Override
	public double doRetrieveContoStrutturaCf(String cf) {
		double contoStruttura = 0;
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("select sum(strutturevillaggio.Tariffa)\r\n"
					+ "from strutturevillaggio\r\n"
					+ "Inner join prenotazionistrutture on prenotazionistrutture.Struttura = strutturevillaggio.IdStruttura\r\n"
					+ "inner join clienti on clienti.CodiceFiscale = prenotazionistrutture.Cliente\r\n"
					+ "inner join tessere on prenotazionistrutture.Tessera =tessere.IdTessera\r\n"
					+ "where clienti.CodiceFiscale="+"'"+cf+"'");
			double contoParziale=0;
			while (result.next()) {
				double contoLetto=result.getDouble("sum(strutturevillaggio.Tariffa)");
				contoParziale=contoParziale+contoLetto;
			}
			contoStruttura=contoParziale;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contoStruttura;
	}

	@Override
	public int updateContiTotali(ContoTotale conto) {
		try {
			
			String numeroConto=conto.getNumeroConto();
			double importo=conto.getImporto();
			LocalDate data=conto.getDataPagamento();
			Date dataPagamento = Date.valueOf(data);
			String codiceFiscale=conto.getCliente();
			
			String query = " insert into contitotali ( NumeroConto, Importo, DataPagamento, Cliente)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement pstmt = connection.getConnection().prepareStatement(query);
			
            pstmt.setString(1, numeroConto);
            pstmt.setDouble(2, importo);
            pstmt.setDate(3, dataPagamento);
            pstmt.setString(4, codiceFiscale);
            return pstmt.executeUpdate();
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
