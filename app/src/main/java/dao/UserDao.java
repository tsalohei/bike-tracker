package dao;

import domain.User;
import java.util.List;


public interface UserDao {
    
    User create(User user) throws Exception; 
    
    //muuta tämä findbyUsernameAndPassword
    User findByUsername(String username) throws Exception;
    
    List<User> getAll();
}
