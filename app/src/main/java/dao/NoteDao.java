

package dao;

import domain.Note;
import domain.User;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface NoteDao {
    
    Note create(LocalDate date, int km, String content, User user) throws Exception;
    
    List<Note> getAll(User user);
    
    int kmTotal(User user);
    
    void remove(Note note);
    
}
