package struttureEventi.classes;



public class Tessera {
	public Tessera(String id, boolean disponibilit�) {
		this.id = id;
		this.disponibilit� = disponibilit�;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public boolean isDisponibilit�() {
		return disponibilit�;
	}
	public void setDisponibilit�(boolean disponibilit�) {
		this.disponibilit� = disponibilit�;
	}

	private String id;
	private boolean disponibilit�;
}
