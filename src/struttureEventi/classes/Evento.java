package struttureEventi.classes;

import java.util.HashMap;

public class Evento {

	public Evento(String idEvento, String nome, String tipo, String descrizione, HashMap<String, Biglietto> biglietti) {
		this.idEvento = idEvento;
		this.nome = nome;
		this.tipo = tipo;
		this.descrizione = descrizione;
		this.biglietti = biglietti;
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
	public HashMap<String, Biglietto> getBiglietti() {
		return biglietti;
	}
	public void setBiglietti(HashMap<String, Biglietto> biglietti) {
		this.biglietti = biglietti;
	}

	private String idEvento;
	private String nome;
	private String tipo;
	private String descrizione;
	private HashMap<String, Biglietto> biglietti;
}
