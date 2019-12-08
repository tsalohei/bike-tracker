package com.salohei.ui;


import com.salohei.dao.Database;
import com.salohei.dao.NoteDao;
import com.salohei.dao.SqlNoteDao; //OTA NÄMÄ KAKSI POIS
import com.salohei.dao.SqlUserDao;
import com.salohei.dao.UserDao;
import com.salohei.domain.Note;
import com.salohei.domain.NoteService;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;

public class TextUi {

    private Scanner scanner;
    private NoteService noteService;
    private Map<String, String> commands;
    
    public TextUi() throws Exception {
        this.commands = createCommands();
    }
    
    private TreeMap<String, String> createCommands() {
        TreeMap commands = new TreeMap<>();
        commands.put("x", "x: close the program"); 
        commands.put("1", "1: login");
        commands.put("2", "2: register as a new user");
        commands.put("3", "3: add a new cycling note");
        commands.put("4", "4: total kilometer count");
        commands.put("5", "5: list all cycling notes");
        commands.put("6", "6: delete note by date");
        commands.put("7", "7: logout");
        return commands;
    }
    
    public void start() {
        System.out.println("***Kilometer tracker for cycling***");
        printInstructions();
        while (true) {
            System.out.println();
            System.out.print("Command: ");
            String command = scanner.nextLine();
            if (!commands.keySet().contains(command)) {
                System.out.println("Command was not recognized");
                printInstructions();
            }
            if (command.equals("x")) {
                break;
            } else if (command.equals("1")) {
                login();
            } else if (command.equals("2")) {
                createUser();
            } else if (command.equals("3")) {
                createNote();
            } else if (command.equals("4")) {
                kmTotal();
            } else if (command.equals("5")) {
                listAllNotes();         
            } else if (command.equals("6")) {
                deleteNote();
            } else if (command.equals("7")) {
                logout();
                break;
            }
        }
    }

    private void deleteNote() {
        System.out.println("Which day's note would you like to remove?");
        
        LocalDate localDate = null;
        while (localDate == null) {
            System.out.print("Date (dd/mm/yyyy): ");
            String stringDate = scanner.nextLine();
        
            localDate = formatStringDateToLocalDate(stringDate);
        }
        
        boolean result = noteService.deleteNote(localDate);
        if (result == false) {
            System.out.println("You don't have a cycling note with this date");
        } else {
            System.out.println("The note has been deleted");
        }
    }
    
    private void kmTotal() {
        int total = noteService.kmTotal();
        System.out.println(total);
    }
    
    private void listAllNotes(){
        List<Note> notes = noteService.getAll();
        if (notes.size() == 0) {
            System.out.println("You have no cycling notes yet.");
        } else {
            for (Note n : notes){
                System.out.println(n.toString());
                System.out.println("***");
            }
        }
    }
    
    private void createNote() {        
        LocalDate localDate = null;
        while (localDate == null) {
            System.out.print("Date (dd/mm/yyyy): ");
            String stringDate = scanner.nextLine();
        
            localDate = formatStringDateToLocalDate(stringDate);
        }
        
        Integer km = null;
        while (km == null) {
            System.out.print("Kilometers: ");
            String stringKm = scanner.nextLine();
            km = validateKmInput(stringKm);
        }
        
        String content = null;
        while (content == null) {
            System.out.print("Your notes about the day: ");
            content = scanner.nextLine();
            if (content.length() <= 1 || content.length() > 200) {
                System.out.println("Invalid, note has to be within 1 and 200 characters");
                content = null;
            }
        }
        
        noteService.createNote(localDate, km, content);
        
    }
    
    
    public Integer validateKmInput(String stringKm) {
        try {
            int km = Integer.parseInt(stringKm);
            
            if (km < 1 || km > 500) {
                System.out.println("Invalid, daily kilometers need to be within 1 and 500.");
                return null;
            }
            
            return km;
        } catch (NumberFormatException e) {
            System.out.println("Invalid format for kilometers! Use numbers.");
        }
        return null;
    }
    
    
    public LocalDate formatStringDateToLocalDate(String stringDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(stringDate, formatter);
            return localDate;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format!");
        }
        
        return null;
    }
    
    private void printInstructions() {
        System.out.println("Choose one of the following commands:");
        for (Map.Entry<String, String> entry : commands.entrySet()) {
            System.out.println(entry.getValue());
        }    
    }
    
    private void logout() {
        noteService.logout();
        System.out.print("You have been logged out");      
    }
    
    private void login() {
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
    
    public static void main(String[] args) throws Exception {
        TextUi ui = new TextUi();
        ui.init();  
    }
    
    private void init() throws Exception {
        Properties properties = new Properties();
        InputStream cpResource = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        properties.load(cpResource);
        String databaseAddress = properties.getProperty("databaseAddress");
        Database db = new Database(databaseAddress);
        db.getConnection();
        db.createTables();
        UserDao userDao = new SqlUserDao(db); 
        NoteDao noteDao = new SqlNoteDao(db);
        NoteService noteService = new NoteService(noteDao, userDao);
        
        Scanner scanner = new Scanner(System.in);
                
        this.scanner = scanner;
        this.noteService = noteService;
        start();    
    }

    
}
