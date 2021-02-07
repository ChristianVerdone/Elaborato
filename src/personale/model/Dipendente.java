package personale.model;

public class Dipendente {

	public Dipendente(String cf, String nome, String cognome, String mansione, Integer stipendio) {
		this.cf = cf;
		this.nome = nome;
		this.cognome = cognome;
		this.mansione = mansione;
		this.stipendio = stipendio;
	}

	@Override
	public String toString() {
		return cf + " | " + nome + " " + cognome + " | " + mansione + " | " + stipendio;
	}

	/* Getters  */
	public String getCf() { return cf; }
	public String getNome() { return nome; }
	public String getCognome() { return cognome; }
	public String getMansione() { return mansione; }
	public Integer getStipendio() { return stipendio; }

	private String cf, nome, cognome, mansione;
	private Integer stipendio;	
}
