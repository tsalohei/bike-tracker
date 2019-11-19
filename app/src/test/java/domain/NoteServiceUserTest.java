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
public class NoteServiceUserTest {
    
    @Mock
    NoteDao noteDao;
    
    @Mock
    UserDao userDao;
    
    NoteService noteService;
    
    @Before
    public void setup() {
        noteService = new NoteService(noteDao, userDao);
    }
    
    //logout
    
    @Test
    public void creatingNewUserWithNewUsernameWorks(){
        
        when(userDao.findByUsername("username")).thenReturn(null);
        
        assertEquals(true, noteService.createUser("name", "username"));
        
    }
    
    
    @Test
    public void creatingNewUserWithTakenUsernameReturnsFalse(){  
        User user = new User("name", "username");
        when(userDao.findByUsername("username")).thenReturn(user);
        
        noteService.createUser("name", "username");
        assertEquals(false, noteService.createUser("name", "username"));
    }
    
    /*
    @Test
    public void creatingNewUserButDatabaseThrowsException(){
        
    }
    */
    
    @Test
    public void loggingOutWorks(){
        
        User user = new User("name", "username");
        when(userDao.findByUsername("username")).thenReturn(user);
        
        noteService.login("username");
        noteService.logout();
        
        assertEquals(noteService.getLoggedUser(), null);
    }
    
    @Test
    public void loginWithExistingUsernameWorks() {
        
        User user = new User("name", "username");
        when(userDao.findByUsername("username")).thenReturn(user);
        
        assertTrue(noteService.login("username"));        
        assertEquals(user, noteService.getLoggedUser());
        
    }
    
}
