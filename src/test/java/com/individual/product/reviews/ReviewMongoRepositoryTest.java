package com.individual.product.reviews;

import com.individual.product.reviews.entities.mongo.ReviewMongo;
import com.individual.product.reviews.repositories.mongo.ReviewsMongoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ReviewMongoRepositoryTest {

    @Autowired
    private ReviewsMongoRepository reviewsMongoRepository;

    @Test()
    public void testGetReviewById() {
//        DBObject dbObject= BasicDBObjectBuilder.start().add("id")
        ReviewMongo newReview = new ReviewMongo();
        newReview.setProductId(1l);
        newReview.setName("iPhone first review");
        newReview = reviewsMongoRepository.save(newReview);
        ReviewMongo savedReview = reviewsMongoRepository.findById(newReview.getId()).orElse(new ReviewMongo());
        assertThat(savedReview.getId(), equalTo(newReview.getId()));
        assertThat(savedReview.getName(), equalTo("iPhone first review"));
        assertThat(savedReview.getComments(), hasSize(0));
    }
}
