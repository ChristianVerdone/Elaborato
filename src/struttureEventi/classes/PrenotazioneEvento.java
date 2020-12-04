package struttureEventi.classes;

import contabilità.Cliente;

public class PrenotazioneEvento extends Prenotazione {

	public PrenotazioneEvento(String idPrenotazione, Cliente cliente, Evento evento, Biglietto biglietto) {
		super(idPrenotazione, cliente);
		this.evento = evento;
		this.biglietto = biglietto;
	}
	
	public Evento getEvento() {
		return evento;
	}
	
	public Biglietto getBiglietto() {
		return biglietto;
	}
	

	@Override
	public String toString() {
		return "PrenotazioneEvento [idPrenotazione=" + idPrenotazione + ", cliente=" + cliente + ", evento=" + evento
				+ ", biglietto=" + biglietto + "]";
	}

	private Evento evento;
	private Biglietto biglietto;
}
