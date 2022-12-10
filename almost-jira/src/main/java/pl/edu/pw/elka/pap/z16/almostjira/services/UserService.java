package pl.edu.pw.elka.pap.z16.almostjira.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pw.elka.pap.z16.almostjira.exceptions.ResourceNotFoundException;
import pl.edu.pw.elka.pap.z16.almostjira.models.*;
import pl.edu.pw.elka.pap.z16.almostjira.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    //    TODO: https://www.baeldung.com/mongodb-return-specific-fields
    @Autowired
    UserRepository userRepository;

    public User getUserById(String id) {
        System.out.println("dupa " +  id + ' ' + UUID.fromString(id));
        System.out.println(UUID.fromString(id));
        System.out.println(id);
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

    public User createUser(UserForm u) {
        String a = String.valueOf(UUID.randomUUID());
        System.out.println("User created: " + a);
        return userRepository.save(
                User.builder()
                        .id(a)
                        .username(u.getUsername())
                        .build());
    }
}
