package struttureEventi.classes;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import contabilita.Cliente;

public class PrenotazioneRistorante extends Prenotazione {

	public PrenotazioneRistorante(String idPrenotazione, Cliente cliente, int nTavolo, LocalDate data, LocalTime ora) {
		super(idPrenotazione, cliente);
		this.nTavolo = nTavolo;
		this.data = data;
		this.ora = ora;
	}

	public int getnTavolo() {
		return nTavolo;
	}

	public LocalTime getOra() {
		return ora;
	}

	public LocalDate getData() {
		return data;
	}

	@Override
	public String toString() {
		return "PrenotazioneRistorante [nTavolo=" + nTavolo + ", data=" + data + ", ora=" + ora + ", idPrenotazione="
				+ idPrenotazione + ", cliente=" + cliente + "]";
	}

	private int nTavolo;
	private LocalDate data;
	private LocalTime ora;
}
