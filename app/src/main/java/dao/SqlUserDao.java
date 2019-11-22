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
    public User create(User user) {
        try (Connection conn = database.getConnection()) {    
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO User (name, username) VALUES (?,?)");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getUsername());
            //stmt.setString(3, user.getPassword());

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            return user;
            
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
            
            User u = new User(rs.getString("name"), rs.getString("username"));

            stmt.close();
            conn.close();

            return u;
            
        } catch (Throwable t) {
            return null;
        }
        
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
