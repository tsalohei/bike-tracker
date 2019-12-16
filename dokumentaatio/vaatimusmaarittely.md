# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjien on mahdollista pitää pyöräilypäiväkirjaa, joka koostuu päiväkohtaisista muistiinpanoista. Yhteen päiväkohtaiseen muistiinpanoon kirjataan päivämäärä, kilometrisaldo ja vapaamuotoiset muistiinpanot. Sovelluksen käyttäminen edellyttää rekisteröitymistä. Useampi käyttäjä voi käyttää sovellusta, ja heillä on kaikilla omat päiväkohtaiset muistiinpanonsa.

## Toimintaympäristön rajoitteet

Sovellus toimii koneissa, joissa on Linux- tai OSX-käyttöjärjestelmä. Sovellus toimii Javan versiolla 1.8. Lisäksi, jotta JavaDoc toimii, tulee JAVA_HOME-muuttujan olla asetettu. Käyttäjien ja heidän päiväkohtaisten muistiinpanojensa tiedot tallennetaan paikallisen koneen levylle. 

## Perusversion tarjoama toiminnallisuus 

### Ennen kirjautumista
- uusi käyttäjä voi luoda järjestelmään käyttäjätunnuksen, joka on uniikki ja pituudeltaan 2-30 merkkiä (tehty) 
  - järjestelmä antaa virheilmoituksen mikäli käyttäjätunnus ei täytä kriteereitä (tehty)
- uudelta käyttäjältä pyydetään käyttäjätunnuksen luomisen yhteydessä myös nimeä, jonka tulee olla pituudeltaan 2-30 merkkiä (tehty)
  - järjestelmä antaa virheilmoituksen mikäli nimi ei täytä kriteereitä (tehty)

- käyttäjä voi kirjautua järjestelmään syöttämällä aiemmin luomansa käyttäjätunnuksen käyttöliittymään (tehty)
  - järjestelmä antaa virheilmoituksen jos syötettyä käyttäjätunnusta ei olekaan olemassa (tehty)

### Kirjautumisen jälkeen
- käyttäjä näkee kuinka monta kilometriä hän on pyöräillyt kokonaisuudessaan (tehty)
- käyttäjä näkee listan päiväkohtaisista muistiinpanoista (tehty)
- käyttäjä voi lisätä päiväkohtaisen muistiinpanon (yhteen päivään voi liittyä yksi tai ei yhtään merkintää) (tehty)
  - päivämäärä ei voi olla tulevaisuudessa, ja päivämäärä tulee antaa käyttöliittymän pyytämässä muodossa (tehty)
  - päiväkohtaisen kilometrisaldon tulee olla 1-500 kilometriä (tehty)
  - muistiinpano voi olla 1-200 merkkiä pitkä (tehty)
  - jos joku käyttäjän antamista tiedoista ei ole odotetun kaltainen, antaa järjestelmä virheilmoituksen ja pyytää tietoa uudelleen (tehty)
- käyttäjä voi poistaa päiväkohtaisen muistiinpanon (tehty)
  - jos käyttäjä yrittää poistaa päiväkohtaista muistiinpanoa, jota ei ole olemassa, antaa järjestelmä virheilmoituksen (tehty)
- jos käyttäjä yrittää tehdä jotain, mikä edellyttää kirjautumista, antaa järjestelmä virheilmoituksen (tehty)
- käyttäjä voi kirjautua ulos järjestelmästä (tehty)

## Sovelluksen jatkokehitys

Jos aikaa on vielä jäljellä kun perusversio on toteutettu, täydennetään ohjelmaa esimerkiksi seuraavilla toiminnallisuuksilla:
- käyttäjä voi muokata päiväkohtaista muistiinpanoa
- käyttäjälle lisätään salasana, joka pitää syöttää kirjautumisen yhteydessä

