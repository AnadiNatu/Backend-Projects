package com.example.Task_SpringBoot.services.employee;

import com.example.Task_SpringBoot.dto.CommentDto;
import com.example.Task_SpringBoot.dto.TaskDto;
import com.example.Task_SpringBoot.entities.Comment;
import com.example.Task_SpringBoot.entities.Task;
import com.example.Task_SpringBoot.entities.Users;
import com.example.Task_SpringBoot.enums.TaskStatus;
import com.example.Task_SpringBoot.repository.CommentRepository;
import com.example.Task_SpringBoot.repository.TaskRepository;
import com.example.Task_SpringBoot.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl  implements EmployeeService{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<TaskDto> getTaskByUserId() {

        Users user = jwtUtil.getLoggedInUser();

        if (user != null){
          return   taskRepository.findAllByUserId(user.getId())
                  .stream()
                  .sorted(Comparator.comparing(Task::getDueDate).reversed())
                  .map(task -> task.getTaskDto())
                  .collect(Collectors.toList());
        }
        throw new EntityNotFoundException("Entity not found");
    }

    @Override
    public TaskDto updateTask(Long id, String status) {
       Optional<Task> optionalTask =  taskRepository.findById(id);

       if (optionalTask.isPresent()){

           Task existingTask = optionalTask.get();
           existingTask.setTaskStatus(mapStringToTaskStatus(status));
           return taskRepository.save(existingTask).getTaskDto();

       }

       throw new EntityNotFoundException("Task Not Found");
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        return optionalTask.map(Task::getTaskDto).orElseThrow(null);
    }

    @Override
    public CommentDto createComment(Long taskId, String content) {

        Optional<Task> optionalTask = taskRepository.findById(taskId);

        Users user = jwtUtil.getLoggedInUser();

        if (optionalTask.isPresent() && user != null ){

            Comment comment = new Comment();
            comment.setCreatedAt(new Date());
            comment.setContent(content);
            comment.setTask(optionalTask.get());
            comment.setUser(user);
            return commentRepository.save(comment).getCommentDto();
        }
        throw new EntityNotFoundException("Task and User not Found");

    }

    @Override
    public List<CommentDto> getCommentByTaskId(Long taskId) {
        return commentRepository.findByTaskId(taskId).stream().map(Comment::getCommentDto).collect(Collectors.toList());
    }


    public TaskStatus mapStringToTaskStatus(String status){

        return switch (status) {
            case "PENDING" -> TaskStatus.PENDING;
            case "INPROGRESS" -> TaskStatus.IN_PROGRESS;
            case "COMPLETED" -> TaskStatus.COMPLETED;
            case "DEFERRED" -> TaskStatus.DEFERRED;
            default -> TaskStatus.CANCELLED;

        };

    }
}
