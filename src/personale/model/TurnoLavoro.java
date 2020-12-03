package personale.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TurnoLavoro {
		
	public TurnoLavoro(String dip, LocalDate inizio,  Servizio servizio) {
		this.dip = dip;
		this.inizio = inizio;
		this.servizio = servizio;
	}

	/* Getters and Setters */
	public String getDip() { return dip; }
	public LocalDate getInizio() { return inizio; }
	public Servizio getServizio() { return servizio; }
	
	
	public void setDip(String dip) { this.dip = dip; }
	public void setInizio(LocalDate inizio) { this.inizio = inizio; }
	public void setDescrizione(Servizio servizio) { this.servizio = servizio; }
	
	private String dip;
	private LocalDate inizio;
	private Servizio servizio;
}
