package struttureEventi.classes;

import java.time.LocalDate;
import java.time.LocalTime;

public class Evento {

	public Evento(String idEvento, String nome, String tipo, String descrizione, LocalDate dataEvento, LocalTime oraEvento) {
		this.idEvento = idEvento;
		this.nome = nome;
		this.tipo = tipo;
		this.descrizione = descrizione;
		this.dataEvento=dataEvento;
		this.oraEvento = oraEvento;
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
	
	public LocalDate getDataEvento() {
		return dataEvento;
	}
	
	public LocalTime getOraEvento() {
		return oraEvento;
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
	private LocalDate dataEvento;
	private LocalTime oraEvento;
}