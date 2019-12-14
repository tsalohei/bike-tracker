package com.salohei.dao;

import com.salohei.domain.Note;
import com.salohei.domain.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Luokka vastaa käyttäjän muistiinpanojen käsittelystä ja tietojen 
 * tallentamisesta SQL-tietokantaan.
 */
public class SqlNoteDao implements NoteDao {
    
    private Database database;
    
    /**
     * Konstruktori.
     * @param database tietokanta 
     */
    public SqlNoteDao(Database database) {
        this.database = database;
    }

    /**
     * Metodi luo muistiinpanon, joka vastaa parametreina saatuja tietoja.
     * 
     * @param date Muistiinpanon päivämäärä
     * @param km Kuinka monta kilometria muistiinpanoon liittyy
     * @param content Teksti, joka halutaan liittää osaksi muistiinpanoa
     * @param user Käyttäjä, johon muistiinpano liittyy
     * 
     * @return Luotu muistiinpano, tai null jos luominen ei onnistu
     * 
     * @throws Exception 
     */
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

    /**
     * Metodi hakee tietokannasta tietokannan luoman tunnisteen (id) 
     * muistiinpanolle ja palauttaa näitä tietoja vastaavan muistiinpanon.
     * 
     * @param user Käyttäjä, joka on luonut muistiinpanon
     * @param date Päivämäärä, jolle muistiinpano on luotu
     * 
     * @return Luotu muistiinpano, tai null jos muistiinpanon luominen
     * ei onnistu
     */
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
            return null;
        }
        
    }
    
    /**
     * Metodi palauttaa kaikki käyttäjään liittyvät muistiinpanot.
     * 
     * @param user Käyttäjä, johon muistiinpanot liittyvät
     * 
     * @return lista muistiinpanoista, tai tyhjä lista jos muistiinpanoja ei ole
     */
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
            
        }
       
        return list;
    }

    /**
    * Metodi palauttaa käyttäjän kokonaiskilometrit.
    * 
    * @param user Käyttäjä
    * 
    * @return pyöräillyt kilometrit yhteensä, tai 0 jos ei kilometreja
    */
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
                       
        }
    
        return tulos;
    }

    /**
     * Metodi poistaa muistiinpanon pyydetyltä päivämäärältä.
     * 
     * @param date Käyttäjän antama päivämäärä
     * @param user Käyttäjä
     */
    @Override
    public boolean deleteNote(LocalDate date, User user) {
        int userId = user.getId();
       
        Date sqlDate = Date.valueOf(date);
        
        try (Connection conn = database.getConnection()) {
            
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Note WHERE user = ? AND date = ?");
            stmt.setInt(1, userId);
            stmt.setDate(2, sqlDate);
            
            stmt.executeUpdate();
   
            stmt.close();
            conn.close();
            return true;
        } catch (Throwable t) {
            return false;         
        }   
    }
    
}
