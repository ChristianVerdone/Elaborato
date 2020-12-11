package struttureEventi.classes;

import java.time.LocalDateTime;

public class Lettore {

	public Lettore(String idLettore, String descrizione, String struttura) {
		super();
		this.idLettore = idLettore;
		this.descrizione = descrizione;
		this.struttura = struttura;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getStruttura() {
		return struttura;
	}

	public String getIdLettore() {
		return idLettore;
	}

	public Movimento createMovimento(String idTessera) {
		return new Movimento(idTessera, idLettore, true);
	}
	
	private String idLettore;
	private String descrizione;
	private String struttura;
}