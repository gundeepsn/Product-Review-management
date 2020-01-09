package com.individual.product.reviews.controller;

import com.individual.product.reviews.entities.Comment;
import com.individual.product.reviews.entities.Review;
import com.individual.product.reviews.entities.mongo.ReviewMongo;
import com.individual.product.reviews.repositories.CommentRepository;
import com.individual.product.reviews.repositories.ProductRepository;
import com.individual.product.reviews.repositories.ReviewsRepository;
import com.individual.product.reviews.repositories.mongo.ReviewsMongoRepository;
import com.individual.product.reviews.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ReviewsMongoRepository reviewsMongoRepository;

    @Autowired
    CommentRepository commentJpaRepository;

    @Autowired
    ReviewsRepository reviewsJpaRepository;

    @Autowired
    ProductRepository productRepository;
    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<com.individual.product.reviews.entities.mongo.Comment> createCommentForReview(@PathVariable("reviewId") int reviewId, @RequestBody com.individual.product.reviews.entities.mongo.Comment comment) {
        ReviewMongo review= reviewsMongoRepository.findById(reviewId).orElseThrow(()->new HttpServerErrorException(HttpStatus.NOT_FOUND));
        List<com.individual.product.reviews.entities.mongo.Comment> comments=review.getComments();
        comments.add(comment);
        // creating comment for jpa
        Comment commentJpa=new Comment();
        Review reviewJpa=reviewsJpaRepository.findById(Long.valueOf(reviewId)).orElseThrow(()->new HttpServerErrorException(HttpStatus.NOT_FOUND));
        commentJpa.setReview(reviewJpa);
        commentJpaRepository.save(commentJpa);

        review.setComments(comments);
        reviewsMongoRepository.save(review);
        return new ResponseEntity<com.individual.product.reviews.entities.mongo.Comment>(comment,HttpStatus.CREATED);
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public List<com.individual.product.reviews.entities.mongo.Comment> listCommentsForReview(@PathVariable("reviewId") int reviewId) {
        ReviewMongo review= reviewsMongoRepository.findById(reviewId).orElseThrow(()->new HttpServerErrorException(HttpStatus.NOT_FOUND));
        return review.getComments();
    }
}