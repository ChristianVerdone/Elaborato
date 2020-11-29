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
	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	private String idEvento;
	private String nome;
	private String tipo;
	private String descrizione;

}
