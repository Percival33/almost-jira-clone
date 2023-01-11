package pl.edu.pw.elka.pap.z16.almostjira.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pw.elka.pap.z16.almostjira.exceptions.ResourceNotFoundException;
import pl.edu.pw.elka.pap.z16.almostjira.models.*;
import pl.edu.pw.elka.pap.z16.almostjira.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;



@Service
public class UserService {
    //    TODO: https://www.baeldung.com/mongodb-return-specific-fields
    @Autowired
    UserRepository userRepository;
    User loggedUser = null;

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow((() ->
                new ResourceNotFoundException("User", "id", id)));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(String id) {
        userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));
        userRepository.deleteById(id);
    }

    public User login(String login, String password) {
        var userlist = getAllUsers();
        var listiterator = userlist.iterator();
        while (listiterator.hasNext()){
            var currentuser = listiterator.next();
            if (login.equals(currentuser.login()) && password.equals(currentuser.password()))
                loggedUser = currentuser;
                return currentuser;
        }
        throw new ResourceNotFoundException("Matching data", "", login);
    }

    public boolean checkifloggedin() {
        if (loggedUser != null)
            return true;
        return false;
    }

    public User updateUser(UserForm u, String id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "Id", id));

        Date now = new Date();
        User updatedUser = existingUser.toBuilder()
                .firstName(u.firstName())
                .lastName(u.lastName())
                .login(u.login())
                .password(u.password())
                .lastModified(now)
                .build();

        return userRepository.save(updatedUser);
    }

    public User createUser(UserForm u) {
        Date now = new Date();
        // TODO: hash password
        return userRepository.save(
                User.builder()
                        .id(String.valueOf(UUID.randomUUID()))
                        .firstName(u.firstName())
                        .lastName(u.lastName())
                        .login(u.login())
                        .password(u.password())
                        .projects(null)
                        .createdAt(now)
                        .lastModified(now)
                        .build());
    }
}
