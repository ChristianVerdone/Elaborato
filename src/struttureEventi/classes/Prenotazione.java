package struttureEventi.classes;

import contabilità.Cliente;

public class Prenotazione {
	
	public Prenotazione(String idPrenotazione, Cliente cliente) {
		this.idPrenotazione = idPrenotazione;
		this.cliente = cliente;
	}
	
	public String getIdPrenotazione() {
		return idPrenotazione;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	@Override
	public String toString() {
		return "Prenotazione [idPrenotazione=" + idPrenotazione + ", cliente=" + cliente + "]";
	}

	protected String idPrenotazione;
	public Cliente cliente;
	
}
