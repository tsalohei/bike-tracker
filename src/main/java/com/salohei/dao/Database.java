package com.salohei.dao;


import java.sql.*;

/** 
 * Luokka luo tietokannan sovellukselle ja tallentaa sen parametrina annettuun osoitteeseen.
 */ 
public class Database {

    private String databaseAddress;
    
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }
  
    /**
     * Metodi luo yhteyden tietokantaan.
     * 
     * @see java.sql.DriverManager#getConnection(String url)
     * 
     * @return yhteys tietokantaan
     * 
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
    
    /**
     * Metodi luo tietokantaan tietokantataulut.
     */
    public void createTables() {
        String userTable = "CREATE TABLE IF NOT EXISTS User (\n"
            +   "id integer PRIMARY KEY,\n"
            +   "name text NOT NULL,\n"
            +   "username text NOT NULL\n"       
            +   ");";

        String noteTable = "CREATE TABLE IF NOT EXISTS Note (\n"
            +   "id integer PRIMARY KEY,\n" 
            +   "date date NOT NULL,\n"
            +   "km integer NOT NULL,\n"
            +   "content text NOT NULL,\n"
            +   "user integer NOT NULL,\n" 
            +    "FOREIGN KEY(user) REFERENCES User(id)\n"    
            +   ");";  

        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement(); 
            stmt.execute(userTable);
            stmt.execute(noteTable);
            
        } catch (Throwable t) {
            
        }
    }
}
