package personale.model;
import java.time.LocalDate;

public class TurnoLavoro {

	public TurnoLavoro(String dip, LocalDate inizio,  Servizio servizio) {
		this.dip = dip;
		this.inizio = inizio;
		this.servizio = servizio;
	}

	/* Getters*/
	public String getDip() { return dip; }
	public LocalDate getInizio() { return inizio; }
	public Servizio getServizio() { return servizio; }

	private String dip;
	private LocalDate inizio;
	private Servizio servizio;
}
