package pl.edu.pw.elka.pap.z16.almostjira.controllers;

//1 uzytkownik:
// user_id
// login
// mail

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Hashtable;
@RestController("/users")
public class Users {
    public ArrayList<Hashtable<String, String>> users_data = new ArrayList<Hashtable<String, String>>();
    @PutMapping("/add_user")
    void add_user(String user_id, String login, String mail){
        Hashtable<String, String> user_to_add = new Hashtable<>();
        user_to_add.put("user_id", user_id);
        user_to_add.put("login", login);
        user_to_add.put("mail", mail);
        users_data.add(user_to_add);
    }

    @GetMapping("/get_user_by_id")
    Hashtable<String, String> get_user_by_id(String user_id){
        for (final Hashtable<String, String> user: users_data) {
            if (user.get("user_id") == user_id){
              return user;
            }
        }
        return null;
    }

    @GetMapping("/get_user_by_login")
    Hashtable<String, String> get_user_by_login(String login){
        for (final Hashtable<String, String> user: users_data) {
            if (user.get("login") == login){
                return user;
            }
        }
        return null;
    }
    @PutMapping("/change_user_login")
    void change_user_login(String user_id, String new_login){
        for (final Hashtable<String, String> user: users_data) {
            if (user.get("user_id") == user_id){
                user.replace("login", new_login);
            }
        }
    }
    @PutMapping("/change_user_email")
    void change_user_mail(String user_id, String new_mail){
        for (final Hashtable<String, String> user: users_data) {
            if (user.get("user_id") == user_id){
                user.replace("mail", new_mail);
            }
        }
    }
    @DeleteMapping("/remove_user")
    void remove_user(String user_id){
        users_data.removeIf(user -> user.get("user_id") == user_id);
    }


    // lista uzytkownikow
    // getery i setery dla uzytkownikow o danym loginie
}
