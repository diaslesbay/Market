package com.example.test.service;

import com.example.test.model.Card;
import com.example.test.model.User;
import com.example.test.repository.CardRepository;
import com.example.test.validator.Time;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final Time time = new Time();
    public Optional<Card> getCardByUserId(Long userId){
        return cardRepository.findCardByUserUserId(userId);
    }

    public void save(User user){
        Card card = Card.builder()
                .cardHoldName(user.getFirstname()+" "+user.getLastname())
                .IBAN(generateRandomSixteenDigits())
                .CVV(generateRandomThreeDigits())
                .givenTime(time.dateNow())
                .expirationTime(time.expirationDate())
                .balance(BigDecimal.valueOf(0))
                .user(user)
                .build();
        cardRepository.save(card);
    }
    private int generateRandomThreeDigits() {
        Random random = new Random();
        int randomThreeDigits = random.nextInt(900) + 100;

        while (!checkRandomThreeDigits(randomThreeDigits))
            randomThreeDigits = random.nextInt(900) + 100;

        return randomThreeDigits;
    }
    private boolean checkRandomThreeDigits(Integer cvv){
        Optional<Card> card = cardRepository.findCVV(cvv);
        return card.isEmpty();
    }

    private String generateRandomSixteenDigits() {
        Random random = new Random();
        long randomSixteenDigits = (long)  Math.floor(random.nextDouble() * 9_000_000_000_000_000L) + 1_000_000_000_000_000L;
        while (!checkRandomSixteenDigits(randomSixteenDigits))
            randomSixteenDigits = (long) (Math.floor(random.nextDouble() * 9_000_000_000_000_000L) + 1_000_000_000_000_000L);
        return String.valueOf(randomSixteenDigits);
    }
    private boolean checkRandomSixteenDigits(Long iban){
        Optional<Card> card = cardRepository.findIban(String.valueOf(iban));

        return card.isEmpty();
    }
}
