package struttureEventi.classes;

public class Abitazione {

	public Abitazione(String idAbitazione, int postiletto, float tariffa, String descrizione, int abitazioniDisponibili) {
		super();
		this.idAbitazione = idAbitazione;
		this.postiletto = postiletto;
		this.tariffa = tariffa;
		this.descrizione = descrizione;
		this.abitazioniDisponibili=abitazioniDisponibili;
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
	

	
	public int getAbitazioniDisponibili() {
		return abitazioniDisponibili;
	}



	private String idAbitazione;
	private int postiletto;
	private float tariffa;
	private String descrizione;
	private int abitazioniDisponibili;
}
