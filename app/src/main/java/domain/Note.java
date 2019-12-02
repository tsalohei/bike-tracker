package domain;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

/**
 * Päiväkohtaista muistiinpanoa edustava luokka
 */

public class Note {
    
    private LocalDate date;
    private int km;
    private String content;
    private User user;
    private int id;
 
    public Note(LocalDate date, int km, String content, User user, int id) {
        
        this.date = date;
        this.km = km;
        this.content = content;
        this.user = user;
        this.id = id;
    }
    
    /*
    public Note(int km, String content, User user) {
        this.km = km;
        this.content = content;
    }
    */
    public int getId() {
        return this.id;
    }
      

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public LocalDate getDate() {
        return this.date;
    }
   
    
    public void setKm(int km) {
        this.km = km;
    }
    
    public int getKm() {
        return this.km;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public User getUser() {
        return this.user;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        return "Date: " + this.date.format(formatter) + "\n" + "Kilometers: " + 
                this.km + "\n" + "Your notes: " + this.content;
    }
    
    
    /*
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
