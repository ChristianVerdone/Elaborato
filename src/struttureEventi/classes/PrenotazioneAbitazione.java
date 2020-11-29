package struttureEventi.classes;






import java.sql.Date;
import java.time.LocalDate;

import contabilit�.Cliente;

public class PrenotazioneAbitazione {

	public PrenotazioneAbitazione(String idPrenotazione, Cliente cliente, String abitazione) {
		super(idPrenotazione, cliente);
		this.abitazione = abitazione;
		this.dataInizio=dataInizio;
		this.dataFine=dataFine;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Abitazione getAbitazione() {
		return abitazione;
	}


	public void setAbitazione(String abitazione) {
		this.abitazione = abitazione;
	}


	

	@Override
	public String toString() {
		return "PrenotazioneAbitazione [abitazione=" + abitazione + ", idPrenotazione=" + idPrenotazione + ", cliente="
				+ cliente + "]";
	}

	private String abitazione;

}
