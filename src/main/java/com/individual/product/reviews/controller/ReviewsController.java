package com.individual.product.reviews.controller;

import com.individual.product.reviews.entities.Review;
import com.individual.product.reviews.repositories.ReviewsRepository;
import com.individual.product.reviews.entities.Product;
import com.individual.product.reviews.entities.mongo.ReviewMongo;
import com.individual.product.reviews.repositories.ProductRepository;
import com.individual.product.reviews.repositories.mongo.ReviewsMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewsMongoRepository reviewsMongoRepository;

    @Autowired
    ReviewsRepository reviewsJpaRepository;

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<ReviewMongo> createReviewForProduct(@PathVariable("productId") Long productId, @RequestBody ReviewMongo review) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            review.setProductId(product.get().getId());
            // create review for jps
            Review reviewJpa = new Review();
            reviewJpa.setName(review.getName());
            reviewJpa.setProduct(product.get());
            reviewJpa = reviewsJpaRepository.save(reviewJpa);

            // setting review id
            review.setId(reviewJpa.getId().intValue());
            return new ResponseEntity<ReviewMongo>(reviewsMongoRepository.save(review), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<ReviewMongo>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<ReviewMongo>> listReviewsForProduct(@PathVariable("productId") Long productId) {
        return new ResponseEntity<List<ReviewMongo>>(reviewsMongoRepository.findByProductId(productId), HttpStatus.OK);
    }
}