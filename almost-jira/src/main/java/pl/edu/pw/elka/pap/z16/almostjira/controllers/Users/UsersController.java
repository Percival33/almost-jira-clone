package pl.edu.pw.elka.pap.z16.almostjira.controllers.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.elka.pap.z16.almostjira.models.User;
import pl.edu.pw.elka.pap.z16.almostjira.models.UserForm;
import pl.edu.pw.elka.pap.z16.almostjira.services.UserService;


@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserService userService;


    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody UserForm newUser){
        return new ResponseEntity<>(userService.createUser(newUser), HttpStatus.CREATED);
    }

//    @PutMapping("{id}")
//    public ResponseEntity<String> changeUsername(@PathVariable("id") String user_id, @RequestParam String new_username){
//
////        for (final Hashtable<String, String> user: users_data) {
////            if (Objects.equals(user.get("user_id"), String.valueOf(user_id))){
////                user.replace("username", new_username);
////                return new ResponseEntity<>(String.valueOf(user), HttpStatus.OK);
////            }
////        }
////        return new ResponseEntity<>("No such user!", HttpStatus.NOT_FOUND);
//        return null;
//    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String user_id){
        userService.deleteUser(user_id);

        return new ResponseEntity<>("Employee deleted successfully!.", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String user_id){
        return new ResponseEntity<>(userService.getUserById(user_id), HttpStatus.OK);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    // lista uzytkownikow
    // getery i setery dla uzytkownikow o danym loginie
}
