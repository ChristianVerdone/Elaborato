package struttureEventi.classes;

import java.time.LocalDate;

import contabilita.Cliente;

public class PrenotazioneAbitazione extends Prenotazione{

	public PrenotazioneAbitazione(String idPrenotazione, Cliente cliente, Abitazione abitazione, LocalDate dataInizio, LocalDate dataFine) {
		super(idPrenotazione, cliente);
		this.abitazione=abitazione;
		this.dataInizio=dataInizio;
		this.dataFine=dataFine;
	}
	
	
	public Abitazione getAbitazione() {
		return abitazione;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}
	
	public LocalDate getDataFine() {
		return dataFine;
	}
	

	@Override
	public String toString() {
		return "PrenotazioneAbitazione [abitazione=" + abitazione + ", dataInizio=" + dataInizio + ", dataFine="
				+ dataFine + ", idPrenotazione=" + idPrenotazione + ", cliente=" + cliente + "]";
	}

	private Abitazione abitazione;
	private LocalDate dataInizio;
	private LocalDate dataFine;

}
