package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;
import java.util.HashSet;

import javax.swing.JOptionPane;

import contabilita.ContoTotale;
import struttureEventi.classes.PrenotazioneRistorante;

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
				
				long days= ChronoUnit.DAYS.between(datainizio, datafine);
			
			
				//GregorianCalendar initialDate = new GregorianCalendar(datainizio.getYear(),datainizio.getMonthValue(),datainizio.getDayOfMonth());
				//GregorianCalendar finalDate = new GregorianCalendar(datafine.getYear(),datafine.getMonthValue(),datafine.getDayOfMonth());
				//long  giorniInMillisecondi=finalDate.getTimeInMillis()-initialDate.getTimeInMillis();
				//double giorniSoggiornoDouble=giorniInMillisecondi/(24*60*60*1000);
				//int giorniSoggiorno=(int) giorniSoggiornoDouble;
				//System.out.println(giorniSoggiorno);
				contoAbitazione=days*contoLetto;
			
				
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
			ResultSet result = statement.executeQuery("select distinct sum(contiristorante.ContoTotale)\r\n"
					+ "from contiristorante,prenotazioniristorante,tavoliristorante\r\n"
					+ "where prenotazioniristorante.Cliente=(select clienti.CodiceFiscale from clienti where clienti.CodiceFiscale="+"'"+cf+"'"+")\r\n"
					+ "and prenotazioniristorante.IDPrenotazioneRistorante=contiristorante.PrenotazioneRistorante\r\n"
					+ "and prenotazioniristorante.Tavolo=tavoliristorante.NumeroTavolo");
			double contoParziale=0;
			while (result.next()) {
				double contoLetto=result.getDouble("sum(contiristorante.ContoTotale)");
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
	
	public ContoTotale doRetrieveContoTotaleById(String id) {
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("select * from contitotali where numeroconto = '"+ id +"' ");
			if (result.next()) {
				double importo = result.getDouble("Importo");
				String datap = result.getString("DataPagamento");
				LocalDate datapagamento = LocalDate.parse(datap, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String cf = result.getString("Cliente");
				return new ContoTotale(id, importo, datapagamento, cf);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean isCalcoloContoPossibile(String cf) {
		Statement statement = null;
		try {
			statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("select IDPrenotazioneRistorante "
					+ "from prenotazioniristorante "
					+ "where Cliente = '" + cf + "' "
					+ "and DataPrenotazione >= curdate() "
					+ "and OraPrenotazione >= curtime(); ");
			if (result.next()) return false;
			
			result = statement.executeQuery("select cliente "
					+ "from eventi as e "
					+ "inner join PRENOTAZIONIEVENTI as p on e.idEvento = p.Evento and p.Cliente = '" + cf +"' "
					+ "where DataEvento >= curdate() "
					+ "and oraEvento >= curtime(); ");
			if (result.next()) return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	public double getContoTotale(String codCliente) {
		
		if(!this.isCalcoloContoPossibile(codCliente)) 
			JOptionPane.showMessageDialog(null,
					"Attenzione! Risulta una prenotazione ad un ristorante e/o un bigletto per un evento ancora in corso.\n"
					+ "(I biglietti acquistati non sono rimborsabili).");
		
		DAOContoTotaleImpl contoEvento=new DAOContoTotaleImpl();
		double contoTotaleEvento=contoEvento.doRetrieveContoEventoByCf(codCliente);
		DAOContoTotaleImpl contoAbitazione=new DAOContoTotaleImpl();
		double contoTotaleAbitazione=contoAbitazione.doRetrieveContoAbitazioneByCf(codCliente);
		DAOContoTotaleImpl contoStruttura=new DAOContoTotaleImpl();
		double contoTotaleStruttura=contoStruttura.doRetrieveContoStrutturaCf(codCliente);
		DAOContoTotaleImpl contoRistorante=new DAOContoTotaleImpl();
		double contoTotaleRistorante=contoRistorante.doRetrieveContoRistoranteCf(codCliente);
		
		double contoTotale=contoTotaleEvento+contoTotaleAbitazione+contoTotaleStruttura+contoTotaleRistorante;
		
		return contoTotale;
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
