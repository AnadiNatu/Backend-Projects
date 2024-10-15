package com.example.Task_SpringBoot.services.admin;

import com.example.Task_SpringBoot.dto.CommentDto;
import com.example.Task_SpringBoot.dto.TaskDto;
import com.example.Task_SpringBoot.dto.UserDto;
import com.example.Task_SpringBoot.entities.Comment;
import com.example.Task_SpringBoot.entities.Task;
import com.example.Task_SpringBoot.entities.Users;
import com.example.Task_SpringBoot.enums.TaskStatus;
import com.example.Task_SpringBoot.enums.UserRole;
import com.example.Task_SpringBoot.repository.CommentRepository;
import com.example.Task_SpringBoot.repository.TaskRepository;
import com.example.Task_SpringBoot.repository.UserRepository;
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
public class AdminServiceImpl implements AdminService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public List<UserDto> getUser() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getUserRole() == UserRole.EMPLOYEE)
                .map(Users::getDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Optional<Users> optionalUser = userRepository.findById(taskDto.getEmployeeId());

        if (optionalUser.isPresent()) {
            Task task = new Task();
            task.setTitle(taskDto.getTitle());
            task.setDescription(taskDto.getDescription());
            task.setPriority(taskDto.getPriority());
            task.setDueDate(taskDto.getDueDate());
            task.setTaskStatus(TaskStatus.IN_PROGRESS);
            task.setUser(optionalUser.get());
            return taskRepository.save(task).getTaskDto();
        }
        return null;
    }

    @Override
    public List<TaskDto> getAllTask() {
        return taskRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Task::getDueDate).reversed())
                .map(Task::getTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        return optionalTask
                .map(Task::getTaskDto)
                .orElseThrow(null);
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {

        Optional<Task> optionalTask = taskRepository.findById(id);
        Optional<Users> optionalUser = userRepository.findById(taskDto.getId());

        if (optionalTask.isPresent() && optionalUser.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setTitle(taskDto.getTitle());
            existingTask.setDescription(taskDto.getDescription());
            existingTask.setDueDate(taskDto.getDueDate());
            existingTask.setPriority(taskDto.getPriority());
            existingTask.setTaskStatus(mapStringToTaskStatus(String.valueOf((taskDto.getTaskStatus()))));
            existingTask.setUser(optionalUser.get());
            return taskRepository.save(existingTask).getTaskDto();
        }

        return null;
    }

    @Override
    public List<TaskDto> getTaskByTitle(String title) {
        return taskRepository.findAllByTitleContaining(title)
                .stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .map(Task::getTaskDto)
                .collect(Collectors.toList());
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
        return commentRepository
                .findByTaskId(taskId)
                .stream()
                .map(Comment::getCommentDto)
                .collect(Collectors.toList());
    }

    private TaskStatus mapStringToTaskStatus(String status){
        return switch (status){
            case "PENDING" -> TaskStatus.PENDING;
            case "IN_PROGRESS" -> TaskStatus.IN_PROGRESS;
            case "COMPLETE" -> TaskStatus.COMPLETED;
            case "DEFERRED" -> TaskStatus.DEFERRED;
            default -> TaskStatus.CANCELLED;
        };
    }


}
