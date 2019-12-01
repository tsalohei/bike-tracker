package domain;

/**
 * Käyttäjää edustava luokka
 */


public class User {
    private String name;
    private String username;
    private int id;
    //private String password;

    public User(String name, String username, int id) {
        this.name = name;
        this.username = username;
        this.id = id;
        //this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }    
    
    public int getId(){
        return this.id;
    }
    /*
    public String getPassword() {
        return password;
    }
    */

    
    //tarvitaanko tätä?
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        
        User other = (User) obj;
        return username.equals(other.username);
    }
    
}
