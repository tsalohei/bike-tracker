# Rakenne

Ohjelman on rakenteeltaan kolmitasoinen kerrosarkkitehtuuri. Pakkausrakenne on seuraavanlainen:

![Pakkauskaavio](https://raw.githubusercontent.com/tsalohei/bike-tracker/master/dokumentaatio/kuvat/pakkauskaavio.png "Pakkauskaavio")

Pakkaus com.salohei.ui sisältää tekstikäyttöliittymänä toteutetun käyttöliittymän, com.salohei.domain sovelluslogiikan ja com.salohei.dao sisältää 
koodin, joka vastaa tietojen pysyväistalletuksesta. 

# Käyttöliittymä

Käyttöliittymä on toteutettu tekstikäyttöliittymänä. Ohjelman käynnistyessä käyttäjälle näytetään eri toimintoihin liittyvät komennot ja 
käyttäjä voi valita näistä haluamansa. Käyttöliittymän koodi löytyy luokasta com.salohei.ui.TextUi. Sama luokka vastaa myös virheviestien
näyttämisestä käyttäjälle. 

Käyttöliittymä on pyritty pitämään erillään sovelluslogiikasta. Käyttöliittymä pyytää käyttäjältä tietoja, ja välittää niitä parametreina
sovelluslogiikan toteuttavan NoteService-olion metodeille. Myös virheviestien näyttäminen perustuu siihen, mitä paluuarvoja NoteServicen
kutsutut metodit palauttavat. 

# Sovelluslogiikka



# Luokkakaavio


![class diagram](https://raw.githubusercontent.com/tsalohei/bike-tracker/master/dokumentaatio/kuvat/class-diagram.png "Class diagram")


# Sekvenssikaaviot

## Käyttäjän sisäänkirjautuminen

![sequence diagram](https://raw.githubusercontent.com/tsalohei/bike-tracker/master/dokumentaatio/kuvat/sequenceDiagramUserLogsIn.png "Sequence diagram")
