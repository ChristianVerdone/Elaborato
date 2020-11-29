package struttureEventi.classes;

public class Abitazione {

	public Abitazione(String idAbitazione, int postiletto, float tariffa, String descrizione) {
		super();
		this.idAbitazione = idAbitazione;
		this.postiletto = postiletto;
		this.tariffa = tariffa;
		this.descrizione = descrizione;
	}
	

	public String getIdAbitazione() {
		return idAbitazione;
	}
	public void setIdAbitazione(String idAbitazione) {
		this.idAbitazione = idAbitazione;
	}
	public int getPostiletto() {
		return postiletto;
	}
	public void setPostiletto(int postiletto) {
		this.postiletto = postiletto;
	}
	public float getTariffa() {
		return tariffa;
	}
	public void setTariffa(float tariffa) {
		this.tariffa = tariffa;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	private String idAbitazione;
	private int postiletto;
	private float tariffa;
	private String descrizione;
}
