package com.example.test.service;

import com.example.test.model.Basket;
import com.example.test.model.BasketProduct;
import com.example.test.model.Product;
import com.example.test.model.User;
import com.example.test.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketService {
    private final BasketRepository basketRepository;
    private final UserService userService;
    private final ProductService productService;
    private final BasketProductService basketProductService;


    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public Basket getUsersBasket(Long userId){
        return basketRepository.findUsersBasket(userId);
    }

    @Transactional
    public Basket addToBasketProduct(UserDetails userDetails, Long id){
        User user = userService.findByUsername(userDetails.getUsername()).orElseThrow();
        Product product = productService.getProductsByProductId(id).orElseThrow();

        Basket basket = getUsersBasket(user.getUserId());

        List<BasketProduct> list = basket.getBasketProducts();

        boolean check = false;
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).getProduct().equals(product)) {
                if(product.getQuantityOrWeight() - list.get(i).getQuantity() < 1) throw new RuntimeException("Product is not available");

                list.get(i).setQuantity(list.get(i).getQuantity() + 1);
                check = true;
            }
        }
        if(!check){
            if(product.getQuantityOrWeight() < 1) throw new RuntimeException("Product is not available");
            BasketProduct basketProduct = BasketProduct.builder()
                    .product(product)
                    .quantity(1L)
                    .id(basket.getBasketId())
                    .build();

            basketProductService.save(basketProduct);
            list.add(basketProduct);
        }
        basketRepository.save(basket);
        return basket;
    }

    @Transactional
    public Basket deleteProductFromBasket(UserDetails userDetails, Long productId){
        log.info("Successfully arrived productId "+productId);

        User user = userService.findByUsername(userDetails.getUsername()).orElseThrow();
        Product product = productService.getProductsByProductId(productId).orElseThrow();
        Basket basket = getUsersBasket(user.getUserId());

        List<BasketProduct> list = basket.getBasketProducts();
        List<BasketProduct> a = list;

        for (BasketProduct value : list) {
            if (value.getProduct().equals(product)){
                a.remove(value);

                BasketProduct basketProduct = entityManager.find(BasketProduct.class, value.getBasketProductId());
                entityManager.remove(basketProduct);
                break;
            }
        }

        basket.setBasketProducts(a);
        log.info("Successfully deleted basket");
        basketRepository.save(basket);
        return basket;
    }

    @Transactional
    public Basket deleteProductQuantityFromBasket(UserDetails userDetails, Long productId){
        User user = userService.findByUsername(userDetails.getUsername()).orElseThrow();
        Product product = productService.getProductsByProductId(productId).orElseThrow();
        Basket basket = getUsersBasket(user.getUserId());

        List<BasketProduct> list = basket.getBasketProducts();

        for (BasketProduct value : list) {
            if (value.getProduct().equals(product) && value.getQuantity() > 1)
                value.setQuantity(value.getQuantity() - 1);
            else if(value.getProduct().equals(product) && value.getQuantity() == 1) {
                list.remove(value);
                BasketProduct basketProduct = entityManager.find(BasketProduct.class, value.getBasketProductId());
                entityManager.remove(basketProduct);
            }
        }
        log.info("Successfully deleted basket quantity");
        basket.setBasketProducts(list);
        basketRepository.save(basket);
        return basket;
    }
    public Basket createBasket(User user){
        return Basket.builder()
                .basketProducts(new CopyOnWriteArrayList<>())
                .user(user)
                .build();
    }

    public Basket save(Basket basket){
        return basketRepository.save(basket);
    }

}
