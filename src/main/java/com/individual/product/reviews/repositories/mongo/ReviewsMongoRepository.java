package com.individual.product.reviews.repositories.mongo;

import com.individual.product.reviews.entities.mongo.ReviewMongo;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewsMongoRepository extends MongoRepository<ReviewMongo, Integer> {

    @Query(value = "{'productId':?0}")
    List<ReviewMongo> findByProductId(Long productId);

    @Query(value = "{'_id':?0}")
    Optional<ReviewMongo> findById(int id, Sort sort);

    @Query(value = "{'_id':?0}")
    Optional<ReviewMongo> findById(int id);

}
