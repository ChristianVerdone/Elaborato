package struttureEventi.classes;

public class Biglietto {
	
	
	public Biglietto(String idBiglietto, float costo, boolean disponibilit�) {
		
		this.idBiglietto = idBiglietto;
		this.costo = costo;
		this.disponibilit� = disponibilit�;
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
	public boolean isDisponibilit�() {
		return disponibilit�;
	}
	public void setDisponibilit�(boolean disponibilit�) {
		this.disponibilit� = disponibilit�;
	}


	private String idBiglietto;
	private float costo;
	private boolean disponibilit�;
}
