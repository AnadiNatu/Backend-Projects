package com.example.Task_SpringBoot.repository;

import com.example.Task_SpringBoot.dto.CommentDto;
import com.example.Task_SpringBoot.dto.TaskDto;
import com.example.Task_SpringBoot.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment , Long> {


    List<Comment> findByTaskId(Long taskId);
}
