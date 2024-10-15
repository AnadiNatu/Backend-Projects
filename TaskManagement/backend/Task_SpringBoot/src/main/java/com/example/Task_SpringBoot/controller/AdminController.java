package com.example.Task_SpringBoot.controller;

import com.example.Task_SpringBoot.dto.CommentDto;
import com.example.Task_SpringBoot.dto.TaskDto;
import com.example.Task_SpringBoot.entities.Task;
import com.example.Task_SpringBoot.services.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return  ResponseEntity.ok(adminService.getUser());
    }


    @PostMapping("/task")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto){

        TaskDto createTaskDto = adminService.createTask(taskDto);

        if (createTaskDto == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(createTaskDto);
    }

    @GetMapping("/tasks")
    public ResponseEntity<?> getAllTask(){
        return ResponseEntity.ok(adminService.getAllTask());
    }


    @DeleteMapping("/task/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){

//        Optional<TaskDto> deletedTask = adminService.getAllTask().stream().filter(task -> task.getId().equals(id)).findFirst();
//
//        if (deletedTask.isPresent()) {
//            return ResponseEntity.ok(deletedTask);
//        }else
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(deletedTask);

        adminService.deleteTask(id);

        return ResponseEntity.ok(null);
    }

    @GetMapping("task/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok(adminService.getTaskById(id));
    }

    @PutMapping("task/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id , @RequestBody TaskDto taskDto){

        TaskDto updateTask = adminService.updateTask(id, taskDto);

        if (updateTask == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updateTask);
    }


    @GetMapping("/tasks/search/{title}")
    public ResponseEntity<List<TaskDto>> searchTask(@PathVariable String title){
        return ResponseEntity.ok(adminService.getTaskByTitle(title));
    }


    @PostMapping("/task/comment/{taskId}")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long taskId , @RequestParam String content){
        CommentDto createdComment = adminService.createComment(taskId, content);

        if (createdComment != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @GetMapping("/comments/{taskId}")
    public ResponseEntity<List<CommentDto>> getCommentByTaskId(@PathVariable(name = "taskId") Long id){

        return ResponseEntity.ok(adminService.getCommentByTaskId(id));


    }
}
