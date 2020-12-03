package struttureEventi.classes;

public class Tessera {
	public Tessera(String id, String descrizione) {
		this.id = id;
		this.descrizione = descrizione;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	public void seDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	
	@Override
	public String toString() {
		return "Tessera [id=" + id + ", descrizione=" + descrizione + "]";
	}


	private String id;
	private String descrizione;
}
