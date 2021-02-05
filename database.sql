drop database if exists sannioVillage;
create database sannioVillage;
use sannioVillage;

create table CLIENTI(
	CodiceFiscale char(16) PRIMARY KEY,
    Nome varchar(30) NOT NULL,
    Cognome varchar(30) NOT NULL
);

create table EVENTI(
	IdEvento char(5) PRIMARY KEY,
    Nome varchar(30) NOT NULL,
    Tipo varchar(20) NOT NULL,
    Descrizione varchar(200) NOT NULL,
    DataEvento date NOT NULL,
    oraEvento time NOT NULL
);

create table BIGLIETTI(
	IdBiglietto char(5) PRIMARY KEY,
    Costo decimal(4,2) NOT NULL,
    Disponibilita boolean NOT NULL,
    NomeEvento varchar(30) not null
);

create table CONTITOTALI(
	NumeroConto char(5) PRIMARY KEY,
    Importo decimal(6,2) NOT NULL,
    DataPagamento date NOT NULL,
    Cliente char(16) references CLIENTI(CodiceFiscale)
);

create table STRUTTUREVILLAGGIO(
	IdStruttura varchar(15) PRIMARY KEY,
    Tipo varchar(20) NOT NULL,
    Tariffa decimal(4,2) NOT NULL
);

create table LETTORI(
	IdLettore char(3) PRIMARY KEY,
    DescrizioneLettore varchar(50) NOT NULL,
    StrutturaVillaggio varchar(15) references STRUTTUREVILLAGGIO(IdStruttura)
);

create table TESSERE(
	IdTessera char(5) PRIMARY KEY,
    DescrizioneTessera varchar(50) NOT NULL
);

create table ABITAZIONI(
	IdAbitazione varchar(15) PRIMARY KEY,
    PostiLetto decimal(2) NOT NULL,
    Tariffa decimal(5,2) NOT NULL,
    Descrizione varchar(100) NOT NULL,
    AbitazioniDisponibili decimal(2) NOT NULL
);

create table CONTIRISTORANTE(
	IdConto char(5) PRIMARY KEY,
    PrenotazioneRistorante char(5) references prenotazioniristorante(IDPrenotazioneRistorante),
    ContoTotale decimal(5,2) NOT NULL
);

create table TAVOLIRISTORANTE(
	NumeroTavolo numeric(2) PRIMARY KEY,
    NumeroPosti decimal(2) NOT NULL
);

create table SERVIZI(
	IdServizio char(5) PRIMARY KEY,
    DescrizioneServizio varchar(100) NOT NULL,
    oraInizio time NOT NULL,
	oraFine time NOT NULL
);

create table DIPENDENTI(
	CFiscale char(16) PRIMARY KEY,
    Nome varchar(30) NOT NULL,
    Cognome varchar(30) NOT NULL,
	Mansione varchar(30) NOT NULL,
    Stipendio decimal(6,2) NOT NULL
);

create table ACCOUNTS(
	Username varchar(16) PRIMARY KEY,
    Dipendente char(16) references DIPENDENTI(CFiscale),
    PassDipendente varchar(30) NOT NULL,
	Permessi decimal(1) NOT NULL
);

create table TURNIDILAVORO(
    Dipendente char(16) references DIPENDENTI(CFiscale),
    Servizio char(5) references SERVIZI(IdTurno),
    DataInizioTurno date NOT NULL
);

create table PRENOTAZIONIABITAZIONI(
	IdPrenotazioneAbitazione char(5) primary key,
    Cliente char(16),
    Abitazione varchar(15) references ABITAZIONI(IdAbitazione),
    DataInizio date not null,
    DataFine date not null,
    foreign key (Cliente) references CLIENTI(CodiceFiscale) on delete cascade
);

create table PRENOTAZIONIRISTORANTE(
	IDPrenotazioneRistorante char(5) primary key,
    Cliente char(16),
    Tavolo numeric(2) references TAVOLI(NumeroTavolo),
    DataPrenotazione date,
    OraPrenotazione time,
    foreign key (Cliente) references CLIENTI(CodiceFiscale) on delete cascade
);

create table PRENOTAZIONIEVENTI(
	IdPrenotazioneEvento char(5) primary key,
    Cliente char(16),
    Biglietto char(5) references BIGLIETTI(IdBiglietto),
    Evento char(5) references EVENTI(IdEvento),
    foreign key (Cliente) references CLIENTI(CodiceFiscale) on delete cascade
);

