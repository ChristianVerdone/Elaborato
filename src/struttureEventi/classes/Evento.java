package struttureEventi.classes;

import java.time.LocalDateTime;

public class Evento {

	public Evento(String idEvento, String nome, String tipo, String descrizione, LocalDateTime dataEvento) {
		this.idEvento = idEvento;
		this.nome = nome;
		this.tipo = tipo;
		this.descrizione = descrizione;
		this.dataEvento=dataEvento;
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
	

	
	public LocalDateTime getDataEvento() {
		return dataEvento;
	}

	@Override
	public String toString() {
		return "Evento [idEvento=" + idEvento + ", nome=" + nome + ", tipo=" + tipo + ", descrizione=" + descrizione
				+ ", dataEvento=" + dataEvento + "]";
	}



	private String idEvento;
	private String nome;
	private String tipo;
	private String descrizione;
	private LocalDateTime dataEvento;

}
