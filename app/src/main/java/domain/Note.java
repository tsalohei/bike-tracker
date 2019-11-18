package domain;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

/**
 * Päiväkohtaista muistiinpanoa edustava luokka
 */

public class Note {
    
    private int id;
    private LocalDate date;
    private int km;
    private String content;
    private User user;
 
    public Note(int id, LocalDate date, int km, String content, User user){
        this.id = id;
        this.date = date;
        this.km = km;
        this.content = content;
        this.user = user;
    }
    
    public Note(int km, String content, User user){
        this.date = LocalDate.now();
        this.km = km;
        this.content = content;
    }
    
    
    public User getUser(){
        return this.user;
    }
    //getterit ja setterit
    
    /* equals. Mihin tarvitaan?
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Note)) {
            return false;
        }
        Note other = (Note) obj;
        return id == other.id;
    }
    */
}
