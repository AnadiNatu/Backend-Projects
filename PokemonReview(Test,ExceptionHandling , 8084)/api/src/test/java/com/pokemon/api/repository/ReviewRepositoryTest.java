package com.pokemon.api.repository;


import com.pokemon.api.model.Review;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReviewRepositoryTest {


    @Autowired
    private ReviewRepository reviewRepository;


    @Test
    public void reviewRepository_saveAll_returnedSavedReview(){

        //Arrange
        Review review = Review.builder().title("title").content("content").stars(5).build();

       //Act
        Review saveReview = reviewRepository.save(review);

        //Assert
        Assertions.assertThat(saveReview).isNotNull();
        Assertions.assertThat(saveReview.getId()).isGreaterThan(0);


    }


    @Test
    public void reviewRepository_findAll_returnAllSavedReview(){

        //Arrange
        Review review = Review.builder().title("title").content("content").stars(5).build();
        Review review1 = Review.builder().title("title1").content("content1").stars(4).build();


        Review saveReview = reviewRepository.save(review);
        Review saveReview1 = reviewRepository.save(review1);

        //Act
        List<Review> reviewList = reviewRepository.findAll();


        //Assert
        Assertions.assertThat(reviewList).isNotNull();
        Assertions.assertThat(reviewList.size()).isEqualTo(2);


    }

    @Test
    public void reviewRepository_findById_getReviewById(){

        //Arrange
        Review review = Review.builder().title("title").content("content").stars(5).build();

        Review saveReview = reviewRepository.save(review);


        //Act
        Review reviewReturn = reviewRepository.findById(review.getId()).get();


        //Assert
        Assertions.assertThat(reviewReturn).isNotNull();
        Assertions.assertThat(reviewReturn.getTitle()).isEqualTo("title");
        Assertions.assertThat(reviewReturn.getContent()).isEqualTo("content");


    }

    @Test
    public void reviewRepository_update_returnsUpdatedReview(){

        //Arrange
        Review review = Review.builder().title("title").content("content").stars(5).build();

        reviewRepository.save(review);

        Review reviewToUpadte = reviewRepository.findById(review.getId()).get();

        reviewToUpadte.setContent("content1");
        reviewToUpadte.setTitle("title1");
        reviewToUpadte.setStars(3);

        //Act
        Review updatedReview = reviewRepository.save(reviewToUpadte);

        //Assert
        Assertions.assertThat(updatedReview).isNotNull();
        Assertions.assertThat(updatedReview.getTitle()).isEqualTo("title1");
        Assertions.assertThat(updatedReview.getContent()).isEqualTo("content1");
        Assertions.assertThat(updatedReview.getStars()).isEqualTo(3);

    }


    @Test
    public void reviewRepository_deleteById_deletesReviewById(){

        //Arrange
        Review review = Review.builder().title("title").content("content").stars(5).build();

        reviewRepository.save(review);

        //Act

        reviewRepository.deleteById(review.getId());
        Optional<Review>  deleteReview = reviewRepository.findById(review.getId());

        //Assert
        Assertions.assertThat(deleteReview).isEmpty();

    }

}
