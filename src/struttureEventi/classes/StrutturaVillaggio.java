package struttureEventi.classes;

public class StrutturaVillaggio {
	
	public StrutturaVillaggio(String idStruttura, float tariffaOraria, String tipo) {
		this.idStruttura = idStruttura;
		this.tariffaOraria = tariffaOraria;
		this.tipo = tipo;
	}
	
	public String getIdStruttura() {
		return idStruttura;
	}
	
	public float getTariffaOraria() {
		return tariffaOraria;
	}
	
	public String getTipo() {
		return tipo;
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
