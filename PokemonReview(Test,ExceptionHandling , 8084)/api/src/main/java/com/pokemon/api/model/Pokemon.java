package com.pokemon.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Pokemon {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL , orphanRemoval = true) //what does orphanRemoval do is that if we delete the parent entity , why will we need to keep the child entities related to  it also . So when we delet the parent entity the related child entities will be deleted too
    private List<Review> reviews = new ArrayList<Review>();


}
