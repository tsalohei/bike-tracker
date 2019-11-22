package domain;

/**
 * Käyttäjää edustava luokka
 */


public class User {
    private String name;
    private String username;
    //private String password;

    public User(String name, String username) {
        this.name = name;
        this.username = username;
        //this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }    
    
    /*
    public String getPassword() {
        return password;
    }
    */

    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        
        User other = (User) obj;
        return username.equals(other.username);
    }
    
}
