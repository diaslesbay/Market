package com.example.test.service;

import com.example.test.enums.TypeOfPayment;
import com.example.test.model.*;
import com.example.test.repository.OrderRepository;
import com.example.test.validator.Time;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final BasketService basketService;
    private final UserService userService;
    private final CardService cardService;
    private final Time time = new Time();
    private final CreditService creditService;
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final BasketProductService basketProductService;
    private final SaleService saleService;
    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public Order addOrder(UserDetails userDetails, String selectTypeOfPayment){
        User user = userService.findByUsername(userDetails.getUsername()).orElseThrow();
        Basket basket = basketService.getUsersBasket(user.getUserId());
        Optional<Card> card = cardService.getCardByUserId(user.getUserId());

        List<BasketProduct> basketProduct = basket.getBasketProducts();

        List<Product> products = new ArrayList<>();
        List<Long> quantities = new ArrayList<>();

        double total = 0.0;

        for (BasketProduct value : basketProduct){
            products.add(value.getProduct());
            value.getProduct().setQuantityOrWeight(value.getProduct().getQuantityOrWeight() - value.getQuantity());
            quantities.add(value.getQuantity());

            total += value.getProduct().getPrice().multiply(BigDecimal.valueOf(value.getQuantity())).doubleValue();
        }

        List<Long> ids = new ArrayList<>();
        for (BasketProduct value : basketProduct)
            ids.add(value.getBasketProductId());

        basketProductService.deleteBasketId(ids);

        log.info("After loop: "+basket.getBasketProducts());

        if(card.isPresent() && card.get().getBalance().doubleValue() < total) throw new RuntimeException("Not enough money");

        Order order = Order.builder()
                .orderDate(time.dateNow())
                .user(user)
                .totalAmount(BigDecimal.valueOf(total))
                .products(products)
                .build();
        orderRepository.save(order);

        log.info("The order was successfully created, the basketProduct was deleted.");

        Payment payment = Payment.builder()
                .paymentAmount(BigDecimal.valueOf(total))
                .orders(order)
                .paymentTime(time.dateNow())
                .build();
        log.info("The payment has been successfully created.");

        if(TypeOfPayment.DEBIT_CARD.name().equalsIgnoreCase(selectTypeOfPayment)) {
            card.get().setBalance(card.get().getBalance().subtract(BigDecimal.valueOf(total)));
            for (int i = 0; i < products.size(); i++) {
                Seller seller = products.get(i).getSeller();
                seller.setBalance(seller.getBalance().add(products.get(i).getPrice().multiply(BigDecimal.valueOf(quantities.get(i)))));
            }
            payment.setTypeOfPayment(TypeOfPayment.DEBIT_CARD);
            paymentService.save(payment);
            log.info("Successful payment by Debit card");
        }
        else {
            payment.setTypeOfPayment(TypeOfPayment.CREDIT_CARD);
            paymentService.save(payment);
            for (int i = 0; i < products.size(); i++) {
                Seller seller = products.get(i).getSeller();
                seller.setBalance(seller.getBalance().add(products.get(i).getPrice().multiply(BigDecimal.valueOf(quantities.get(i)))));
            }
            Credit credit = Credit.builder()
                    .startTime(time.dateNow())
                    .endTime(time.creditEndDate())
                    .card(card.get())
                    .monthlyPayment(payment.getPaymentAmount().divide(BigDecimal.valueOf(3),2, RoundingMode.HALF_UP))
                    .build();
            creditService.save(credit);
            log.info("Successfully paid by Credit card");
        }
        for (Product p: products) {
            if(p.getQuantityOrWeight() > 10){
                Optional<Sale> sale = saleService.getSaleByProduct(p.getProductId());
                if(sale.isPresent())
                    saleService.deleteByProductId(p.getProductId());
            }
            else{
                Optional<Sale> sale = saleService.getSaleByProduct(p.getProductId());
                if(sale.isPresent()) {
                    sale.get().setPercentage(sale.get().getPercentage() + 0.05);
                    sale.get().setStartTime(time.dateNow());
                    sale.get().setEndTime(time.saleEndDate());
                    log.info("Successfully changed 'Sale' for " +p.getProductName() +" "+sale.get().getPercentage());
                }
                else {
                    Sale newSale = Sale.builder()
                            .startTime(time.dateNow())
                            .endTime(time.saleEndDate())
                            .percentage(0.2)
                            .product(p)
                            .build();
                    saleService.save(newSale);
                    log.info("Successfully created 'Sale' for " +p.getProductName() +" "+sale.get().getPercentage());
                }
            }
        }
        return orderRepository.save(order);
    }
}
