package struttureEventi.classes;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

import contabilità.Cliente;

public class PrenotazioneRistorante extends Prenotazione{

	public PrenotazioneRistorante(String idPrenotazione, Cliente cliente, int nTavolo, LocalDateTime data) {
		super(idPrenotazione, cliente);
		this.nTavolo = nTavolo;
		this.data = data;
		
	}

	public int getnTavolo() {
		return nTavolo;
	}

	public void setnTavolo(int nTavolo) {
		this.nTavolo = nTavolo;
	}


	


	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Time getOra() {
		return ora;
	}

	public void setOra(Time ora) {
		this.ora = ora;
	}





	@Override
	public String toString() {
		return "PrenotazioneRistorante [nTavolo=" + nTavolo + ", data=" + data +  ", idPrenotazione="
				+ idPrenotazione + ", cliente=" + cliente + "]";
	}





	private int nTavolo;
	private LocalDateTime data;
	private Time ora;
}
