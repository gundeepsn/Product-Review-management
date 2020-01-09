package com.individual.product.reviews.repositories;

import com.individual.product.reviews.entities.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment,Long> {

    @Query(
            value = "SELECT * FROM Comment c WHERE c.review_id = ?1",
            nativeQuery = true)
    List<Comment> findAllByReviewId(Long reviewId);
}
