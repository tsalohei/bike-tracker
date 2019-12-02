package domain;

import dao.NoteDao;
import dao.UserDao;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Sovelluslogiikasta vastaava luokka
 */


public class NoteService {
    private NoteDao noteDao;
    private UserDao userDao;
    private User currentUser;
    
    public NoteService(NoteDao noteDao, UserDao userDao) {
        this.noteDao = noteDao;
        this.userDao = userDao;
    }
    
    
    //NOTE-METODIT
    
    //tarkista kilometrisaldo (kokonaisuudessaan)
    //päiväkirjamerkinnän poistaminen
    //päiväkirjamerkinnän muokkaaminen
    
    //kirjautuneen käyttäjän kaikki pyöräilymerkinnät
    public List<Note> getAll() {
        return noteDao.getAll(currentUser);   
    }
    
    
    //uusi päiväkirjamerkintä kirjautuneelle käyttäjälle
    public boolean createNote(LocalDate date, int km, String content) {
        
        try {
            Note note = noteDao.create(date, km, content, currentUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    //käyttäjän sisäänkirjautuminen
    
    public boolean login(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;
        }
        currentUser = user;
        return true;
    }
    
    //uloskirjautuminen
    
    public void logout() {
        currentUser = null;
    }
    
    //uusi käyttäjä
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

    //kirjautunut käyttäjä
    
    public User getLoggedUser() {
        return currentUser;
    }
        
}
