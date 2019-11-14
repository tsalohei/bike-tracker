package domain;

import java.util.Date;

/**
 * Class that represents a note in the application
 */

public class Note {
    
    private int id;
    private Date date;
    private int km;
    private String content;
    private User user;
 
    public Note(int id, Date date, int km, String content, User user){
        this.id = id;
        this.date = date;
        this.km = km;
        this.content = content;
        this.user = user;
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
