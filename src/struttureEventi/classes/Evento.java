package struttureEventi.classes;

public class Evento {

	public Evento(String idEvento, String nome, String tipo, String descrizione) {
		this.idEvento = idEvento;
		this.nome = nome;
		this.tipo = tipo;
		this.descrizione = descrizione;
	}
	
	public String getIdEvento() {
		return idEvento;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	

	@Override
	public String toString() {
		return "Evento [idEvento=" + idEvento + ", nome=" + nome + ", tipo=" + tipo + ", descrizione=" + descrizione
				+ "]";
	}

	private String idEvento;
	private String nome;
	private String tipo;
	private String descrizione;

}
