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
        this.km = km;
        this.content = content;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setDate(LocalDate date){
        this.date = date;
    }
    
    public LocalDate getDate(){
        return this.date;
    }
    
    public void setKm(int km){
        this.km = km;
    }
    
    public int getKm(){
        return this.km;
    }
    
    public void setContent(String content){
        this.content = content;
    }
    
    public String getContent(){
        return this.content;
    }
    
    public void setUser(User user){
        this.user = user;
    }
    
    public User getUser(){
        return this.user;
    }
    
    
    
    
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
