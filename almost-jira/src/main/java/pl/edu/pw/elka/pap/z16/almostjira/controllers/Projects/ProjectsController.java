package pl.edu.pw.elka.pap.z16.almostjira.controllers.Projects;

/* 1 projekt:
 project id
 project name
 tasks list
 overseer - user_id */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;

@RestController
@RequestMapping("/projects")
public class ProjectsController {
    int project_id = 0;
    private final ArrayList<Hashtable<String, Object>> projects_data = new ArrayList<>();

    @PostMapping("add_project")
    public ResponseEntity<String> add_project(@RequestBody ProjectForm requestBody){
        Hashtable<String, Object> project_to_add = new Hashtable<>();

        // TODO: add data validation
        project_to_add.put("project_id", String.valueOf(this.project_id));
        project_to_add.put("project_name", requestBody.project_name);
        project_to_add.put("tasks", requestBody.project_tasks);
        project_to_add.put("overseer", requestBody.overseer_id);

        projects_data.add(project_to_add);
        this.project_id++;
        return new ResponseEntity<>(String.valueOf(project_to_add), HttpStatus.CREATED);
    }

    @PutMapping("change_name_of_project_with_id")
    public ResponseEntity<String> change_project_name(@PathVariable("project_id") int project_id, @RequestParam String new_project_name){

        for (final Hashtable<String, Object> project: projects_data) {
            if (Objects.equals(project.get("project_id"), String.valueOf(project_id))){
                project.replace("project_name", new_project_name);
                return new ResponseEntity<>(String.valueOf(project), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No such project!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{user_id}")
    public ResponseEntity<String> remove_user(@PathVariable("user_id") int user_id){
        for (final Hashtable<String, String> user: users_data) {
            if (Objects.equals(user.get("user_id"), String.valueOf(user_id))){
                users_data.remove(user);
                return new ResponseEntity<>("User deleted!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No such project!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("get_project_with_id")
    public ResponseEntity<String> get_project_by_id(@PathVariable("project_id") int project_id){
        for (final Hashtable<String, Object> project: projects_data) {
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
// lista słownikow reprezentujacych pojedynczy projekt
// metoda zeby odczytac dane z projektu o danym Id
// metoda zeby dodac projekt do listy (dba zeby id bylo unikalne)
// metoda zeby zmienic projekt o danym id
// metoda zeby usunac projekt  o danym id
}

