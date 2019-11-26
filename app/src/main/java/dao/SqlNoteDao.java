package dao;

import domain.Note;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;


public class SqlNoteDao implements NoteDao {
    
    private Database database;
    
    public SqlNoteDao(Database database){
        this.database = database;
    }

    @Override
    public Note create(Note note) throws Exception {
        try (Connection conn = database.getConnection()) {    
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Note (date, km, content, user) VALUES (?,?,?,?)");
            //stmt.setDate(1, Note.getDate());
            //stmt.setString(2, Note.getJotain());
            //stmt.setString(2, Note.getJotain());
            //stmt.setString(2, Note.getJotain());
            //stmt.setString(2, Note.getJotain());

            //stmt.executeUpdate();

            //stmt.close();
            conn.close();

            return note;
            
        } catch (Throwable t) {
            return null;
        }
    
    }

    @Override
    public List<Note> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Note note) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
