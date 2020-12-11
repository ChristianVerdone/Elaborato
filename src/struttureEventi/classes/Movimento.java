package struttureEventi.classes;

import java.time.LocalDateTime;

public class Movimento {


	public Movimento(String idTessera, String idLettore, boolean tipo) {

		this.idTessera = idTessera;
		this.idLettore = idLettore;
		this.tipo = tipo;
	}

	public String getIdTessera() {
		return idTessera;
	}

	public String getIdLettore() {
		return idLettore;
	}

	public boolean getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return "Movimento [idTessera=" + idTessera + ", idLettore=" + idLettore + ", tipo=" + tipo + "]";
	}

	private String idTessera;
	private String idLettore;
	private boolean tipo;
}
