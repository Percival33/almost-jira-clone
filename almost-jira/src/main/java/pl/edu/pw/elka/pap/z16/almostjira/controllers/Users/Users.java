package pl.edu.pw.elka.pap.z16.almostjira.controllers.Users;

//1 uzytkownik:
// user_id
// login
// mail

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;

@RestController("/users")
public class Users {
    private int user_id = 0;
    private final ArrayList<Hashtable<String, String>> users_data = new ArrayList<>();
    @PostMapping("/add_user")
    public void add_user(@RequestBody LoginForm requestBody){
        Hashtable<String, String> user_to_add = new Hashtable<>();
        user_to_add.put("user_id", String.valueOf(this.user_id));
        user_to_add.put("login", requestBody.username);
        user_to_add.put("mail", requestBody.password);

        users_data.add(user_to_add);
        this.user_id++;
        // TODO: return http status code OK or error
    }

    @GetMapping("/get_user_by_id")
    public ResponseEntity<Hashtable<String, String>> get_user_by_id(String user_id){
        for (final Hashtable<String, String> user: users_data) {
            if (Objects.equals(user.get("user_id"), user_id)){
              return ResponseEntity.ok(user);
            }
        }
        return null;
    }

    @GetMapping("/get_user_by_login")
    Hashtable<String, String> get_user_by_login(String login){
        for (final Hashtable<String, String> user: users_data) {
            if (Objects.equals(user.get("login"), login)){
                return user;
            }
        }
        return null;
    }
    @PutMapping("/change_user_login")
    void change_user_login(String user_id, String new_login){
        for (final Hashtable<String, String> user: users_data) {
            if (Objects.equals(user.get("user_id"), user_id)){
                user.replace("login", new_login);
            }
        }
    }
    @PutMapping("/change_user_email")
    void change_user_mail(String user_id, String new_mail){
        for (final Hashtable<String, String> user: users_data) {
            if (Objects.equals(user.get("user_id"), user_id)){
                user.replace("mail", new_mail);
            }
        }
    }
    @DeleteMapping("/remove_user")
    void remove_user(String user_id){
        users_data.removeIf(user -> Objects.equals(user.get("user_id"), user_id));
    }


    // lista uzytkownikow
    // getery i setery dla uzytkownikow o danym loginie
}
