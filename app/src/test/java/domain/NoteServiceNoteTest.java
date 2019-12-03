package domain;

import dao.NoteDao;
import dao.UserDao;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NoteServiceNoteTest {
    
    @Mock
    NoteDao noteDao;
    
    @Mock
    UserDao userDao;
    
    NoteService noteService;
    User user;
    Note note;
    
    @Before
    public void setup() {
        this.noteService = new NoteService(noteDao, userDao);
        this.user = this.userDao.create("Cynthia Cyclist", "cycy");
    }
    
    
    @Test
    public void creatingNewNoteForLoggedUserWorks() throws Exception{
        
        LocalDate date = LocalDate.now();
        Note note = new Note(date, 22, "foo", user, 1);
        
        when(noteDao.create(date, 22, "foo", user)).thenReturn(note);
        
        assertEquals(true, noteService.createNote(date, 22, "foo"));

    }
   
    
    
    
}
