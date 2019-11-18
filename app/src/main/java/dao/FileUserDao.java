
package dao;

import domain.User;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author taru
 */
public class FileUserDao implements UserDao{
        private List<User> users;
        private String file;
        
    public FileUserDao(String file) throws Exception {
        this.users = new ArrayList<>();
        this.file = file;
        try { 
            Scanner reader = new Scanner(new File(file)); 
            while (reader.hasNextLine()){
                String[] nameAndUsername = reader.nextLine().split(";");
                User u = new User(nameAndUsername[0], nameAndUsername[1]);
                users.add(u);
            }
        } catch (Exception e){
            FileWriter writer = new FileWriter(new File(file));
            
        }
    }

    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(this.file))) {
            for (User u : users){
                writer.write(u.getUsername() + ";" + u.getName() + "\n");
            }
        }
        
    }

    @Override
    public User findByUsername(String username) throws Exception {
        return users.stream()
            .filter(u->u.getUsername()
            .equals(username))
            .findFirst()
            .orElse(null);
    }

    @Override
    public User create(User user) throws Exception {
        users.add(user);
        save();
        return user;
    }

    @Override
    public List<User> getAll() {
        return users;
    }
    
}
