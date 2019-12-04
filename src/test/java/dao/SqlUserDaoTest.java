
package dao;

import domain.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class SqlUserDaoTest {
    
    Database database;
    UserDao dao; 
    
    @Before
    public void setup() throws Exception {
        database = new Database("jdbc:sqlite:test-tietokanta.db");
        Connection connection = database.getConnection();
        database.createTables();
        
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User (name, username) VALUES (?,?)");
            stmt.setString(1, "Cynthia Cyclist");
            stmt.setString(2, "cycy");

            stmt.executeUpdate();

            stmt.close();
            connection.close();   
            
            this.dao = new SqlUserDao(database);
    }
    
    @After
    public void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get("test-tietokanta.db"));
    }
    
    @Test
    public void existingUserIsFound(){
        User user = dao.findByUsername("cycy");
        assertEquals("Cynthia Cyclist", user.getName());
        assertEquals("cycy", user.getUsername());   
    }
    
    @Test
    public void nonExistingUserIsFound(){
        User user = dao.findByUsername("batman");
        assertEquals(null, user);
    }
    
    @Test 
    public void newUserIsFound() {
        dao.create("Jill Homer", "jill");
        
        User user = dao.findByUsername("jill");
        
        assertEquals("Jill Homer", user.getName());
        assertEquals("jill", user.getUsername());
    }
    
}
