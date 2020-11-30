package repository;



public class DAOFactory {

	public DAOFactory() { }
	
	public static ConcreteDAOAccount getDAOAccount() {
		return new ConcreteDAOAccount();
	}

	public static ConcreteDAODipendenti getDAODipendenti() {
		return new ConcreteDAODipendenti();
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
	
}
