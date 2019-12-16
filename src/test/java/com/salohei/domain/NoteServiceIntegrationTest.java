package com.salohei.domain;

import com.salohei.dao.Database;
import com.salohei.dao.NoteDao;
import com.salohei.dao.SqlNoteDao;
import com.salohei.dao.SqlUserDao;
import com.salohei.dao.UserDao;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class NoteServiceIntegrationTest {

    NoteService noteService;
    
    @Before
    public void setup() throws Exception {
        Database database = new Database("jdbc:sqlite:test-tietokanta2.db");
        database.createTables();
        
        UserDao userDao = new SqlUserDao(database); 
        NoteDao noteDao = new SqlNoteDao(database);
        NoteService noteService = new NoteService(noteDao, userDao);
        
        this.noteService = noteService;
    }    
    
    //laita samat testit kuin NoteServiceNote/UserTest-luokissa
    
    @After
    public void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get("test-tietokanta2.db"));
    }
    
    @Test    
    public void getAllreturnsEmptyListforNewUser() throws SQLException {
        loginUser();        
        assertEquals(0, noteService.getAll().size());
    }
    
    /*
    @Test    
    public void getAllreturnsEmptyListforNoCurrentUser() throws SQLException {
        assertEquals(0, noteService.getAll().size());
    }
    */
    //apumetodi
    private void loginUser() throws SQLException {
        noteService.createUser("Katariina Suuri", "kata");
        noteService.login("kata");        
    }
}
