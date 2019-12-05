package domain;


import dao.NoteDao;
import dao.UserDao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    
    @Test
    public void gettingKmCountForLoggedUserWorks() {
        when(noteDao.kmTotal(user)).thenReturn(12);
        
        assertEquals(12, noteService.kmTotal());
    }
    
    @Test
    public void gettinListofNotesForLoggedUserWorksWhenThereAreNotes() {
        Note note = new Note(LocalDate.now(), 22, "foo", user, 1);
        List<Note> list = new ArrayList<>();
        list.add(note);
        when(noteDao.getAll(user)).thenReturn(list);
        
        assertEquals(list, noteService.getAll());
        
    }
   
    
     @Test
    public void gettinListofNotesForLoggedUserWorksWhenThereAreNoNotes() {
        List<Note> list = new ArrayList<>();
        when(noteDao.getAll(user)).thenReturn(list);
        
        assertEquals(list, noteService.getAll());
        
    }
    
    
    
    
}
