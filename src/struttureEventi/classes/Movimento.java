package struttureEventi.classes;

import java.time.LocalDateTime;

public class Movimento {

	
	public Movimento(String idTessera, String idLettore, int tipo, LocalDateTime data) {
		
		this.idTessera = idTessera;
		this.idLettore = idLettore;
		this.tipo = tipo;
		this.data = data;
	}
	
	public String getIdTessera() {
		return idTessera;
	}

	public String getIdLettore() {
		return idLettore;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	

	private String idTessera;
	private String idLettore;
	private int tipo;
	private LocalDateTime data;
}
