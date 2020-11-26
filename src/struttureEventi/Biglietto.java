package struttureEventi;

public class Biglietto {
	
	
	public Biglietto(String idBiglietto, float costo, boolean disponibilità) {
		
		this.idBiglietto = idBiglietto;
		this.costo = costo;
		this.disponibilità = disponibilità;
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
	public boolean isDisponibilità() {
		return disponibilità;
	}
	public void setDisponibilità(boolean disponibilità) {
		this.disponibilità = disponibilità;
	}


	private String idBiglietto;
	private float costo;
	private boolean disponibilità;
}
