# Arkkitehtuurikuvaus

## Rakenne

Ohjelma on rakenteeltaan kolmitasoinen kerrosarkkitehtuuri. Pakkausrakenne on seuraavanlainen:

![Pakkauskaavio](https://raw.githubusercontent.com/tsalohei/bike-tracker/master/dokumentaatio/kuvat/bike-tracker_pakkauskaavio.png "Pakkauskaavio")

Pakkaus _com.salohei.ui_ sisältää tekstikäyttöliittymänä toteutetun käyttöliittymän, _com.salohei.domain_ sovelluslogiikan ja _com.salohei.dao_ sisältää koodin, joka vastaa tietojen pysyväistalletuksesta. 

## Käyttöliittymä

Käyttöliittymä on toteutettu tekstikäyttöliittymänä. Ohjelman käynnistyessä käyttäjälle näytetään eri toimintoihin liittyvät komennot ja käyttäjä voi valita näistä haluamansa. Käyttöliittymän koodi löytyy luokasta [com.salohei.ui.TextUi](https://github.com/tsalohei/bike-tracker/blob/master/src/main/java/com/salohei/ui/TextUi.java). Sama luokka vastaa myös virheviestien näyttämisestä käyttäjälle. 

Käyttöliittymä on pyritty pitämään erillään sovelluslogiikasta. Käyttöliittymä pyytää käyttäjältä tietoja, ja välittää niitä parametreina sovelluslogiikan toteuttavan NoteService-olion metodeille. Virheviestien näyttäminen perustuu käyttäjän antamiin syötteisiin sekä siihen, mitä paluuarvoja NoteServicen metodit palauttavat. 

## Sovelluslogiikka

Luokat [User](https://github.com/tsalohei/bike-tracker/blob/master/src/main/java/com/salohei/domain/User.java) ja [Note](https://github.com/tsalohei/bike-tracker/blob/master/src/main/java/com/salohei/domain/Note.java) muodostavat ohjelman loogisen datamallin. Luokat kuvaavat ohjelman käyttäjiä ja käyttäjien päiväkohtaisia muistiinpanoja: 

![class diagram](https://raw.githubusercontent.com/tsalohei/bike-tracker/master/dokumentaatio/kuvat/luokkakaavio_simple.png "Class diagram") 

Ohjelmassa on kerrallaan vain yksi olio luokasta [NoteService](https://github.com/tsalohei/bike-tracker/blob/master/src/main/java/com/salohei/domain/NoteService.java). Tämä olio vastaa siitä, että käyttöliittymän omista metodeista syntyy erilaisia toiminnallisia kokonaisuuksia vastaavat toimintoketjut. Käytännössä NoteService-luokka tarjoaa käyttöliittymän kaikille toiminnoille omat metodit, esimerkiksi:

* boolean createUser(String name, String username)
* boolean login(String username)
* boolean createNote(LocalDate date, int km, String content)
* List<Note> getAll()
* int kmTotal()
* boolean deleteNote(LocalDate date)

NoteService ei pääse suoraan käsiksi käyttäjien ja muistiinpanojen tietoihin. Tämä tapahtuu välillisesti [NoteDao](https://github.com/tsalohei/bike-tracker/blob/master/src/main/java/com/salohei/dao/NoteDao.java)- ja [UserDao](https://github.com/tsalohei/bike-tracker/blob/master/src/main/java/com/salohei/dao/UserDao.java) rajapinnat toteuttavien luokkien [SqlUserDao](https://github.com/tsalohei/bike-tracker/blob/master/src/main/java/com/salohei/dao/SqlUserDao.java) ja [SqlNoteDao](https://github.com/tsalohei/bike-tracker/blob/master/src/main/java/com/salohei/dao/SqlNoteDao.java) kautta. Luokat SqlUserDao ja SqlNoteDao injektoidaan sovelluslogiikan toteuttavalle NoteService oliolle NoteService-olion konstruktorikutsun yhteydessä. 

Tämä yhdistetty luokka/pakkauskaavio kuvaa ohjelman eri osien suhdetta toisiinsa: 

![Luokat ja pakkaukset](https://raw.githubusercontent.com/tsalohei/bike-tracker/master/dokumentaatio/kuvat/luokka-ja-pakkauskaavio.png "Luokat ja pakkaukset")

## Tietojen pysyväistallennus

Pakkauksen com.salohei.dao luokat SqlUserDao ja SqlNoteDao ovat vastuussa tietojen tallettamisesta SQLite-tietokantaan. 

Luokat ovat yksi mahdollinen toteutus rajapinnoista UserDao ja NoteDao. Jos tietojen tallennustapa haluttaisiin vaihtaa johonkin muuhun, rajapinnoista voitaisiin tehdä jotkut toiset toteutukset. 

### Tietokanta

Sovellus tallentaa käyttäjien ja heidän päiväkohtaisten muistiinpanojensa tiedot yhteen SQLite-tietokantaan; käyttäjät yhteen tietokantatauluun ja päiväkohtaiset muistiinpanot toiseen tietokantatauluun. Tietokantataulut luodaan pakkauksen com.salohei.dao luokassa [Database](https://github.com/tsalohei/bike-tracker/blob/master/src/main/java/com/salohei/dao/Database.java).

Tietokannan nimi määritellään konfiguraatiotiedostossa nimeltä [config.properties](https://github.com/tsalohei/bike-tracker/blob/master/src/main/resources/config.properties). 

Sovellus tallettaa käyttäjien tiedot seuraavankaltaiseen tietokantatauluun:

	CREATE TABLE User 
	(
	    id integer PRIMARY KEY,
	    name text NOT NULL,
	    username text NOT NULL
	);

Tietokantataulun ensimmäiseen sarakkeeseen talletataan käyttäjän tunniste (id), toiseen sarakkeeseen käyttäjän nimi ja kolmanteen sarakkeeseen käyttäjänimi.

Päiväkohtaiset muistiinpanot talletetaan vastaavasti tällaiseen tietokantatauluun:

	CREATE TABLE Note
	(
	    id integer PRIMARY KEY,
	    date date NOT NULL,
	    km integer NOT NULL,
	    content text NOT NULL,
	    user integer NOT NULL,
	    FOREIGN KEY(user) REFERENCES User(id)
	);

Ensimmäisessä sarakkeessa on päiväkohtaisen muistiinpanon yksilöivä tunnus (id), ja tätä seuraavissa sarakkeissa päivämäärä, kilometrit, tekstimuistiinpanot sekä viimeisenä sarakkeena vierasavain käyttäjän tunnukseen.

## Päätoiminnallisuudet

Alla on kuvattu sovelluksen toimintalogiikkaa muutaman päätoiminnallisuuden osalta, sekvenssikaavioita hyödyntäen.

### Käyttäjän sisäänkirjautuminen

Kun käyttäjä syöttää käyttöliittymään login-toiminnallisuutta vastaavan komennon 1 ja painaa enter, etenee sovelluksen kontrolli seuraavalla tavalla: 

![sequence diagram](https://raw.githubusercontent.com/tsalohei/bike-tracker/master/dokumentaatio/kuvat/sequenceDiagramUserLogsIn.png "Sequence diagram")

Kun käyttäjä on antanut komennon 1, tekstikäyttöliittymä tarkastaa NoteService:n metodia kutsumalla, että järjestelmässä ei ole ketään kirjautuneena sisään. Mikäli asia on kunnossa, tekstikäyttöliittymän login-metodi kutsuu NoteService-olion omaa login-metodia antaen sille parametriksi käyttäjän antaman käyttäjätunnuksen. 

NoteService-olio kutsuu UserDao:n metodia findByUsername, antaen sille parametriksi äsken käyttöliittymästä saadun käyttäjänimen. Jos käyttäjänimeä vastaava käyttäjä löytyy, UserDao antaa sen paluuarvona NoteServicelle. Tämän jälkeen NoteService asetettaa kyseisen käyttäjän kirjautuneeksi sisään, ja palauttaa käyttöliittymään arvon true.


### Uuden päivittäisen muistiinpanon luominen

Kun käyttäjä syöttää käyttöliittymään Create new note -toiminnallisuutta vastaavan komennon 3 ja painaa enter, etenee sovellluksen kontrolli seuraavalla tavalla:

![sequence diagram](https://raw.githubusercontent.com/tsalohei/bike-tracker/master/dokumentaatio/kuvat/sequenceDiagramUserCreatesNewNote.png "Sequence diagram")

Aluksi tekstikäyttöliittymä tarkastaa NoteService:n metodia kutsumalla, että komennon 3 antanut käyttäjä on myös kirjautunut sisään järjestelmään. Mikäli näin on, tekstikäyttöliittymän createNote-metodi kutsuu NoteService-olion omaa createNote-metodia antaen sille parametriksi käyttäjän antamat tiedot: päivämäärän, kilometrimäärän, ja tekstimuistiinpanot. NoteService-oliolla on tieto siitä, kuka on sovelluksen senhetkinen, sisäänkirjautunut käyttäjä. 

NoteService-olio kutsuu NoteDao:n metodia createNote, antaen sille parametriksi kaikki tiedot mitä äsken sai itse käyttöliittymästä, ja sen lisäksi tiedon sisäänkirjautuneesta käyttäjästä. NoteDao luo näitä tietoja vastaavan note:n tietokantaan, ja palauttaa note:n NoteService-oliolle. Jos palautus tapahtuu onnistuneesti, lähettää NoteService paluuarvona sitä kutsuneelle käyttöliittymän metodille arvon “true”. 


### Kaikkien pyöräilymuistiinpanojen tarkasteleminen

### Kokonaiskilometrimäärän tarkasteleminen

### Pyöräilymuistiinpanon poistaminen päivämäärän perusteella

## Sovelluksen rakenteeseen jääneitä heikkouksia

Käyttöliittymässä on koodia, joka käsittelee listoja ja printtaa 
