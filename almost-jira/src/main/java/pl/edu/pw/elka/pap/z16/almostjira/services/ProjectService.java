package pl.edu.pw.elka.pap.z16.almostjira.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.elka.pap.z16.almostjira.exceptions.ResourceNotFoundException;
import pl.edu.pw.elka.pap.z16.almostjira.models.Project;
import pl.edu.pw.elka.pap.z16.almostjira.models.ProjectForm;
import pl.edu.pw.elka.pap.z16.almostjira.repositories.ProjectRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    public Project getProjectById(String id) {
        return projectRepository.findById(id).orElseThrow((() ->
                new ResourceNotFoundException("Project", "id", id)));
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
//
//    public void deleteUser(String id) {
//        projectRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("User", "id", id));
//        projectRepository.deleteById(id);
//    }
//
//    public User updateUser(UserForm u, String id) {
//        User existingUser = projectRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("User", "Id", id));
//
//        Date now = new Date();
//        User updatedUser = existingUser.toBuilder()
//                .firstName(u.firstName())
//                .lastName(u.lastName())
//                .password(u.password())
//                .lastModified(now)
//                .build();
//
//        return projectRepository.save(updatedUser);
//    }
//
    public Project createProject(ProjectForm p) {
        Date now = new Date();
        return projectRepository.save(
                Project.builder()
                        .id(String.valueOf(UUID.randomUUID()))
                        .overseerId(p.overseerId())
                        .projectName(p.projectName())
                        .createdAt(now)
                        .lastModified(now)
                        .tasks(null)
                        .build());
    }
}
