package struttureEventi.classes;

public class Biglietto {
	
	
	public Biglietto(String idBiglietto, float costo, boolean disponibilit�, String nomeEvento) {
		this.nomeEvento= nomeEvento;
		this.idBiglietto = idBiglietto;
		this.costo = costo;
		this.disponibilit� = disponibilit�;
	}
	
	public String getIdBiglietto() {
		return idBiglietto;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public boolean isDisponibilit�() {
		return disponibilit�;
	}
	

	public String getNomeEvento() {
		return nomeEvento;
	}



	@Override
	public String toString() {
		return "Biglietto [nomeEvento=" + nomeEvento + ", idBiglietto=" + idBiglietto + ", costo=" + costo
				+ ", disponibilit�=" + disponibilit� + "]";
	}

	private String nomeEvento;
	private String idBiglietto;
	private float costo;
	private boolean disponibilit�;
}
