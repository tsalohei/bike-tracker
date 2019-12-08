package com.salohei.dao;

import com.salohei.domain.User;
import java.sql.*;

/**
 * Luokka vastaa käyttäjän luomisesta ja käyttäjän tietojen etsimisestä 
 * SQL-tietokannasta.
 */
public class SqlUserDao implements UserDao {

    private Database database;
    
    public SqlUserDao(Database database) {
        this.database = database;
    }
  
     /**
     * Metodi luo uuden käyttäjän.
     * 
     * @param name Käyttäjän nimi
     * @param username Käyttäjän käyttäjänimi
     * 
     * @return Luotu käyttäjä, tai null jos käyttäjän luominen ei onnistu
     */
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

    /**
     * Metodi etsii annettua käyttäjänimeä vastaavan käyttäjän tietokannasta.
     * 
     * @param username Käyttäjänimi
     * 
     * @return Käyttäjä, tai null jos käyttäjän hakeminen ei onnistu 
     */
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
