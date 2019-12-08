package com.salohei.domain;

import com.salohei.dao.NoteDao;
import com.salohei.dao.UserDao;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Sovelluslogiikasta vastaava luokka. 
 */
public class NoteService {
    private NoteDao noteDao;
    private UserDao userDao;
    private User currentUser;
    
    public NoteService(NoteDao noteDao, UserDao userDao) {
        this.noteDao = noteDao;
        this.userDao = userDao;
    }
    
    
    /**
     * Metodi poistaa muistiinpanon nykyisen käyttäjän antamalta
     * päivämäärältä.
     * 
     * @param date Nykyisen käyttäjän antama päivämäärä
     * @return palautetaan true jos muistiinpanon poistaminen onnistui,
     * muuten palautetaan false
     */
    public boolean deleteNote(LocalDate date) {
        List<Note> list = noteDao.getAll(currentUser);
        boolean result = false;
        for (Note note : list) {
            if (note.getDate().equals(date)) {
                result = true;
            }
        }
        if (result == true) {
            this.noteDao.deleteNote(date, currentUser);
        } 
        return result;
    }

    /**
     * Metodi palauttaa nykyisen käyttäjän kokonaiskilometrit.
     * 
     * @return pyöräillyt kilometrit yhteensä, tai 0 jos ei kilometreja
     */
    
    public int kmTotal() {
        return noteDao.kmTotal(currentUser);
    }
    
    /**
     * Metodi palauttaa nykyisen käyttäjän kaikki muistiinpanot.
     * 
     * @return lista muistiinpanoista, tai tyhjä lista jos muistiinpanoja ei ole
     */
    public List<Note> getAll() {
        return noteDao.getAll(currentUser);   
    }
    
    /**
     * Metodi luo uuden muistiinpanon nykyiselle käyttäjälle
     * 
     * @param date Muistiinpanon päivämäärä
     * @param km kuinka monta kilometria muistiinpanoon liittyy
     * @param content Teksti, joka halutaan liittää osaksi muistiinpanoa
     * 
     * @return true jos muistiinpanon luominen onnistuu, false jos ei onnistu
     */
    public boolean createNote(LocalDate date, int km, String content) {
        
        try {
            Note note = noteDao.create(date, km, content, currentUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Metodin avulla käyttäjä, jolla on jo käyttäjätunnus, kirjautuu sisään
     * 
     * @param username Käyttäjänimi
     * 
     * @return true jos sisäänkirjautuminen onnistuu, false jos ei onnistu 
     */
    public boolean login(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;
        }
        currentUser = user;
        return true;
    }
    
    /**
     * Metodi kirjaa nykyisen käyttäjän ulos ohjelmasta
     */
    public void logout() {
        currentUser = null;
    }
    
    /**
     * Metodi luo uuden käyttäjän.
     * 
     * @param name Käyttäjän nimi
     * @param username Käyttäjän käyttäjänimi
     * 
     * @return true jos käyttäjän luominen onnistuu, false jos ei onnistu 
     */
    public boolean createUser(String name, String username) {
        if (userDao.findByUsername(username) != null) {
            return false;
        }
        try {
            User user = userDao.create(name, username);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Metodi palauttaa nykyisen käyttäjän.
     * 
     * @return nykyinen käyttäjä 
     */
    public User getLoggedUser() {
        return currentUser;
    }
        
}
