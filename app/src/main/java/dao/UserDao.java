package dao;

import domain.User;
import java.util.List;


public interface UserDao {
    
    User create(String name, String username); 
    
    //muuta tämä findbyUsernameAndPassword
    User findByUsername(String username);
    
    /*
    List<User> getAll();
    */
}
