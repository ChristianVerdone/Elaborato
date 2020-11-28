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
	public void setIdPrenotazione(String idPrenotazione) {
		this.idPrenotazione = idPrenotazione;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	protected String idPrenotazione;
	public Cliente cliente;
	
}
