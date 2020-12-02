package struttureEventi.classes;

import java.util.HashMap;

public class StrutturaVillaggio {
	
	public StrutturaVillaggio(String idStruttura, float tariffaOraria, String tipo) {
		this.idStruttura = idStruttura;
		this.tariffaOraria = tariffaOraria;
		this.tipo = tipo;
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


	@Override
	public String toString() {
		return "StrutturaVillaggio [idStruttura=" + idStruttura + ", tariffaOraria=" + tariffaOraria + ", tipo=" + tipo
				+ "]";
	}


	private String idStruttura;
	private float tariffaOraria;
	private String tipo;

}
