package com.individual.product.reviews.repositories;

import com.individual.product.reviews.entities.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewsRepository extends CrudRepository<Review, Long> {

    @Query(
            value = "SELECT * FROM Review r WHERE r.product_id = ?1",
            nativeQuery = true)
    List<Review> findAllByProductId(Long productId);
}
