# Bike tracker

## Sovelluksen kuvaus

Sovellusta voi käyttää pyöräilypäiväkirjana, joka koostuu päiväkohtaisista muistiinpanoista. Sovellusta voi käyttää useampi rekisteröitynyt käyttäjä, ja heillä on kaikilla omat yksilölliset muistiinpanonsa. Sovelluksessa voi:

* kirjata talteen päiväkohtaiset kilometrisaldot ja päivään liittyvät muistiinpanot
* tarkastella kuinka paljon kilometreja on kertynyt yhteensä
* tarkastella listaa kaikista päiväkohtaisista muistiinpanoista
* poistaa päiväkohtaisen muistiinpanon

## Dokumentaatio

[Kayttöohje](https://github.com/tsalohei/bike-tracker/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/tsalohei/bike-tracker/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/tsalohei/bike-tracker/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/tsalohei/bike-tracker/blob/master/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/tsalohei/bike-tracker/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

[Viikko 6](https://github.com/tsalohei/bike-tracker/releases/tag/vko6)

## Käskyt komentoriviltä

### Ohjelman suorittaminen

Ohjelma käynnistetään komennolla. 

	mvn compile exec:java -Dexec.mainClass=com.salohei.Main

### Testit

Suorita testit ollessasi  komennolla:

	mvn test

Luo testikattavuusraportti komennolla:

	mvn test jacoco:report

Raporttia pääsee katsomaan, kun avaa selaimessa hakemistosta /target/site/jacoco löytyvän tiedoston nimeltä index.html.

### Jar-tiedoston luominen

Luo suoritettava jar-tiedosto komennolla:

	mvn package

minkä jälkeen hakemistosta _target_ löytyy suoritettava jar-tiedosto nimeltä app-1.0-SNAPSHOT.jar

### Checkstyle

Suorita tiedostoon [checkstyle.xml](https://github.com/tsalohei/bike-tracker/blob/master/app/checkstyle.xml)  määritellyt tarkistukset komennolla: 

	mvn jxr:jxr checkstyle:checkstyle

Checkstyle-raporttia virheilmoituksineen pääsee tarkastelemaan, kun avaa selaimella tiedoston /target/site/checkstyle.html  
 
### JavaDoc

Luo JavaDocista HTML-versio komennolla: 
	
	mvn javadoc:javadoc

Luotu JavaDoc löytyy hakemistosta target/site/apidocs/.
