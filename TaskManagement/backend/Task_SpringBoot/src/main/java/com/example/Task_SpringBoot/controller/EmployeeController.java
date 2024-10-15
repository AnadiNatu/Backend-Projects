package com.example.Task_SpringBoot.controller;

import com.example.Task_SpringBoot.dto.CommentDto;
import com.example.Task_SpringBoot.dto.TaskDto;
import com.example.Task_SpringBoot.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin("*")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getTaskByUserId(){
        return ResponseEntity.ok(employeeService.getTaskByUserId());
    }

    @GetMapping("/task/{id}/{status}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id , @PathVariable String status){

        TaskDto updatedTaskStatus = employeeService.updateTask(id, status);

        if (updatedTaskStatus == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedTaskStatus);
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedTaskStatus);
    }


    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getTaskById(id));
    }

    @PostMapping("/task/comment/{taskId}")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long taskId , @RequestParam String content){
        CommentDto createdCommentDto = employeeService.createComment(taskId , content);
        if (createdCommentDto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCommentDto);
    }

    @GetMapping("/comments/{taskId}")
    public ResponseEntity<List<CommentDto>> getCommentByTaskId(@PathVariable Long taskId){
        return ResponseEntity.ok(employeeService.getCommentByTaskId(taskId));
    }
}
