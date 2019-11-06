# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjien on mahdollista pitää kirjaa siitä, kuinka paljon he ovat pyöräilleet. Sovelluksen käyttäminen edellyttää rekisteröitymistä. Sovellusta voi käyttää useampi käyttäjä, ja heillä kaikilla on oma kirjanpitonsa. 

## Perusversion tarjoama toiminnallisuus 

### Ennen kirjautumista
- käyttäjä voi luoda järjestelmään käyttäjätunnuksen rekisteröitymislomakkeen avulla 
 - käyttäjätunnuksen tulee olla uniikki ja pituudeltaan vähintään 3 merkkiä
 - järjestelmä antaa virheilmoituksen mikäli käyttäjätunnus ei täytä kriteereitä

- käyttäjä voi kirjautua järjestelmään
 - kirjautuminen tapahtuu siten, että olemassaoleva käyttäjätunnus syötetään kirjautumislomakkeelle
 - järjestelmä antaa virheilmoituksen jos syötettyä käyttäjätunnusta ei olekaan olemassa

### Kirjautumisen jälkeen
- käyttäjä näkee kuinka monta kilometriä hän on pyöräillyt kokonaisuudessaan
- käyttäjä näkee listan päiväkohtaisista kilometrisaldoista ja niihin liittyvistä muistiinpanoista
- käyttäjä voi lisätä päiväkohtaisen merkinnän
 - yhdellä kalenteripäivällä voi olla 1 merkintä, tai ei yhtään merkintää
 - päiväkohtaisen kilometrisaldon tulee olla 1-500 kilometriä
 - muistiinpano voi olla 0-200 merkkiä pitkä
- käyttäjä voi poistaa päiväkohtaisen merkinnän
- käyttäjä voi kirjautua ulos järjestelmästä

## Sovelluksen jatkokehitys

Jos aikaa on vielä jäljellä kun perusversio on toteutettu, täydennetään ohjelmaa esimerkiksi seuraavilla toiminnallisuuksilla:
- käyttäjä voi muokata päiväkohtaista merkintää (kilometrimäärää ja muistiinpanoa)
- käyttäjälle lisätään salasana, joka pitää syöttää kirjautumisen yhteydessä
- käyttäjä voi tarkastella kilometrien kertymistä myös visuaalisesti; käyttäjälle näytetään kuvaaja, jossa on esim. x-akselilla on päivämäärät ja y-akselilla päivittäiset kilometrikertymät

