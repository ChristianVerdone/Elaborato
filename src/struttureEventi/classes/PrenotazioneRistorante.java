package struttureEventi.classes;

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

	public LocalDateTime getData() {
		return data;
	}


	@Override
	public String toString() {
		return "PrenotazioneRistorante [nTavolo=" + nTavolo + ", data=" + data +  ", idPrenotazione="
				+ idPrenotazione + ", cliente=" + cliente + "]";
	}

	private int nTavolo;
	private LocalDateTime data;

}
