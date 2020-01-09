package com.individual.product.reviews.repositories;

import com.individual.product.reviews.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {
}
