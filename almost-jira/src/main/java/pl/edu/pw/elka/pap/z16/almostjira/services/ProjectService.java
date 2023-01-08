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

    public void deleteProject(String id) {
        projectRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Project", "id", id));
        projectRepository.deleteById(id);
    }

    public Project updateProject(ProjectForm p, String id) {
        Project existingProject = projectRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Project", "Id", id));

        Date now = new Date();
        Project updatedProject = existingProject.toBuilder()
                .overseerId((p.overseerId() == null) ? existingProject.overseerId() : p.overseerId())
                .projectName((p.projectName() == null) ? existingProject.projectName() : p.projectName())
                .tasks((p.tasks() == null) ? existingProject.tasks() : p.tasks())
                .lastModified(now)
                .build();

        return projectRepository.save(updatedProject);
    }

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

    public Project updateProjectAddTask(String newTask, String id) {
        Project existingProject = projectRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Project", "Id", id));

        Date now = new Date();


        var updatedTasks = existingProject.tasks();
        updatedTasks.add(newTask);


        Project updatedProject = existingProject.toBuilder()
                .tasks(updatedTasks)
                .lastModified(now)
                .build();

        return projectRepository.save(updatedProject);
    }

    public Project updateProjectModifyTask(int taskIndex, String modifiedTask, String id) {
        Project existingProject = projectRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Project", "Id", id));

        Date now = new Date();
        var updatedTasks = existingProject.tasks();
        updatedTasks.set(taskIndex-1, modifiedTask);

        Project updatedProject = existingProject.toBuilder()
                .tasks(updatedTasks)
                .lastModified(now)
                .build();

        return projectRepository.save(updatedProject);
    }

    public Project updateProjectRemoveTask(int taskIndex, String id) {
        Project existingProject = projectRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Project", "Id", id));

        Date now = new Date();
        var updatedTasks = existingProject.tasks();
        updatedTasks.remove(taskIndex-1);

        Project updatedProject = existingProject.toBuilder()
                .tasks(updatedTasks)
                .lastModified(now)
                .build();

        return projectRepository.save(updatedProject);
    }
}
