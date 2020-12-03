package struttureEventi.classes;

import contabilità.Cliente;

public class PrenotazioneSv extends Prenotazione {

	public PrenotazioneSv(String idPrenotazione, Cliente cliente, StrutturaVillaggio struttura, Tessera tessera) {
		super(idPrenotazione, cliente);
		this.struttura = struttura;
		this.tessera = tessera;
		
	}
	
	public Tessera getTessera() {
		return tessera;
	}
	public void setTessera(Tessera tessera) {
		this.tessera = tessera;
	}
	public StrutturaVillaggio getStruttura() {
		return struttura;
	}

	public void setStruttura(StrutturaVillaggio struttura) {
		this.struttura = struttura;
	}


	@Override
	public String toString() {
		return "PrenotazioneSv [tessera=" + tessera + ", struttura=" + struttura + "]";
	}


	private Tessera tessera;
	private StrutturaVillaggio struttura;
	
}
