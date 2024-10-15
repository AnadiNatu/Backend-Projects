package com.web.pokemon_review_new.repository;


import com.web.pokemon_review_new.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByPokemonId(Long id);

}
