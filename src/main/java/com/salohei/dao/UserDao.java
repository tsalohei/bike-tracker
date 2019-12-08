package com.salohei.dao;

import com.salohei.domain.User;
import java.util.List;


public interface UserDao {
    
    User create(String name, String username); 
    
    User findByUsername(String username);
    
}
