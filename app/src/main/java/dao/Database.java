package dao;


import java.sql.*;

public class Database {

    private String databaseAddress;
    
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }
    
    //avataan tietokantayhteys olemassaolevaan tietokantaan
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
    
    //luodaan tietokantaan tietokantataulut (ensimmäisellä kerralla)
    public void createTables(){
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
        +   "user integer NOT NULL,\n" //USER
        +    "FOREIGN KEY(user) REFERENCES User(id)\n"    
        +   ");";  
      
        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement(); 
            stmt.execute(userTable);
            stmt.execute(noteTable);
            
        } catch (Throwable t) {
            System.out.println("Error: " + t);
        }
    }
}
