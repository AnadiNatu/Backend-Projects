package com.example.Task_SpringBoot.entities;


import com.example.Task_SpringBoot.dto.CommentDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "user_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Users user;


    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "task_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Task task;


    public CommentDto getCommentDto(){
        CommentDto commentDto = new CommentDto();

        commentDto.setId(id);
        commentDto.setContent(content);
        commentDto.setCreatedAt(createdAt);
        commentDto.setTaskId(task.getId());
        commentDto.setPostedBy(user.getName());

        return commentDto;

    }



}
