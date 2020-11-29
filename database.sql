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
    Descrizione varchar(200) NOT NULL
);

create table BIGLIETTI(
	IdBiglietto char(5) PRIMARY KEY,
    Costo decimal(4,2) NOT NULL,
    Disponibilit� boolean NOT NULL
);

create table CONTITOTALI(
	NumeroConto char(5) PRIMARY KEY,
    Importo decimal(6,2) NOT NULL,
    DataPagamento date NOT NULL,
    Cliente char(16) references CLIENTI(CodiceFiscale)
);

create table STRUTTUREVILLAGIO(
	IdStruttura char(5) PRIMARY KEY,
    Tipo varchar(20) NOT NULL,
    Tariffa decimal(4,2) NOT NULL
);

create table LETTORI(
	IdLettore char(3) PRIMARY KEY,
    DescrizioneLettore varchar(50) NOT NULL,
    StrutturaVillaggio char(5) references STRUTTUREVILLAGIO(IdStruttura)
);

create table TESSERE(
	IdTessera char(5) PRIMARY KEY,
    DescrizioneTessera varchar(50) NOT NULL
);

create table ABITAZIONI(
	IdAbitazione varchar(15) PRIMARY KEY,
    PostiLetto decimal(2) NOT NULL,
    Tariffa decimal(5,2) NOT NULL,
    Descrizione varchar(100) NOT NULL
);

create table CONTIRISTORANTE(
	IdConto char(5) PRIMARY KEY,
    ContoTotale decimal(5,2) NOT NULL
);

create table TAVOLIRISTORANTE(
	NumeroTavolo varchar(2) PRIMARY KEY,
    NumeroPosti decimal(2) NOT NULL
);

create table SERVIZI(
	IdTurno char(5) PRIMARY KEY,
    DescrizioneServizio varchar(100) NOT NULL,
    DataInizio time NOT NULL,
	DataFine time NOT NULL
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
    Servizio char(5) references SERVIZI(IdTurno)
);

create table PRENOTAZIONIABITAZIONI(
	IdPrenotazioneAbitazione char(5) primary key,
    Cliente char(16) references CLIENTI(CodiceFiscale),
    Abitazione varchar(15) references ABITAZIONI(IdAbitazione),
    DataInizio date not null,
    DataFine date not null
);

create table PRENOTAZIONIRISTORANTE(
    Cliente char(16) references CLIENTI(CodiceFiscale),
    TavoloRistorante varchar(2) references TAVOLI(NumeroTavolo),
    ContoRistorante char(5) references CONTIRISTORANTE(IdConto)
);

create table PRENOTAZIONIEVENTI(
    Cliente char(16) references CLIENTI(CodiceFiscale),
    Biglietto char(5) references BIGLIETTI(IdBiglietto),
    Evento char(5) references EVENTI(IdEvento)
);

