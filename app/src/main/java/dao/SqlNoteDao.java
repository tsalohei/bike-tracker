package dao;

import domain.Note;
import domain.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class SqlNoteDao implements NoteDao {
    
    private Database database;
    
    public SqlNoteDao(Database database){
        this.database = database;
    }

    @Override
    public Note create(Note note, User user) throws Exception {
        
        try (Connection conn = database.getConnection()) {  
            
            int id = getUserId(user);
            
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Note (date, km, content, user) VALUES (?,?,?,?)");
            stmt.setDate(1, Date.valueOf(note.getDate()));
            stmt.setInt(2, note.getKm());
            stmt.setString(3, note.getContent());
            stmt.setInt(4, id); 

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            return note;
            
        } catch (Throwable t) {
            return null;
        }
    
    }

    @Override
    public List<Note> getAll() {
        List<Note> list = new ArrayList<>();
        
        /*
        userid = getUserId()
        SELECT * FROM Note WHERE user = ?
        */
        
        return list;
    }

    @Override
    public void remove(Note note) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getUserId(User user) {
        try (Connection conn = database.getConnection()) {  
            String username = user.getUsername();
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE username = ?");
            
            stmt.setString(1, username);
            
            ResultSet rs = stmt.executeQuery();
            
            boolean hasOne = rs.next();
            if (!hasOne) {
                return null;
            }
            
            int id = rs.getInt("id");
            
            stmt.close();
            conn.close();

            return id;
            
        } catch (Throwable t) {
            return null;
        }
    }
   
}
