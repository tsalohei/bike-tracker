# Arkkitehtuurikuvaus

## Rakenne

Ohjelma on rakenteeltaan kolmitasoinen kerrosarkkitehtuuri. Pakkausrakenne on seuraavanlainen:

![Pakkauskaavio](https://raw.githubusercontent.com/tsalohei/bike-tracker/master/dokumentaatio/kuvat/bike-tracker_pakkauskaavio.png "Pakkauskaavio")

Pakkaus com.salohei.ui sisältää tekstikäyttöliittymänä toteutetun käyttöliittymän, com.salohei.domain sovelluslogiikan ja com.salohei.dao sisältää koodin, joka vastaa tietojen pysyväistalletuksesta. 

## Käyttöliittymä

Käyttöliittymä on toteutettu tekstikäyttöliittymänä. Ohjelman käynnistyessä käyttäjälle näytetään eri toimintoihin liittyvät komennot ja käyttäjä voi valita näistä haluamansa. Käyttöliittymän koodi löytyy luokasta com.salohei.ui.TextUi. Sama luokka vastaa myös virheviestien näyttämisestä käyttäjälle. 

Käyttöliittymä on pyritty pitämään erillään sovelluslogiikasta. Käyttöliittymä pyytää käyttäjältä tietoja, ja välittää niitä parametreina sovelluslogiikan toteuttavan NoteService-olion metodeille. Myös virheviestien näyttäminen perustuu siihen, mitä paluuarvoja NoteServicen kutsutut metodit palauttavat. 

## Sovelluslogiikka

Luokat User ja Note muodostavat ohjelman loogisen datamallin. Luokat kuvaavat ohjelman käyttäjiä ja käyttäjien pyöräilymuistiinpanoja: 

![class diagram](https://raw.githubusercontent.com/tsalohei/bike-tracker/master/dokumentaatio/kuvat/luokkakaavio_simple.png "Class diagram") 

Ohjelmassa on kerrallaan vain yksi olio luokasta NoteService. Tämä olio vastaa siitä, että käyttöliittymän omista metodeista syntyy erilaisia toiminnallisia kokonaisuuksia vastaavat toimintoketjut. Käytännössä NoteService-luokka tarjoaa käyttöliittymän kaikille toiminnoille omat metodit, esimerkiksi:

* boolean createUser(String name, String username)
* boolean login(String username)
* boolean createNote(LocalDate date, int km, String content)
* List<Note> getAll()
* int kmTotal()
* boolean deleteNote(LocalDate date)

NoteService ei pääse suoraan käsiksi käyttäjien ja muistiinpanojen tietoihin. Tämä tapahtuu välillisesti NoteDao- ja UserDao rajapinnat toteuttavien luokkien SqlUserDao ja SqlNoteDao kautta. Luokat SqlUserDao ja SqlNoteDao injektoidaan sovelluslogiikan toteuttavalle NoteService oliolle NoteService-olion konstruktorikutsun yhteydessä. 

Tämä yhdistetty luokka/pakkauskaavio kuvaa ohjelman eri osien suhdetta toisiinsa: 

## Luokkakaavio


![class diagram](https://raw.githubusercontent.com/tsalohei/bike-tracker/master/dokumentaatio/kuvat/class-diagram.png "Class diagram")


# Sekvenssikaaviot

## Käyttäjän sisäänkirjautuminen

![sequence diagram](https://raw.githubusercontent.com/tsalohei/bike-tracker/master/dokumentaatio/kuvat/sequenceDiagramUserLogsIn.png "Sequence diagram")
