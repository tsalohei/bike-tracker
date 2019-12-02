package domain;

import dao.NoteDao;
import dao.UserDao;
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
    
    @Before
    public void setup() {
        noteService = new NoteService(noteDao, userDao);
    }
    
    /*
    @Test
    public void testi(){
        
        
        //when().thenReturn();
        //assertEquals(true, );
        
    }
*/

    
}
