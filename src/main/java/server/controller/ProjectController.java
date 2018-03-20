package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.model.Project;
import server.model.Resource;
import server.repository.ProjectRepository;

import javax.validation.Valid;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectRepository repository;

    @PostMapping("/projects")
    public Project createProject(@Valid @RequestBody Project project) {
        return repository.save(project);
    }

    @GetMapping("/projects")
    public @ResponseBody
    Iterable<Project> getAllProjects() {
        return this.repository.findAll();
    }

    @GetMapping("/projects/{projectId}")
    public @ResponseBody
    ResponseEntity<Project> getProject(@PathVariable(value = "projectId") Integer projectId) {
        Project project = repository.findOne(projectId);
        if (project != null) {
            return ResponseEntity.ok(project);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/projects/{projectId}/resources")
    public @ResponseBody
    ResponseEntity<Set<Resource>> getResourcesFromProject(@PathVariable(value = "projectId") Integer projectId) {
        Project project = repository.findOne(projectId);
        if (project != null) {
            return ResponseEntity.ok(project.getResources());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/projects/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable(value = "projectId") Integer projectId,
                                                 @Valid @RequestBody Project updateDetails) {
        Project beforeProject = repository.findOne(projectId);
        if (beforeProject != null) {
            beforeProject.setName(updateDetails.getName());
            beforeProject.setBudget(updateDetails.getBudget());
            beforeProject.setManager(updateDetails.getManager());
            beforeProject.setStartDate(updateDetails.getStartDate());
            beforeProject.setEndDate(updateDetails.getEndDate());
            beforeProject.setRagStatus(updateDetails.getRagStatus());
            beforeProject.setActualClosedStatusDate(updateDetails.getActualClosedStatusDate());
            beforeProject.setActualClosingStatusDate(updateDetails.getActualClosingStatusDate());
            beforeProject.setActualConfirmedStatusDate(updateDetails.getActualConfirmedStatusDate());
            beforeProject.setActualPipelineStatusDate(updateDetails.getActualPipelineStatusDate());
            beforeProject.setActualPreApprovalStatusDate(updateDetails.getActualPreApprovalStatusDate());
            beforeProject.setActualSeekFundingStatusDate(updateDetails.getActualSeekFundingStatusDate());
            beforeProject.setExpectedClosedStatusDate(updateDetails.getExpectedClosedStatusDate());
            beforeProject.setExpectedClosingStatusDate(updateDetails.getExpectedClosingStatusDate());
            beforeProject.setExpectedConfirmedStatusDate(updateDetails.getExpectedConfirmedStatusDate());
            beforeProject.setExpectedPipelineStatusDate(updateDetails.getExpectedPipelineStatusDate());
            beforeProject.setExpectedPreApprovalStatusDate(updateDetails.getExpectedPreApprovalStatusDate());
            beforeProject.setExpectedSeekFundingStatusDate(updateDetails.getExpectedSeekFundingStatusDate());
            beforeProject.setPercentageComplete(updateDetails.getPercentageComplete());
            beforeProject.setEstimatedRemainingCost(updateDetails.getEstimatedRemainingCost());
            Project updatedProject = repository.save(beforeProject);
            return ResponseEntity.ok(updatedProject);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<Project> deleteProject(@PathVariable(value = "projectId") Integer projectId) {
        Project project = repository.findOne(projectId);
        if (project != null) {
            repository.delete(project);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}