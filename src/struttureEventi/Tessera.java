package struttureEventi;



public class Tessera {
	public Tessera(String id, boolean disponibilità) {
		this.id = id;
		this.disponibilità = disponibilità;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public boolean isDisponibilità() {
		return disponibilità;
	}
	public void setDisponibilità(boolean disponibilità) {
		this.disponibilità = disponibilità;
	}

	private String id;
	private boolean disponibilità;
}