create table PRENOTAZIONISTRUTTURE(
	IdPrenotazioneStruttura char(5) primary key,
    Cliente char(16),
    Struttura varchar(15) references STRUTTUREVILLAGGIO(IdStruttura),
    Tessera char(5) references TESSERE(IdTessera),
    foreign key (Cliente) references CLIENTI(CodiceFiscale) on delete cascade
);

create table MOVIMENTI(
    Tessera char(5) references TESSERE(IdTessera),
    Lettore char(3) references LETTORI(IdLettore),
    Tipo boolean NOT NULL
);
insert into CLIENTI values 
("AMNNCC66G32N523K", "Niccol�", "Ammaniti"),
("FRNELN43B54D432N", "Elena", "Ferrante"),
("CRSDNT73B24C634L", "Donato", "Carrisi"),
("CGNPLO78H12N234D", "Paolo", "Cognetti"),
("PSTRSL78F34D519C", "Rossella", "Postorino"),
("FRNSLV98B43G645F", "Silvia", "Fernandez");

insert into EVENTI values 
("EV001", "Portaria", "Escursione", "Il vecchio convento abbandonato dei frati minori cappuccini di San Pietro di Portaria Ã¨ tappa del percorso in questione.", "2021-10-18", "19:00"),
("EV002", "La Cerreta","Escursione","Un giro contraddistinto da un susseguirsi di continui saliscendi, per le colline nei dintorni di Sangemini.", "2021-12-30", "20:00"),
("EV003", "La Fenice in live", "Spettacolo", " la Fenice offrirï¿½ al suo pubblico ancora una volta la grande musica interpretata dai piï¿½ grandi artisti del veneziano", "2021-11-15", "20:30"),
("EV004", "Campionato di pallavolo", "Evento Sportivo", "Capionato di pallavolo tra i clienti del villaggio", "2021-09-16", "21:00"),
("EV005", "Le mille note di Beethoven ", "Spettacolo", "Il gruppo Rivoletto si esibira' per voi presentando i piï¿½ grandi pezzi dell'illustre Ludwig van Beethoven ", "2021-04-24", "21:30"),
("EV006", "Once upon a time", "Spettacolo", "Rivisitazione dei grandi classici di Quentin Tarantino","2021-12-25", "19:00");


insert into BIGLIETTI values
("BI001", "12.50", false, "Portaria"),
("BI002", "12.50", false, "La Cerreta"),
("BI003", "15.50", false, "La Fenice in live"),
("BI004", "17.50", false,  "Campionato di pallavolo"),
("BI005", "12.50", false, "Le mille note di Beethoven "),
("BI006", "17.50", false, "Once upon a time"),
("BI007", "17.50", false, "Once upon a time"),
("BI008", "13.50", true, "Campionato di pallavolo"),
("BI009", "18.50", true, "La Cerreta"),
("BI010", "12.50", true, "Portaria");

insert into contitotali values
("CT001", 305.60, "2020-10-24", "AMNNCC66G32N523K"),
("CT002", 400.50, "2020-12-28", "FRNELN43B54D432N"),
("CT003", 250.00, "2020-09-13", "CRSDNT73B24C634L"),
("CT004", 270.40, "2020-07-30", "CGNPLO78H12N234D"),
("CT005", 540.20, "2020-02-10", "PSTRSL78F34D519C"),
("CT006", 230.30, "2020-05-21", "FRNSLV98B43G645F");

insert into strutturevillaggio values
("Campo da tennis", "Sport", 3.00),
("Campo da calcio", "Sport", 4.00);

insert into lettori values
("L01", "Lettore tessere campo da tennis", "Campo da tennis"),
("L02", "Lettore tessere campo da calcio", "Campo da calcio");

insert into tessere values
("TS001", "Tessera per il campo da tennis"),
("TS002", "Tessera per il campo da calcio"),
("TS003", "Tessera per il campo da tennis"),
("TS004", "Tessera per il campo da tennis"),
("TS005", "Tessera per il campo da calcio"),
("TS006", "Tessera per il campo da tennis"),
("TS007", "Tessera per il campo da calcio");

insert into abitazioni values
("Deluxe", 4, 80.00, "Abitazione sita nel centro del nostro splendido villaggio", 2),
("Standard", 4, 60.00, "Abitazione sita nel centro del nostro splendido villaggio", 3),
("Camera doppia", 2, 30.00, "Abitazione con una doppia", 1),
("Camera singola", 1, 20.00, "Abitazione con stanza singola",5),
("Appartamento", 6, 100.00, "Abitazione per comitiva di sei persone", 6),
("Suite", 2, 30.00, "Abitazione con stanza doppia perfetta per coppie", 4);

