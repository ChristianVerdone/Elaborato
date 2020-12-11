package repository;

public class DAOFactory {

	public DAOFactory() { }

	public static DAOAccount getDAOAccount() {
		return new DAOAccountImpl();
	}

	public static DAODipendenti getDAODipendenti() {
		return new DAODipendentiImpl();
	}

	public static DAOCliente getDAOCliente() {
		return new DAOClienteImpl();
	}

	public static DAOBiglietto getDAOBiglietto() {
		return new DAOBigliettoImpl();
	}

	public static DAOPrenotazioneEvento getDAOPrenotazioneEvento() {
		return new DAOPrenotazioneEventoImpl();
	}

	public static DAOEvento getDAOEvento() {
		return new DAOEventoImpl();
	}

	public static DAOAbitazione getDAOAbitazione() {
		return new DAOAbitazioneImpl();
	}

	public static DAOPrenotazioneAbitazione getDAOPrenotazioneAbitazione() {
		return new DAOPrenotazioneAbitazioneImpl();
	}

	public static DAOTavoloRistorante getDAOTavoloRistorante() {
		return new DAOTavoloRistoranteImp();
	}
	public static DAOPrenotazioneRistorante getDAOPrenotazioneRistorante() {
		return new DAOPrenotazioneRistoranteImpl();
	}

	public static DAOTessera getDAOTessera() {
		return new DAOTesseraImp();
	}
	public static DAOPrenotazioneStruttura getDAOPrenotazioneStruttura() {
		return new DAOPrenotazioneStrutturaImpl();
	}

	public static DAOStrutturraVillaggio getDAOStrutturaVillaggio() {
		return new DAOStrutturaVillaggioImpl();
	}
	public static DAOTurniLavoro getDAOTurniLavoro() {
		return new DAOTurniLavoroImpl();
	}

	public static DAOContoTotale getDAOContoTotale() {
		return new DAOContoTotaleImpl();
	}
}