package personale.model;

public class Dipendente {

	public Dipendente(String cf, String nome, String cognome, String mansione, double stipendio) {
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



	/* Getters and Setters */
	public String getCf() { return cf; }
	public String getNome() { return nome; }
	public String getCognome() { return cognome; }
	public String getMansione() { return mansione; }
	public double getStipendio() { return stipendio; }
	
	public void setCf(String cf) { this.cf = cf; }
	public void setNome(String nome) { this.nome = nome; }
	public void setCognome(String cognome) { this.cognome = cognome; }
	public void setMansione(String mansione) { this.mansione = mansione; }
	public void setStipendio(double stipendio) { this.stipendio = stipendio; }
	
	private String cf, nome, cognome, mansione;
	private double stipendio;	
}
