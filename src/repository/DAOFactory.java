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

	public static DAOAbitazione getDAOAbitazione() {
		return new DAOAbitazioneImpl();
	}
	
	public static DAOPrenotazioneAbitazione getDAOPrenotazioneAbitazione() {
		return new DAOPrenotazioneAbitazioneImpl();
	}
	
	public static DAOTurniLavoro getDAOTurniLavoro() {
		return new DAOTurniLavoroImpl();
	}
	
}
