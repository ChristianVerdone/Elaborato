package struttureEventi.classes;

import java.util.HashMap;

public class StrutturaVillagio {
	
	public StrutturaVillagio(String idStruttura, float tariffaOraria, String tipo, HashMap<String, Tessera> tessere,
			HashMap<String, Lettore> lettori) {
		this.idStruttura = idStruttura;
		this.tariffaOraria = tariffaOraria;
		this.tipo = tipo;
		this.tessere = tessere;
		this.lettori = lettori;
	}
	
	public String getIdStruttura() {
		return idStruttura;
	}
	public void setIdStruttura(String idStruttura) {
		this.idStruttura = idStruttura;
	}
	public float getTariffaOraria() {
		return tariffaOraria;
	}
	public void setTariffaOraria(float tariffaOraria) {
		this.tariffaOraria = tariffaOraria;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public HashMap<String, Tessera> getTessere() {
		return tessere;
	}
	public void setTessere(HashMap<String, Tessera> tessere) {
		this.tessere = tessere;
	}
	public HashMap<String, Lettore> getLettori() {
		return lettori;
	}
	public void setLettori(HashMap<String, Lettore> lettori) {
		this.lettori = lettori;
	}

	private String idStruttura;
	private float tariffaOraria;
	private String tipo;
	private HashMap<String, Tessera> tessere;
	private HashMap<String, Lettore> lettori;
}
