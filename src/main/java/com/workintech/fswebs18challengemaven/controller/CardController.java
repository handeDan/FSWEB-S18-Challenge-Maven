package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech/cards")
@Slf4j
public class CardController {
    private final CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @GetMapping("/byColor/{color}")
    public List<Card> getAllCardsByColor(@PathVariable String color) {
        return cardRepository.findByColor(color);
    }

    @GetMapping("/byValue/{value}")
    public Card getCardByValue(@PathVariable Integer value) {
        return cardRepository.findByValue(value).get(0);
    }

    @GetMapping("/byType/{type}")
    public List<Card> getAllCardsByType(@PathVariable String type) {
        return cardRepository.findByType(type);
    }

    @PostMapping
    public Card createCard(@RequestBody Card card) {
        return cardRepository.save(card);
    }

    @PutMapping
    public Card updateCard(Long id) {
        Card foundCard = cardRepository.findById(id);
        if(foundCard != null) return cardRepository.update(foundCard);
        return foundCard;
    }

    @DeleteMapping("/{id}")
    public void deleteCard(Long id) {
        Card foundCard = cardRepository.findById(id);
        if(foundCard != null) cardRepository.remove(foundCard.getId());
    }
}
