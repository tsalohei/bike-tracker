package dao;

import domain.User;
import java.sql.*;
import java.util.List;


public class SqlUserDao implements UserDao {

    private Database database;
    
    public SqlUserDao(Database database) {
        this.database = database;
    }
  
    @Override    
    public User create(String name, String username) {
        try (Connection conn = database.getConnection()) {    
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO User (name, username) VALUES (?,?)");
            
            stmt.setString(1, name);
            stmt.setString(2, username);
            
            stmt.executeUpdate();

            stmt.close();
            conn.close();

            return findByUsername(username);
            
        } catch (Throwable t) {
            return null;
        }
        
    }

    @Override
    public User findByUsername(String username) {
        try (Connection conn = database.getConnection()) {
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE username = ?");
            
            stmt.setString(1, username);
            
            ResultSet rs = stmt.executeQuery();
            boolean hasOne = rs.next();
            if (!hasOne) {
                return null;
            }
            
            User u = new User(rs.getString("name"), rs.getString("username"), rs.getInt("id"));

            stmt.close();
            conn.close();

            return u;
            
        } catch (Throwable t) {
            return null;
        }
        
    }
    
}
