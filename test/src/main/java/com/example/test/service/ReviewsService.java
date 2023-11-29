package com.example.test.service;

import com.example.test.model.Product;
import com.example.test.model.Review;
import com.example.test.model.User;
import com.example.test.repository.ReviewsRepository;
import com.example.test.validator.Time;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewsService {
    private final ReviewsRepository reviewsRepository;
    private final ProductService productService;
    private final UserService userService;
    private final Time time = new Time();
    public Review writeReview(Long productId, String comment, Integer rate, UserDetails userDetails){
        Optional<Product> product = productService.getProductsByProductId(productId);
        Optional<User> user = userService.findByUsername(userDetails.getUsername());
        if(!product.isPresent() || !user.isPresent()) throw new RuntimeException("Product is not found");
        Review review = Review.builder()
                .rating(rate)
                .comment(comment)
                .product(product.get())
                .reviewTime(time.dateNow())
                .user(user.get())
                .build();

        return reviewsRepository.save(review);
    }

    public List<Object[]> getThreeTheMostPopularProduct(){
        return reviewsRepository.findByAllProductWithRating();
    }
}
