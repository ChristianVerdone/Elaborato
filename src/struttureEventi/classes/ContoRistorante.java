package struttureEventi.classes;

public class ContoRistorante {


	public ContoRistorante(String idConto, float costo, Prenotazione prenotazioneRistorante) {
		this.idConto = idConto;
		this.costo = costo;
		this.prenotazioneRistorante = prenotazioneRistorante;
	}
	
	
	public String getIdConto() {
		return idConto;
	}
	public void setIdConto(String idConto) {
		this.idConto = idConto;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public Prenotazione getPrenotazioneRistorante() {
		return prenotazioneRistorante;
	}
	public void setPrenotazioneRistorante(Prenotazione prenotazioneRistorante) {
		this.prenotazioneRistorante = prenotazioneRistorante;
	}


	@Override
	public String toString() {
		return "ContoRistorante [idConto=" + idConto + ", costo=" + costo + ", prenotazioneRistorante="
				+ prenotazioneRistorante + "]";
	}


	private String idConto;
	private float costo;
	private Prenotazione prenotazioneRistorante;
}
