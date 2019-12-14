package com.salohei.dao;

import com.salohei.domain.Note;
import com.salohei.domain.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Luokka...
 */
public class SqlNoteDaoTest {
    
    Database database;
    NoteDao noteDao;
    UserDao userDao;
    User user;
    
    @Before
    public void setup() throws Exception {
        database = new Database("jdbc:sqlite:test-tietokanta.db");
        Connection connection = database.getConnection();
        database.createTables();
        
        this.userDao = new SqlUserDao(database);
        this.user = this.userDao.create("Cynthia Cyclist", "cycy");
        this.noteDao = new SqlNoteDao(database);
        
        connection.close();
    }
    
    @After
    public void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get("test-tietokanta.db"));
    }
    
    @Test
    public void noteIsCreatedWithoutError() throws Exception {
        Note result = this.noteDao.create(LocalDate.now(), 1, "foo", user);        
        assertNotNull(result);
    }
    
    @Test
    public void notesAreReadCorrectlyFromDatabase() throws Exception {
        this.noteDao.create(LocalDate.now(), 1, "foo", user);
        
        List<Note> list = noteDao.getAll(this.user);
        assertEquals(1, list.size());
    }
    
    @Test
    public void totalKmCountIsReadCorrectlyFromDatabaseWhenThereAreNotes() throws Exception {
        this.noteDao.create(LocalDate.now(), 12, "foo", user);
        
        int result = this.noteDao.kmTotal(this.user);
        assertEquals(12, result);
    }
    
    @Test
    public void totalKmCountIsReadCorrectlyFromDatabaseWhenThereAreNoNotes() throws Exception {
        int result = this.noteDao.kmTotal(this.user);
        assertEquals(0, result);
    }
    
    
    @Test
    public void noteIsDeletedCorrectlyWhenTheNoteExists() throws Exception {
        LocalDate date = LocalDate.now();
        this.noteDao.create(date, 1, "foo", this.user);  
        boolean result = this.noteDao.deleteNote(date, this.user);
        
        assertTrue(result);
    }
      
}
