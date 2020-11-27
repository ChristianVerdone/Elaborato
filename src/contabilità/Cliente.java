package contabilità;

public class Cliente {
	
	
	public Cliente(String cf, String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	
	




	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cognome=" + cognome + ", cf=" + cf + "]";
	}






	private String nome;
	private String cognome;
	private String cf;
}
