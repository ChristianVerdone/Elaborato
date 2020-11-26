package struttureEventi;

public class ContoRistorante {

	public ContoRistorante(int nTavolo, Prenotazione prenotazioneRistorante) {

		this.nTavolo = nTavolo;
		this.prenotazioneRistorante = prenotazioneRistorante;
	}
	
	public int getnTavolo() {
		return nTavolo;
	}
	public void setnTavolo(int nTavolo) {
		this.nTavolo = nTavolo;
	}
	public Prenotazione getPrenotazioneRistorante() {
		return prenotazioneRistorante;
	}
	public void setPrenotazioneRistorante(Prenotazione prenotazioneRistorante) {
		this.prenotazioneRistorante = prenotazioneRistorante;
	}

	private int nTavolo;
	private Prenotazione prenotazioneRistorante;
}
