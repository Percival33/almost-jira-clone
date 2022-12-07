package pl.edu.pw.elka.pap.z16.almostjira.controllers.Projects;

//1 projekt:
// project id
// project name
// tasks (multiple, vector?)


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectsController {
// lista słownikow reprezentujacych pojedynczy projeky
// metoda zeby odczytac dane z projektu o danym Id
// metoda zeby dodac projekt do listy (dba zeby id bylo unikalne)
// metoda zeby zmienic projekt o danym id
// metoda zeby usunac projekt  o danym id

    @GetMapping("/hellop")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
    @GetMapping("/byep2")
    public String hello2(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Bye %s!", name);
    }
}

