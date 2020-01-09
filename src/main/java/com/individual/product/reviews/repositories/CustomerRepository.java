package com.individual.product.reviews.repositories;

import com.individual.product.reviews.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
