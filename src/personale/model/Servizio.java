package personale.model;

import java.time.LocalTime;

public class Servizio {

	public Servizio(String id, String descrizione, LocalTime inizio, LocalTime fine) {
		this.id = id;
		this.descrizione = descrizione;
		this.inizio = inizio;
		this.fine = fine;
	}

	/* Getters */
	public String getId() { return id; }
	public String getDescrizione() { return descrizione; }
	public LocalTime getInizio() { return inizio; }
	public LocalTime getFine() { return fine; }

	private String id;
	private String descrizione;
	private LocalTime inizio;
	private LocalTime fine;
}
