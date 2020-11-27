package struttureEventi.classes;

import contabilità.Cliente;

public class PrenotazioneAbitazione extends Prenotazione {


	public PrenotazioneAbitazione(String idPrenotazione, Cliente cliente, String abitazione) {
		super(idPrenotazione, cliente);
		this.abitazione = abitazione;
	}

	
	public String getAbitazione() {
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
