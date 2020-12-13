package struttureEventi.classes;

public class ContoRistorante {


	public ContoRistorante(String idConto, float costo, PrenotazioneRistorante prenotazioneRistorante) {
		this.idConto = idConto;
		this.costo = costo;
		this.prenotazioneRistorante = prenotazioneRistorante;
	}
	
	
	public String getIdConto() {
		return idConto;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public PrenotazioneRistorante getPrenotazioneRistorante() {
		return prenotazioneRistorante;
	}
	


	@Override
	public String toString() {
		return "ContoRistorante [idConto=" + idConto + ", costo=" + costo + ", prenotazioneRistorante="
				+ prenotazioneRistorante + "]";
	}


	private String idConto;
	private float costo;
	private PrenotazioneRistorante prenotazioneRistorante;
}
