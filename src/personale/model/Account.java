package personale.model;

import personale.model.Account.Permessi;

public class Account {
	public enum Permessi {ALL, REDUCED, NONE};
	
	public Account(String username, String password, Permessi tipologiaPermessi) {
		this.username = username;
		this.password = password;
		this.tipologiaPermessi = tipologiaPermessi;
	}
	
	/* ENUM methods */
	public static Permessi getPermessiFromInt(int p) {
		if(p == 2) return Permessi.REDUCED;
		if(p == 3) return Permessi.ALL;
		return Permessi.NONE;
	}
	
	public static int getIntFromPermessi(Permessi prm) {
		if(prm == Permessi.REDUCED) return 2;
		if(prm == Permessi.ALL) return 3;
		return 1;
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
