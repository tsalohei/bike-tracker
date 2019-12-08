

package com.salohei.dao;

import com.salohei.domain.Note;
import com.salohei.domain.User;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Rajapinta vastaa käyttäjän muistiinpanojen käsittelystä ja tietojen
 * tallentamisesta. 
 */
public interface NoteDao {
    
    /**
     * Metodi luo muistiinpanon, joka vastaa parametreina saatuja tietoja.
     * 
     * @param date Muistiinpanon päivämäärä
     * @param km Kuinka monta kilometria muistiinpanoon liittyy
     * @param content Teksti, joka halutaan liittää osaksi muistiinpanoa
     * @param user Käyttäjä, johon muistiinpano liittyy
     * 
     * @return Luotu muistiinpano, tai null jos muistiinpanon luominen ei 
     * onnistu
     * 
     * @throws Exception 
     */
    Note create(LocalDate date, int km, String content, User user) throws Exception;
    
    /**
     * Metodi palauttaa kaikki käyttäjään liittyvät muistiinpanot.
     * 
     * @param user Käyttäjä, johon muistiinpanot liittyvät
     * @return lista muistiinpanoja (lista voi olla myös tyhjä)
     */
    List<Note> getAll(User user);
    
    /**
     * Metodi palauttaa luvun, joka kertoo kuinka paljon käyttäjä
     * on yhteensä pyöräillyt.
     * 
     * @param user Käyttäjä
     * @return pyöräillyt kilometrit yhteensä
     */
    int kmTotal(User user);
    
    /**
     * Metodi poistaa muistiinpanon tietyltä päivämäärältä.
     * 
     * @param date Käyttäjän antama päivämäärä
     * @param user Käyttäjä
     */
    void deleteNote(LocalDate date, User user);
    
}
