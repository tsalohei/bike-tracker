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
    
    public SqlNoteDao(Database database) {
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
            System.err.println("Error: " + t);
            return null;
        }
    
    }

    public Note findByUsernameAndDate(User user, LocalDate date) {
        String username = user.getUsername();        
        
        try (Connection conn = database.getConnection()) {
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Note, User WHERE User.username = ? AND Note.date = ?");
            stmt.setString(1, username);
            stmt.setDate(2, Date.valueOf(date));
            
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
            System.err.println("Error: " + t);
            return null;
        }
        
    }

    @Override
    public List<Note> getAll(User user) {
        List<Note> list = new ArrayList<>();
        
        int userId = user.getId();
        
        try (Connection conn = database.getConnection()) {
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Note WHERE user = ? ORDER BY date DESC");
            stmt.setInt(1, userId);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Note n = new Note(rs.getDate("date").toLocalDate(), 
                        rs.getInt("km"), rs.getString("content"), user, 
                        rs.getInt("id"));
                list.add(n);
            }
            
            stmt.close();
            conn.close();
        } catch (Throwable t) {
            System.err.println("Error: " + t);            
        }
       
        return list;
    }

    @Override
    public int kmTotal(User user) {
        int userId = user.getId();
        int tulos = 0;
        
        try (Connection conn = database.getConnection()) {
            
            PreparedStatement stmt = conn.prepareStatement("SELECT SUM(km) FROM Note WHERE user = ?");
            stmt.setInt(1, userId);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                tulos = rs.getInt(1);
            }
            
            stmt.close();
            conn.close();
        } catch (Throwable t) {
            System.err.println("Error: " + t);            
        }
    
        return tulos;
    }

    @Override
    public void deleteNote(LocalDate date, User user) {
        int userId = user.getId();
        Date sqlDate = Date.valueOf(date);
        
        try (Connection conn = database.getConnection()) {
            
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Note WHERE user = ? AND date = ?");
            stmt.setInt(1, userId);
            stmt.setDate(2, sqlDate);
            
            stmt.executeUpdate();
   
            stmt.close();
            conn.close();
        } catch (Throwable t) {
            System.err.println("Error: " + t);            
        }
        
    }
    
    
 
}
