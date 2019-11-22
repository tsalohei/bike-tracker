package ui;


import dao.Database;
import dao.NoteDao;
import dao.SqlNoteDao;
import dao.SqlUserDao;
import dao.UserDao;
import domain.NoteService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;

public class TextUi {

    private Scanner scanner;
    private NoteService noteService;
    private Map<String, String> commands;
    
    public TextUi(Scanner scanner, NoteService noteService) throws Exception {
        this.scanner = scanner;
        this.noteService = noteService;
        this.commands = createCommands();
    }
    
    private TreeMap<String, String> createCommands(){
        TreeMap commands = new TreeMap<>();
        commands.put("x", "x: close the program"); 
        commands.put("1", "1: login");
        commands.put("2", "2: register as a new user");
        commands.put("3", "3: logout");
        return commands;
    }
    
    public void start(){
        System.out.println("***Kilometer tracker for cycling***");
        printInstructions();
        while(true){
            System.out.println();
            System.out.print("Command: ");
            String command = scanner.nextLine();
            if (!commands.keySet().contains(command)){
                System.out.println("Command was not recognized");
                printInstructions();
            }
            
            if (command.equals("x")){
                break;
            } else if (command.equals("1")){
                login();
            } else if (command.equals("2")){
                createUser();
            } else if (command.equals("3")){
                logout();
                break;
            }
        }
    }

    private void printInstructions() {
        System.out.println("Choose one of the following commands:");
        for (Map.Entry<String, String> entry : commands.entrySet()){
            System.out.println(entry.getValue());
        }    
    }
    
    private void logout(){
        noteService.logout();
        System.out.print("You have been logged out");      
    }
    
    private void login(){
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        noteService.login(username);
    }
    
    private void createUser() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        noteService.createUser(name, username);
    
    }
    
    public static void main(String[] args) throws Exception{
        
        Properties properties = new Properties();        
        properties.load(new FileInputStream("config.properties"));
        String databaseAddress = properties.getProperty("databaseAddress");
        Database db = new Database(databaseAddress);
        db.getConnection();
        db.createTables();
        UserDao userDao = new SqlUserDao(db); 
        NoteDao noteDao = new SqlNoteDao();
        NoteService noteService= new NoteService(noteDao, userDao);
        
        Scanner scanner = new Scanner(System.in);
        
        TextUi textUi = new TextUi(scanner, noteService);
        
        textUi.start();
    
    }

    
}
