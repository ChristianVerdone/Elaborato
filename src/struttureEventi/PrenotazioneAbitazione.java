package struttureEventi;



public class PrenotazioneAbitazione extends Prenotazione {

	public PrenotazioneAbitazione(String idPrenotazione, String abitazione) {
		super(idPrenotazione);
		this.abitazione = abitazione;
	}

	public String getAbitazione() {
		return abitazione;
	}

	public void setAbitazione(String abitazione) {
		this.abitazione = abitazione;
	}

	private String abitazione;
}
