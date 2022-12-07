package pl.edu.pw.elka.pap.z16.almostjira.controllers.Projects;

/* 1 projekt:
 project id
 project name
 tasks list
 overseer - user */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.elka.pap.z16.almostjira.controllers.Users.LoginForm;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;

@RestController
@RequestMapping("/projects")
public class ProjectsController {
    int project_id = 0;
    private final ArrayList<Hashtable<String, String>> projects_data = new ArrayList<>();
    @GetMapping("{project_id}")
    public ResponseEntity<String> get_project_by_id(@PathVariable("project_id") int project_id){
        for (final Hashtable<String, String> project: projects_data) {
            if (Objects.equals(project.get("project_id"), String.valueOf(project_id))){
                return new ResponseEntity<>(String.valueOf(project), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No such project!", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<String> get_projects(){
        return new ResponseEntity<>(String.valueOf(this.projects_data), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> add_project(@RequestBody LoginForm requestBody){
        Hashtable<String, String> user_to_add = new Hashtable<>();

        // TODO: add data validation
        user_to_add.put("user_id", String.valueOf(this.user_id));
        user_to_add.put("username", requestBody.username);
        user_to_add.put("password", requestBody.password);

        users_data.add(user_to_add);
        this.user_id++;
        return new ResponseEntity<>(String.valueOf(user_to_add), HttpStatus.CREATED);
    }
// lista słownikow reprezentujacych pojedynczy projekt
// metoda zeby odczytac dane z projektu o danym Id
// metoda zeby dodac projekt do listy (dba zeby id bylo unikalne)
// metoda zeby zmienic projekt o danym id
// metoda zeby usunac projekt  o danym id

}

