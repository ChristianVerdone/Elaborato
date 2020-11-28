package struttureEventi.classes;

import java.time.LocalDateTime;

public class Lettore {

	public Lettore(String idLettore) {
		this.idLettore = idLettore;
	}

		
	public String getIdLettore() {
		return idLettore;
	}

	public void setIdLettore(String idLettore) {
		this.idLettore = idLettore;
	}

	public Movimento createMovimento(String idTessera) {
		return new Movimento(idTessera, idLettore, 0, LocalDateTime.now());
	}
	private String idLettore;
}
