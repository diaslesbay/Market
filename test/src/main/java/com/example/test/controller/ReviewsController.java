package com.example.test.controller;

import com.example.test.model.Product;
import com.example.test.model.Review;
import com.example.test.service.ReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewsController {
    private final ReviewsService reviewsService;

    @PostMapping("/write-comment")
    public Review writeReview(Long productId, String comment, Integer rate){
        UserDetails authentication = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return reviewsService.writeReview(productId, comment, rate, authentication);
    }

    @GetMapping("/showThreeTheMostPopularProduct")
    public List<Object[]> showThreeTheMostPopularProduct(){
        return reviewsService.getThreeTheMostPopularProduct();
    }

}
