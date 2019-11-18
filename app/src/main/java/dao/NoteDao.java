

package dao;

import domain.Note;
import java.util.List;

public interface NoteDao {
    
    Note create(Note note) throws Exception;
    
    List<Note> getAll();
    
    void remove(Note note);
    
}