create table PRENOTAZIONISTRUTTURE(
    Cliente char(16) references CLIENTI(CodiceFiscale),
    StrutturaVillaggio char(5) references STRUTTUREVILLAGGIO(IdStruttura),
    Tessera char(5) references TESSERE(IdTessera)
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
("EV001", "Portaria", "Escursione", "Il vecchio convento abbandonato dei frati minori cappuccini di San Pietro di Portaria � tappa del percorso in questione."),
("EV002", "La Cerreta","Escursione","Un giro contraddistinto da un susseguirsi di continui saliscendi, per le colline nei dintorni di Sangemini."),
("EV003", "La Fenice in live", "Spettacolo", " la Fenice offrir� al suo pubblico ancora una volta la grande musica interpretata dai pi� grandi artisti del veneziano"),
("EV004", "Campionato di pallavolo", "Evento Sportivo", "Capionato di pallavolo tra i clienti del villaggio"),
("EV005", "Le mille note di Beethoven ", "Spettacolo", "Il gruppo Rivoletto si esibira' per voi presentando i pi� grandi pezzi dell'illustre Ludwig van Beethoven "),
("EV006", "Once upon a time", "Spettacolo", "Rivisitazione dei grandi classici di Quentin Tarantino");

#ho impostato la disponibilit� a true per semplicit�, secondo me potremmo anche toglierla

insert into BIGLIETTI values
("BI001", "12.50", true),
("BI002", "12.50", true),
("BI003", "15.50", true),
("BI004", "17.50", true),
("BI005", "12.50", true),
("BI006", "17.50", true),
("BI007", "17.50", true),
("BI008", "13.50", true),
("BI009", "18.50", true),
("BI010", "12.50", true);

insert into contitotali values
("CT001", 305.60, "2020-10-24", "AMNNCC66G32N523K"),
("CT002", 400.50, "2020-12-28", "FRNELN43B54D432N"),
("CT003", 250.00, "2020-09-13", "CRSDNT73B24C634L"),
("CT004", 270.40, "2020-07-30", "CGNPLO78H12N234D"),
("CT005", 540.20, "2020-02-10", "PSTRSL78F34D519C"),
("CT006", 230.30, "2020-05-21", "FRNSLV98B43G645F");

insert into strutturevillagio values
("SV001", "Campo tennis", 3.00),
("SV002", "Campo da calcio", 4.00);

insert into lettori values
("L01", "Lettore tessere campo da tennis", "SV001"),
("L02", "Lettore tessere campo da calcio", "SV002");

insert into tessere values
("TS001", "Tessera per il campo da tennis"),
("TS002", "Tessera per il campo da calcio"),
("TS003", "Tessera per il campo da tennis"),
("TS004", "Tessera per il campo da tennis"),
("TS005", "Tessera per il campo da calcio"),
("TS006", "Tessera per il campo da tennis"),
("TS007", "Tessera per il campo da calcio");

insert into abitazioni values
("Deluxe", 4, 80.00, "Abitazione sita nel centro del nostro splendido villaggio"),
("Standard", 4, 60.00, "Abitazione sita nel centro del nostro splendido villaggio"),
("Camera doppia", 2, 30.00, "Abitazione con una doppia"),
("Camera singola", 1, 20.00, "Abitazione con stanza singola"),
("Appartamento", 6, 100.00, "Abitazione per comitiva di sei persone"),
("Suite", 2, 30.00, "Abitazione con stanza doppia perfetta per coppie");

insert into contiristorante values
("CR001", 80.00),
("CR002", 90.50),
("CR003", 60.50),
("CR004", 67.30),
("CR005", 58.30),
("CR006", 120.40);

insert into tavoliristorante values
("1", 6),
("2", 4),
("3", 8),
("4", 10),
("5", 3),
("6", 6),
("7", 4);

insert into servizi values
("SE001", "Pulizia camere", 0900, 1100),	#bisogna gestire le date
("SE002", "Guida per le escursioni", 1200, 1600),
("SE003", "Collaudatore dello spettacolo", 2000, 2300),
("SE004", "Addetto alla reception", 0900, 1800);


insert into dipendenti values
("PNDRYN08R25A662F", "Ryan", "Pandolfi", "Amministratore", 1600.00),
("MRALSN08C52F205P", "Alessandra", "Mauri", "Addetto alla Reception", 1400.00),
("GRNMTT08T29L219Z", "Matteo", "Grandi", "Guida", 1400.00),
("GRNRSL06H65D969E", "Ursula", "Grande", "Cameriera", 1400.00),
("DMRGVS03C29A662P", "Gervaso", "Demarco", "Tecnico spettacoli", 1400.00);

insert into accounts values
("ryanp12", "PNDRYN08R25A662F", "ryan12", 1),
("alessandram90", "MRALSN08C52F205P", "ales90",2),
("matteog40", "GRNMTT08T29L219Z", "matt40", 3),
("gervasod99", "DMRGVS03C29A662P", "gerva99", 2);

insert into turnidilavoro values
("MRALSN08C52F205P", "SE004"),
("GRNRSL06H65D969E", "SE001"),
("GRNMTT08T29L219Z", "SE002"),
("DMRGVS03C29A662P", "SE003");

insert into prenotazioniabitazioni values
("PA01","AMNNCC66G32N523K", "AB001", "2020-10-13", "2020-10-23"),
("PA02","FRNELN43B54D432N", "AB002",  "2020-12-13", "2020-12-23"),
("PA03", "CRSDNT73B24C634L", "AB003", "2020-10-10", "2020-11-02"),
("PA04", "CGNPLO78H12N234D", "AB004", "2020-08-13", "2020-10-23"),
("PA05","PSTRSL78F34D519C", "AB005", "2021-01-13", "2021-02-13"),
("PA06", "FRNSLV98B43G645F", "AB006", "2020-12-22", "2021-01-23");


insert into prenotazioniristorante values
("AMNNCC66G32N523K", "1", "CR001"),
("FRNELN43B54D432N", "2", "CR002"),
("AMNNCC66G32N523K", "3", "CB003"),
("CGNPLO78H12N234D", "4", "CB004");


insert into prenotazionieventi values
("AMNNCC66G32N523K", "BI001", "EV001"),
("FRNELN43B54D432N", "BI002", "EV002"),
("AMNNCC66G32N523K", "BI003", "EV003"),
("CRSDNT73B24C634L", "BI004", "EV004"),
("CGNPLO78H12N234D", "BI005", "EV005"),
("CGNPLO78H12N234D", "BI006", "EV006"),
("PSTRSL78F34D519C", "BI007", "EV006");


insert into prenotazionistrutture values
("AMNNCC66G32N523K", "SV001", "TS001"),
("FRNELN43B54D432N", "SV001", "TS003"),
("PSTRSL78F34D519C", "SV002", "TS002"),
("CGNPLO78H12N234D", "SV002", "TS005");

insert into movimenti values
("TS001", "L01", true),
("TS003", "L01", true),
("TS002", "L02", true),
("TS005", "L02", true);