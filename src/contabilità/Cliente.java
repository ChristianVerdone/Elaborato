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

	public String getCognome() {
		return cognome;
	}
	
	public String getCf() {
		return cf;
	}
		

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cognome=" + cognome + ", cf=" + cf + "]";
	}






	private String nome;
	private String cognome;
	private String cf;
}
