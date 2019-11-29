

package dao;

import domain.Note;
import domain.User;
import java.util.List;

public interface NoteDao {
    
    Note create(Note note, User user) throws Exception;
    
    Integer getUserId(User user);
    
    List<Note> getAll();
    
    void remove(Note note);
    
}
