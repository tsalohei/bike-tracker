# Käyttöohje

Lataa tiedosto bike-tracker.jar 

## Asentaminen ja konfigurointi

Käyttäjän täytyy luoda konfiguraatiotiedosto _config.properties_, joka määrittelee käytettävän tietokannan nimen. Tiedoston tulee sijaita ohjelman käynnistyshakemistossa. Tiedoston formaatti on seuraava: 

	databaseAddress=jdbc:sqlite:tietokanta.db

## Ohjelman käynnistäminen

Käynnistä ohjelma seuraavalla komennolla:

	java -jar bike-tracker.jar

## Kirjautuminen

Sovellus käynnistyy näkymään, jossa on listattu kaikki tarjolla olevat toiminnot. Jo aiemmin rekisteröityneen käyttäjän kirjautuminen tapahtuu valitsemalla komento 1, painamalla enter, kirjoittamalla käyttäjänimi tekstikenttään, ja painamalla enter.

![Login](https://raw.githubusercontent.com/tsalohei/bike-tracker/master/dokumentaatio/kuvat/login.png "Kirjautuminen")

## Uuden käyttäjän luominen

Uusi käyttäjä luodaan kirjoittamalla komento 2 ja painamalla enter, kirjoittamalla nimi ja painamalla enter, ja kirjoittamalla käyttäjänimi ja painamalla enter. 

![Uusi käyttäjä](https://raw.githubusercontent.com/tsalohei/bike-tracker/master/dokumentaatio/kuvat/register.png "Uusi käyttäjä")

## Pyöräilymuistiinpanon luominen

Uusi pyöräilymuistiinpano luodaan kirjoittamalla komento 3, ja antamalla vastaukset kuhunkin käyttöliittymän kysymään kohtaan: päivämäärä, kilometrien määrä, ja vapaamuotoiset muistiinpanot. Jokaisen kohdan jälkeen painetaan enter.

![Uusi muistiinpano](https://raw.githubusercontent.com/tsalohei/bike-tracker/master/dokumentaatio/kuvat/new-note.png "Uusi muistiinpano")

## Muut toiminnot

Muut toiminnot (kokonaiskilometrien katsominen, kaikkien muistiinpanojen selaaminen, pyöräilymuistiinpanon poistaminen, uloskirjautuminen) toimivat samalla periaatteella kuin ylläkuvatut toiminnot: käyttäjä kirjoittaa haluamansa komennon, painaa enter, ja kirjoittaa lisätietoja jos käyttöliittymä pyytää niitä.

 


