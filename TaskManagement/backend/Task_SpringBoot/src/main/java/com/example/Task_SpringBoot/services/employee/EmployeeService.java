package com.example.Task_SpringBoot.services.employee;

import com.example.Task_SpringBoot.dto.CommentDto;
import com.example.Task_SpringBoot.dto.TaskDto;

import java.util.List;

public interface EmployeeService {

    public List<TaskDto> getTaskByUserId();

    TaskDto updateTask(Long id , String status);

    CommentDto createComment(Long taskId , String content);

    List<CommentDto> getCommentByTaskId(Long id);

    TaskDto getTaskById(Long id);

}
