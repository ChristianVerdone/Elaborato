package struttureEventi.classes;

import contabilità.Cliente;

public class PrenotazioneSv extends Prenotazione {

	public PrenotazioneSv(String idPrenotazione, Cliente cliente,  Tessera tessera, String idStruttura) {
		super(idPrenotazione, cliente);
		this.tessera = tessera;
		this.idStruttura = idStruttura;
	}
	
	public Tessera getTessera() {
		return tessera;
	}
	public void setTessera(Tessera tessera) {
		this.tessera = tessera;
	}
	public String getIdStruttura() {
		return idStruttura;
	}
	public void setIdStruttura(String idStruttura) {
		this.idStruttura = idStruttura;
	}

	private Tessera tessera;
	private String idStruttura;
	
}
