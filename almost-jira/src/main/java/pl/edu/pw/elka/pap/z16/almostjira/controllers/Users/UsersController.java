package pl.edu.pw.elka.pap.z16.almostjira.controllers.Users;

//1 uzytkownik:
// user_id
// username
// haslo

//!!!TODO ADD CLASS USER

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UsersController {
    private int user_id = 0;
    private final ArrayList<Hashtable<String, String>> users_data = new ArrayList<>();
    @GetMapping("{user_id}")
    public ResponseEntity<String> get_user_by_id(@PathVariable("user_id") int user_id){
        for (final Hashtable<String, String> user: users_data) {
            if (Objects.equals(user.get("user_id"), String.valueOf(user_id))){
              return new ResponseEntity<>(String.valueOf(user), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No such user!", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<String> get_users(){
        return new ResponseEntity<>(String.valueOf(this.users_data), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> add_user(@RequestBody LoginForm requestBody){
        Hashtable<String, String> user_to_add = new Hashtable<>();

        // TODO: add data validation
        user_to_add.put("user_id", String.valueOf(this.user_id));
        user_to_add.put("username", requestBody.username);
        user_to_add.put("password", requestBody.password);

        users_data.add(user_to_add);
        this.user_id++;
        return new ResponseEntity<>(String.valueOf(user_to_add), HttpStatus.CREATED);
    }

    @PutMapping("{user_id}")
    public ResponseEntity<String> change_username(@PathVariable("user_id") int user_id, @RequestParam String new_username){

        for (final Hashtable<String, String> user: users_data) {
            if (Objects.equals(user.get("user_id"), String.valueOf(user_id))){
                user.replace("username", new_username);
                return new ResponseEntity<>(String.valueOf(user), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No such user exist!", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("{user_id}")
    public ResponseEntity<String> remove_user(@PathVariable("user_id") int user_id){
        for (final Hashtable<String, String> user: users_data) {
            if (Objects.equals(user.get("user_id"), String.valueOf(user_id))){
                users_data.remove(user);
                return new ResponseEntity<>("User deleted!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No such user exist!", HttpStatus.NOT_FOUND);
    }


    // lista uzytkownikow
    // getery i setery dla uzytkownikow o danym loginie
}
