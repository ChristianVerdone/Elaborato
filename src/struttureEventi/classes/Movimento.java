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
	public void setIdTessera(String idTessera) {
		this.idTessera = idTessera;
	}
	public String getIdLettore() {
		return idLettore;
	}
	public void setIdLettore(String idLettore) {
		this.idLettore = idLettore;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}

	private String idTessera;
	private String idLettore;
	private int tipo;
	private LocalDateTime data;
}
