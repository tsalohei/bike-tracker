package dao;

import domain.User;
import java.util.List;


public interface UserDao {
    
    User create(String name, String username); 
    
    User findByUsername(String username);
    
}
