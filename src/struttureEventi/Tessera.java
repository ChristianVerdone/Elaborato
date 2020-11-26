package struttureEventi;



public class Tessera {
	public Tessera(String id, boolean disponibilitā) {
		this.id = id;
		this.disponibilitā = disponibilitā;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public boolean isDisponibilitā() {
		return disponibilitā;
	}
	public void setDisponibilitā(boolean disponibilitā) {
		this.disponibilitā = disponibilitā;
	}

	private String id;
	private boolean disponibilitā;
}
