package pl.edu.pw.elka.pap.z16.almostjira.controllers.Projects;

/* 1 projekt:
 project id
 project name
 tasks list
 overseer - user_id */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.elka.pap.z16.almostjira.models.Project;
import pl.edu.pw.elka.pap.z16.almostjira.models.UserForm;
import pl.edu.pw.elka.pap.z16.almostjira.services.ProjectService;
import pl.edu.pw.elka.pap.z16.almostjira.models.ProjectForm;
import pl.edu.pw.elka.pap.z16.almostjira.services.UserService;
import pl.edu.pw.elka.pap.z16.almostjira.utils.ResponseHandler;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProjectsController {
    @Autowired
    ProjectService projectService;

    private final ArrayList<Hashtable<String, Object>> projects_data = new ArrayList<>();

//    @PostMapping("add_project")
//    public ResponseEntity<String> add_project(@RequestBody ProjectForm requestBody){
//        Hashtable<String, Object> project_to_add = new Hashtable<>();
//
//        // TODO: add data validation
//        project_to_add.put("project_id", String.valueOf(this.project_id));
//        project_to_add.put("project_name", requestBody.project_name);
//        project_to_add.put("tasks", requestBody.project_tasks);
//        project_to_add.put("overseer_id", requestBody.overseer_id);
//
//        projects_data.add(project_to_add);
//        this.project_id++;
//        return new ResponseEntity<>(String.valueOf(project_to_add), HttpStatus.CREATED);
//    }

    @PostMapping()
    public ResponseEntity<Object> addProject(@RequestBody ProjectForm newProject){
        try {
            return ResponseHandler.generateResponse("success", HttpStatus.CREATED, projectService.createProject(newProject));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @PostMapping("/tasks/{id_for_task}")
    public ResponseEntity<Object> addTaskToProject(@PathVariable("id_for_task") String projectId, String newTask){
        try {
            var modifiedList = projectService.getTasks(projectId);
            if (modifiedList == null)
                modifiedList = new ArrayList<String>();
            modifiedList.add(newTask);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, projectService.updateProjectUpdateTasks(modifiedList, projectId));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @PutMapping("/tasks/{id_for_task}")
    public ResponseEntity<Object> modifyTaskInProject(@PathVariable("id_for_task") String project_id, int taskIndex, String modifiedTask){
        try {

            var modifiedList = projectService.getTasks(project_id);
            modifiedList.set(taskIndex-1, modifiedTask);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, projectService.updateProjectUpdateTasks(modifiedList, project_id));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @DeleteMapping("/tasks/{id_for_task}")
    public ResponseEntity<Object> removeTaskFromProject(@PathVariable("id_for_task") String project_id, int taskIndex){
        try {
            var modifiedList = projectService.getTasks(project_id);
            modifiedList.remove(taskIndex-1);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, projectService.updateProjectUpdateTasks(modifiedList, project_id));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProject(@PathVariable("id") String project_id){
        try {
            projectService.deleteProject(project_id);
            return ResponseHandler.generateResponse("success", HttpStatus.NO_CONTENT, "Project deleted successfully!");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

//    @GetMapping("get_project_with_id")
//    public ResponseEntity<String> get_project_by_id(@PathVariable("id") int project_id){
//        projectgetProjectById
//        for (final Hashtable<String, Object> project: projects_data) {
//            if (Objects.equals(project.get("id"), String.valueOf(project_id))){
//                return new ResponseEntity<>(String.valueOf(project), HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>("No such project!", HttpStatus.NOT_FOUND);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProjectById(@PathVariable("id") String project_id){
        try {
            return ResponseHandler.generateResponse("success", HttpStatus.OK, projectService.getProjectById(project_id));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getProjects(){
        try {
            return ResponseHandler.generateResponse("success", HttpStatus.OK, projectService.getAllProjects());
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProject(@PathVariable("id") String project_id, @RequestBody ProjectForm p){
        try {
            return ResponseHandler.generateResponse("success", HttpStatus.OK, projectService.updateProject(p, project_id));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }
// lista słownikow reprezentujacych pojedynczy projekt
// metoda zeby odczytac dane z projektu o danym Id
// metoda zeby dodac projekt do listy (dba zeby id bylo unikalne)
// metoda zeby zmienic projekt o danym id
// metoda zeby usunac projekt  o danym id
}

