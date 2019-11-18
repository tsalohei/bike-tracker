package ui;

import dao.FileUserDao;
import dao.NoteDao;
import domain.NoteService;
import java.io.FileInputStream;
import java.util.Properties;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NoteUi extends Application {   
    private NoteService noteService;
    
    //3 scene-oliota eli 3 erillistä näkymää. Yksi näkymä kerrallaan sijoitettuna stageen.
    private Scene noteScene;
    private Scene newUserScene;
    private Scene loginScene;
    
    //esimerkissä: throws Exception, ei try-catch
    @Override
    public void init() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("config.properties"));
            //tiedostoon tallennus
            String userFile = properties.getProperty("userFile");
            String noteFile = properties.getProperty("noteFile");
            
            FileUserDao userDao = new FileUserDao(userFile);
            FileNoteDao noteDao = new FileNoteDao();
            this.noteService = new NoteService(noteDao, userDao);
           
            
            //databaseen tallennus
            //String databaseAddress = properties.getProperty("databaseAddress");
        } catch (Throwable t) {
            
        }
        
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    
    }
    
    public static void main(String[] args) {
        launch(args);
        
        
        
    }
}
