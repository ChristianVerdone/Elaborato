package personale.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TurnoLavoro {

	/* Test */
	public static void main(String[] args) {
		LocalDateTime oraI = LocalDateTime.of(2020, 11, 23, 8, 00);
		LocalDateTime oraF = LocalDateTime.of(2020, 11, 23, 14, 00);
		TurnoLavoro t = new TurnoLavoro("CF001", oraI, oraF, "Pulizia camere");
		System.out.println(t);
	}
	
	
	public TurnoLavoro(String dip, LocalDateTime oraI, LocalDateTime oraF, String descrizione) {
		this.dip = dip;
		this.oraI = oraI;
		this.oraF = oraF;
		this.descrizione = descrizione;
	}	
	
	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return dip + " | " + descrizione + "\nInizio: " + oraI.format(dtf) + "\nFine: " + oraF.format(dtf);
	}

	/* Getters and Setters */
	public String getDip() { return dip; }
	public LocalDateTime getOraI() { return oraI; }
	public LocalDateTime getOraF() { return oraF; }
	public String getDescrizione() { return descrizione; }
	
	
	public void setOraI(LocalDateTime oraI) { this.oraI = oraI; }
	public void setDip(String dip) { this.dip = dip; }
	public void setOraF(LocalDateTime oraF) { this.oraF = oraF; }
	public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
	
	private String dip;
	private LocalDateTime oraI;
	private LocalDateTime oraF;
	private String descrizione;
}
