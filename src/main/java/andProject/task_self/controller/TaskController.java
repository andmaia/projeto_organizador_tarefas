package andProject.task_self.controller;

import andProject.task_self.dto.taskDTO.CreateTaskDTO;
import andProject.task_self.dto.taskDTO.GetTaskDTO;
import andProject.task_self.dto.taskDTO.UpdateTaskDTO;
import andProject.task_self.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.PublicKey;

@RestController
@RequestMapping("api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    @Transactional
    public ResponseEntity<GetTaskDTO> create(@RequestBody @Valid CreateTaskDTO request, UriComponentsBuilder builder){
        var response = taskService.createTask(request);
        var uri = builder.path("api/task/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTaskDTO> readTask(@PathVariable @NotNull Long id){
        var response = taskService.getTask(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<GetTaskDTO> update (@RequestBody @Valid UpdateTaskDTO request){
        var response = taskService.updateTaskDto(request);
        return  ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public  ResponseEntity delete (@PathVariable @NotNull Long id){
        taskService.deleteTask(id);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/favorite/{id}")
    @Transactional
    public  ResponseEntity favoriteTask(@PathVariable @NotNull Long id){
        taskService.favoriTask(id);
        return ResponseEntity.noContent().build();
    }





}
