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

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String user_id, @RequestBody UserForm u){
        return new ResponseEntity<User>(userService.updateUser(u, user_id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String user_id){
        userService.deleteUser(user_id);

        return new ResponseEntity<>("User deleted successfully!.", HttpStatus.OK);
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
