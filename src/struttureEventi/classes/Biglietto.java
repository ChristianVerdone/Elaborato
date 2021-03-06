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
	
	public float getCosto() {
		return costo;
	}
	
	public boolean isDisponibilitā() {
		return disponibilitā;
	}
	

	public String getNomeEvento() {
		return nomeEvento;
	}



	@Override
	public String toString() {
		return "Biglietto [nomeEvento=" + nomeEvento + ", idBiglietto=" + idBiglietto + ", costo=" + costo
				+ ", disponibilitā=" + disponibilitā + "]";
	}

	private String nomeEvento;
	private String idBiglietto;
	private float costo;
	private boolean disponibilitā;
}
