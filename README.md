# Ohjelmistotekniikka-kurssin harjoitustyö: Bike tracker

## Sovelluksen alustava kuvaus

Harjoitustyönä tehtävää sovellusta voi käyttää pyöräiltyjen kilometrien kirjanpitoon. Sovelluksessa voi:

* Kirjata talteen päiväkohtaiset kilometrisaldot ja niihin liittyvät muistiinpanot.
* Tarkastella kuinka paljon kilometreja on kertynyt yhteensä.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/tsalohei/bike-tracker/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/tsalohei/bike-tracker/blob/master/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/tsalohei/bike-tracker/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

## Käskyt komentoriviltä

### Testit

Suorita testit komennolla:

	mvn test

Luo testikattavuusraportti komennolla:

	mvn test jacoco:report

Raporttia pääsee katsomaan, kun avaa selaimessa hakemistosta app/target/site/jacoco löytyvän tiedoston nimeltä index.html.

### Jar-tiedoston luominen

Luo suoritettava jar-tiedosto komennolla:

	mvn package

minkä jälkeen hakemistosta _target_ löytyy suoritettava jar-tiedosto nimeltä app-1.0-SNAPSHOT.jar

### Checkstyle

Suorita tiedostoon [checkstyle.xml](https://github.com/tsalohei/bike-tracker/blob/master/app/checkstyle.xml)  määritellyt tarkistukset komennolla: 

	mvn jxr:jxr checkstyle:checkstyle

Checkstyle-raporttia virheilmoituksineen pääsee tarkastelemaan, kun avaa selaimella tiedoston app/target/site/checkstyle.html  
 
