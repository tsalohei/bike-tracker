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
    public Note create(LocalDate date, int km, String content, User user) throws Exception {
        
        try (Connection conn = database.getConnection()) {  
            
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Note (date, km, content, user) VALUES (?,?,?,?)");
            stmt.setDate(1, Date.valueOf(date));
            stmt.setInt(2, km);
            stmt.setString(3, content);           
            stmt.setInt(4, user.getId()); 

            stmt.executeUpdate();

            stmt.close();
            conn.close();
            
            return findByUsernameAndDate(user, date);
            
        } catch (Throwable t) {
            return null;
        }
    
    }
    //TARKISTA TOIMIIKO
    public Note findByUsernameAndDate(User user, LocalDate date){
        String username = user.getUsername();        
        Date sqlDate = Date.valueOf(date);
        
        try (Connection conn = database.getConnection()) {
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Note, User WHERE User.username = ? AND Note.date = ?");
            stmt.setString(1, username);
            stmt.setDate(2, sqlDate);
            
            ResultSet rs = stmt.executeQuery();
            boolean hasOne = rs.next();
            if (!hasOne) {
                return null;
            }

            Note n = new Note(rs.getDate("date").toLocalDate(), rs.getInt("km"), rs.getString("content"), user, rs.getInt("id"));

            stmt.close();
            conn.close();

            return n;
            
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
 
}
