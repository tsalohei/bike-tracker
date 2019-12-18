# Testausdokumentti

Sovellusta on testattu automatisoiduilla yksikkö- ja integraatiotesteillä (JUnit-testit). Järjestelmätason testaus on tapahtunut manuaalisesti.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Sovelluslogiikan toteuttavat luokat sijaitsevat pakkauksessa [com.salohei.domain](https://github.com/tsalohei/bike-tracker/tree/master/src/main/java/com/salohei/domain). Näitä on testattu yksikkötesteillä [NoteServiceUserTest](https://github.com/tsalohei/bike-tracker/blob/master/src/test/java/com/salohei/domain/NoteServiceUserTest.java) ja [NoteServiceNoteTest](https://github.com/tsalohei/bike-tracker/blob/master/src/test/java/com/salohei/domain/NoteServiceNoteTest.java) hyödyntäen [Mockito-valekomponentteja](https://site.mockito.org/). Tässä lähestymistavassa DAO-luokkien tekemien tietokantahakujen tilalla on Mock-olioita, joiden paluuarvot voi määritellä kunkin testin kannalta sopivaksi (Mock-olion muoto voi olla esimerkiksi seuraava: kun userDao:n metodia x kutsutaan arvolla z, niin anna paluuarvona y). 

Integraatiotesti [NoteServiceIntegrationTest](https://github.com/tsalohei/bike-tracker/blob/master/src/test/java/com/salohei/domain/NoteServiceIntegrationTest.java) puolestaan hyödyntää keskusmuistiin tallennettavaa testitietokantaa. Sovelluslogiikan testitapaukset simuloivat toiminnallisuuksia, joita käyttöliittymä suorittaa NoteService-olion avulla. Luokille User ja Note ei ole tehty erikseen omia testejä, koska ne sisältävät vain yksinkertaisia gettereitä ja settereitä.  

### DAO-luokat

Molemmat DAO-luokat on testattu luomalla keskusmuistiin tallennettava testitietokanta.

### Testauskattavuus

Sovelluksen testauksen rivikattavuus on 89% ja haaraumakattavuus 86%. Käyttöliittymää ei kuitenkaan sisällytetty testeihin eikä näihin lukuihin. 

![Testauskattavuus](https://raw.githubusercontent.com/tsalohei/bike-tracker/master/dokumentaatio/kuvat/testauskattavuus.png "Testauskattavuus")

## Järjestelmätestaus

Sovelluksen järjestelmätestaus suoritettiin manuaalisesti.

### Asennus ja konfigurointi

ASENNUS JA KONFIGUROINTI

### Toiminnallisuuksien testaaminen

Kaikki [vaatimusmäärittelydokumentissa](https://github.com/tsalohei/bike-tracker/blob/master/dokumentaatio/vaatimusmaarittely.md) kerrotut toiminnallisuudet on testattu manuaalisesti. Sovelluksen toimintaa on testattu myös täyttämällä syötekentät virheellisillä arvoilla kuten tyhjillä kentillä ja kirjaimilla numeroa edellyttävissä kentissä.

LAATUONGELMAT 