insert into contiristorante values
("CR001","PR002", 80.00),
("CR003","PR003", 60.50),
("CR004","PR004", 67.30),
("CR005","PR005", 58.30),
("CR006","PR006", 120.40);

insert into tavoliristorante values
("01", 6),
("02", 4),
("03", 8),
("04", 10),
("05", 3),
("06", 6),
("07", 4);

insert into servizi values
("SE001", "Pulizia camere", "09:00:00", "11:00:00"),	#bisogna gestire le date
("SE002", "Guida per le escursioni", "12:00:00", "16:00:00"),
("SE003", "Collaudatore dello spettacolo", "20:00:00", "23:00:00"),
("SE004", "Addetto alla reception", "09:00:00", "18:00:00");


insert into dipendenti values
("PNDRYN08R25A662F", "Ryan", "Pandolfi", "Amministratore", 1600.00),
("MRALSN08C52F205P", "Alessandra", "Mauri", "Addetto alla Reception", 1400.00),
("GRNMTT08T29L219Z", "Matteo", "Grandi", "Guida", 1400.00),
("GRNRSL06H65D969E", "Ursula", "Grande", "Cameriera", 1400.00),
("DMRGVS03C29A662P", "Gervaso", "Demarco", "Tecnico spettacoli", 1400.00);

insert into accounts values
("ryanp12", "PNDRYN08R25A662F", "ryan12", 3),
("alessandram90", "MRALSN08C52F205P", "ales90",2),
("matteog40", "GRNMTT08T29L219Z", "matt40", 1),
("gervasod99", "DMRGVS03C29A662P", "gerva99", 1);

insert into turnidilavoro values
("MRALSN08C52F205P", "SE004", "2021-12-14"),
("GRNRSL06H65D969E", "SE001", "2021-12-14"),
("GRNMTT08T29L219Z", "SE002", "2021-12-15"),
("DMRGVS03C29A662P", "SE003", "2021-12-16");

insert into prenotazioniabitazioni values
("PA01","AMNNCC66G32N523K", "Deluxe", "2021-02-01", curdate()),
("PA02","FRNELN43B54D432N", "Standard",  "2021-12-13", "2021-12-23"),
("PA03", "CRSDNT73B24C634L", "Camera doppia", "2021-10-10", "2021-11-02"),
("PA04", "CGNPLO78H12N234D", "Appartamento", "2021-08-13", "2021-09-23"),
("PA05","PSTRSL78F34D519C", "Camera singola", "2021-01-13", "2021-02-13"),
("PA06", "FRNSLV98B43G645F", "Suite", "2021-01-22", "2021-02-23");


insert into prenotazioniristorante values
("PR001","AMNNCC66G32N523K", "01", "2021-02-02","20:00"),
("PR002","FRNELN43B54D432N", "02", "2021-12-15","21:00"),
("PR003","AMNNCC66G32N523K", "03", "2021-10-30","21:30"),
("PR004","CGNPLO78H12N234D", "04", "2021-08-15","20:30"),
("PR005","PSTRSL78F34D519C", "05", "2021-01-16","21:30"),
("PR006","CRSDNT73B24C634L", "06", "2021-12-24","21:30");


insert into prenotazionieventi values
("PE001", "AMNNCC66G32N523K", "BI001", "EV001"),
("PE002","FRNELN43B54D432N", "BI002", "EV002"),
("PE003","AMNNCC66G32N523K", "BI003", "EV003"),
("PE004","CRSDNT73B24C634L", "BI004", "EV004"),
("PE007","CGNPLO78H12N234D", "BI005", "EV005"),
("PE005","CGNPLO78H12N234D", "BI006", "EV006"),
("PE006","PSTRSL78F34D519C", "BI007", "EV006");


insert into prenotazionistrutture values
("PS001","AMNNCC66G32N523K", "Campo da tennis", "TS001"),
("PS002","FRNELN43B54D432N", "Campo da tennis", "TS003"),
("PS003","PSTRSL78F34D519C", "Campo da calcio", "TS002"),
("PS004","CGNPLO78H12N234D", "Campo da calcio", "TS005");

insert into movimenti values
("TS001", "L01", true),
("TS003", "L01", true),
("TS002", "L02", true),
("TS005", "L02", true);