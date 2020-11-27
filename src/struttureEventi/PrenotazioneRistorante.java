package struttureEventi;

import contabilità.Cliente;

public class PrenotazioneRistorante extends Prenotazione{

	public PrenotazioneRistorante(String idPrenotazione, Cliente cliente, int nTavolo) {
		super(idPrenotazione, cliente);
		this.nTavolo = nTavolo;
	}

	public int getnTavolo() {
		return nTavolo;
	}

	public void setnTavolo(int nTavolo) {
		this.nTavolo = nTavolo;
	}

	private int nTavolo;
}
