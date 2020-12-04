package struttureEventi.classes;

public class Biglietto {
	
	
	public Biglietto(String idBiglietto, float costo, boolean disponibilità, String nomeEvento) {
		this.nomeEvento= nomeEvento;
		this.idBiglietto = idBiglietto;
		this.costo = costo;
		this.disponibilità = disponibilità;
	}
	
	public String getIdBiglietto() {
		return idBiglietto;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public boolean isDisponibilità() {
		return disponibilità;
	}
	

	public String getNomeEvento() {
		return nomeEvento;
	}



	@Override
	public String toString() {
		return "Biglietto [nomeEvento=" + nomeEvento + ", idBiglietto=" + idBiglietto + ", costo=" + costo
				+ ", disponibilità=" + disponibilità + "]";
	}

	private String nomeEvento;
	private String idBiglietto;
	private float costo;
	private boolean disponibilità;
}
