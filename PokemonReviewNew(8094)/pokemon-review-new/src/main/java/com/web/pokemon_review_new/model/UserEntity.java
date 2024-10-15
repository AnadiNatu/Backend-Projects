package com.web.pokemon_review_new.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users_Table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String username;

    private String password;


    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles" ,joinColumns = @JoinColumn(name = "user_id" , referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id" , referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<Role>();

    //Many to many mapping always create a join table

}
