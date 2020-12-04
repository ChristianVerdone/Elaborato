package struttureEventi.classes;

public class Tessera {
	public Tessera(String id, String descrizione) {
		this.id = id;
		this.descrizione = descrizione;
	}
	
	public String getId() {
		return id;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	
	@Override
	public String toString() {
		return "Tessera [id=" + id + ", descrizione=" + descrizione + "]";
	}


	private String id;
	private String descrizione;
}
