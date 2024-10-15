package com.example.Task_SpringBoot.services.admin;

import com.example.Task_SpringBoot.dto.CommentDto;
import com.example.Task_SpringBoot.dto.TaskDto;
import com.example.Task_SpringBoot.dto.UserDto;

import java.util.List;

public interface AdminService {

    List<UserDto> getUser();

    TaskDto createTask(TaskDto taskDto);

    List<TaskDto> getAllTask();

    void deleteTask(Long id);

    TaskDto getTaskById(Long id);

    TaskDto updateTask(Long id , TaskDto taskDto);

    List<TaskDto> getTaskByTitle(String title);

    CommentDto createComment(Long taskId , String content);

    List<CommentDto> getCommentByTaskId(Long id);


}
