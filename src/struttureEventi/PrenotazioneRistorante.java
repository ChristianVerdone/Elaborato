package struttureEventi;



public class PrenotazioneRistorante extends Prenotazione{

	public PrenotazioneRistorante(String idPrenotazione, int nTavolo) {
		super(idPrenotazione);
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
