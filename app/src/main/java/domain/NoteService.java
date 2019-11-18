package domain;

import dao.NoteDao;
import dao.UserDao;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Sovelluslogiikasta vastaava luokka
 */


public class NoteService {
    private NoteDao noteDao;
    private UserDao userDao;
    private User currentUser;
    
    public NoteService(NoteDao noteDao, UserDao userDao){
        this.noteDao = noteDao;
        this.userDao = userDao;
    }
    
    
    //NOTE-METODIT
    
    //päiväkirjamerkinnän poistaminen
    //päiväkirjamerkinnän muokkaaminen
    
    //kirjautuneen käyttäjän kaikki pyöräilymerkinnät
    public List<Note> getAll(){
        return noteDao.getAll()
            .stream()
            .filter(n->n.getUser().equals(currentUser))
            .collect(Collectors.toList());
    }
    
    
    //uusi päiväkirjamerkintä kirjautuneelle käyttäjälle
    public boolean createNote(int km, String content){
        Note note = new Note(km, content, currentUser);
        try {
            noteDao.create(note);
            return true;
        } catch (Exception e) {
            return false;
        }
        

    }
    
    //käyttäjän sisäänkirjautuminen
    //miksi java haluaa lisätä tähän throws Exception?
    //lisää tähän mukaan salasana
    public boolean login(String username) throws Exception {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;
        }
        currentUser = user;
        return true;
    }
    
    //uloskirjautuminen
    public void logout(){
        currentUser = null;
    }
    
    //kirjautunut käyttäjä
    
    public User currentUser(){
        return currentUser;
    }
     
    //uusi käyttäjä
    //miksi java halusi tähän throws exceptionin?
    public boolean createUser(String username, String name) throws Exception{
        if (userDao.findByUsername(username) != null){
            return false;
        }
        User user = new User(username, name);
        try {
            userDao.create(user);
        } catch (Exception e){
            return false;
        }
        return true;
    }
        
}
