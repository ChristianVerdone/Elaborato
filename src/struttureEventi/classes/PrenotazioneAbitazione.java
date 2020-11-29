package struttureEventi.classes;






import java.sql.Date;
import java.time.LocalDate;

import contabilità.Cliente;

public class PrenotazioneAbitazione {


	public PrenotazioneAbitazione( String id, Cliente cliente, Abitazione abitazione, LocalDate dataInizio, LocalDate dataFine) {
		this.id=id;
		this.cliente=cliente;
		this.abitazione = abitazione;
		this.dataInizio=dataInizio;
		this.dataFine=dataFine;
	}

	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Abitazione getAbitazione() {
		return abitazione;
	}

	public void setAbitazione(Abitazione abitazione) {
		this.abitazione = abitazione;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "PrenotazioneAbitazione [id=" + id + ", cliente=" + cliente + ", abitazione=" + abitazione
				+ ", dataInizio=" + dataInizio + ", dataFine=" + dataFine + "]";
	}



	private String id; 
	private Cliente cliente;
	private Abitazione abitazione;
	private LocalDate dataInizio;
	private LocalDate dataFine;

}
