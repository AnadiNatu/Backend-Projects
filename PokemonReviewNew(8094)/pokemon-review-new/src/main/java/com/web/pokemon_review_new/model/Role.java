package com.web.pokemon_review_new.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Roles_Table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //We need to make a join table to establish a connection with the users and the roles as a single user can have multiple roles
    //we to apply many to many mapping between the two entities so that we can create a joined table which specifies the roles a single user has

}
