package personale.model;

public class Account {
	public enum Permessi {ALL, REDUCED, NONE};
	
	public Account(String username, String password, Permessi tipologiaPermessi) {
		this.username = username;
		this.password = password;
		this.tipologiaPermessi = tipologiaPermessi;
	}
	
	/* Getters and Setters */
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public Permessi getTipologiaPermessi() { return tipologiaPermessi; }
	
	public void setUsername(String username) { this.username = username; }
	public void setPassword(String password) { this.password = password; }
	public void setTipologiaPermessi(Permessi tipologiaPermessi) { this.tipologiaPermessi = tipologiaPermessi; }
	
	private String username, password;
	private Permessi tipologiaPermessi;
}
