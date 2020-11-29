package struttureEventi.classes;

public class Biglietto {
	
	
	public Biglietto(String idBiglietto, float costo, boolean disponibilitā, String nomeEvento) {
		this.nomeEvento= nomeEvento;
		this.idBiglietto = idBiglietto;
		this.costo = costo;
		this.disponibilitā = disponibilitā;
	}
	
	public String getIdBiglietto() {
		return idBiglietto;
	}
	public void setIdBiglietto(String idBiglietto) {
		this.idBiglietto = idBiglietto;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public boolean isDisponibilitā() {
		return disponibilitā;
	}
	public void setDisponibilitā(boolean disponibilitā) {
		this.disponibilitā = disponibilitā;
	}

	private String nomeEvento;
	private String idBiglietto;
	private float costo;
	private boolean disponibilitā;
}
