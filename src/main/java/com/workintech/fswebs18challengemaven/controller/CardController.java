package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cards")
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
    public ResponseEntity<?> getAllCardsByColor(@PathVariable String color) {
        List<Card> cards = cardRepository.findByColor(color);
        if (cards.isEmpty()) {
            throw new CardException("Card not found", HttpStatus.NOT_FOUND);  // 404 Not Found
        }
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/byValue/{value}")
    public List<Card> getCardByValue(@PathVariable Integer value) {
        return cardRepository.findByValue(value);
    }

    @GetMapping("/byType/{type}")
    public List<Card> getAllCardsByType(@PathVariable String type) {
        return cardRepository.findByType(type);
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        Card savedCard = cardRepository.save(card);
        return ResponseEntity.ok(savedCard); // .ok() yerine .created() kullanmayın
    }

    @PutMapping("/")
    public ResponseEntity<Card> updateCard(@RequestBody Card card) {
        try {
            Card updatedCard = cardRepository.update(card);
            return ResponseEntity.ok(updatedCard);
        } catch (Exception e) {
            log.error("Error updating card", e);
            throw new CardException("Failed to update card", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCard(Long id) {
        Card foundCard = cardRepository.findById(id);
        if(foundCard != null) cardRepository.remove(foundCard.getId());
    }
}
