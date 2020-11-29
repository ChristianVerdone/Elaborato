package repository;

public class DAOFactory {

	public DAOFactory() { }
	
	public static ConcreteDAOAccount getDAOAccount() {
		return new ConcreteDAOAccount();
	}

	public static ConcreteDAODipendenti getDAODipendenti() {
		return new ConcreteDAODipendenti();
	}
}
