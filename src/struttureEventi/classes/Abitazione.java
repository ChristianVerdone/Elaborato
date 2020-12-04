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
	
	public int getPostiletto() {
		return postiletto;
	}
	
	public float getTariffa() {
		return tariffa;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	

	private String idAbitazione;
	private int postiletto;
	private float tariffa;
	private String descrizione;
}
