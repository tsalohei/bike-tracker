package com.salohei.dao;

import com.salohei.domain.User;
import java.util.List;

/**
 * Rajapinta vastaa käyttäjän luomisesta ja käyttäjän tietojen etsimisestä.
 */
public interface UserDao {
    
    /**
     * Metodi luo käyttäjän, joka vastaa parametreina saatuja tietoja.
     * 
     * @param name Käyttäjän nimi
     * @param username Käyttäjän käyttäjänimi
     * 
     * @return Luotu käyttäjä, tai null jos käyttäjän luominen ei onnistu 
     */
    User create(String name, String username); 
    
    /**
     * Metodi etsii annettua käyttäjänimeä vastaavan käyttäjän.
     * 
     * @param username Käyttäjänimi
     * 
     * @return Käyttäjä, tai null jos käyttäjän hakeminen ei onnistu
     */
    User findByUsername(String username);
    
}
