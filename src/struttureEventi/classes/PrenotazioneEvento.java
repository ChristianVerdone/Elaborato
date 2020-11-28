package struttureEventi.classes;

import contabilità.Cliente;

public class PrenotazioneEvento extends Prenotazione {

	public PrenotazioneEvento(String idPrenotazione, Cliente cliente, String evento, String biglietto) {
		super(idPrenotazione, cliente);
		this.evento = evento;
		this.biglietto = biglietto;
	}
	
	
	
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public String getBiglietto() {
		return biglietto;
	}
	public void setBiglietto(String biglietto) {
		this.biglietto = biglietto;
	}



	private String evento;
	private String biglietto;
}
