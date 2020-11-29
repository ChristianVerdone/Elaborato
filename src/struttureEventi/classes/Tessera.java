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

	private String id;
	private String descrizione;
}
