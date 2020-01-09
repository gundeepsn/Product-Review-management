package com.individual.product.reviews.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

//    @Lob
//    @Column(name = "comment_text")
//    private String text;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id",referencedColumnName = "ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Review review;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "customer_id",referencedColumnName = "ID", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Customer customer;

//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
}
