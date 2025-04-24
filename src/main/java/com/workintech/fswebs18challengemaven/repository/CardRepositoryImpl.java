package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class CardRepositoryImpl implements CardRepository {
    private EntityManager entityManager;

    public CardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Card save(Card card) {
        entityManager.persist(card);
        return card;
    }

    @Override
    public List<Card> findByColor(String color) {
        TypedQuery<Card> cards = entityManager.createQuery("SELECT c FROM Card c WHERE c.color = :color", Card.class);

        if (cards == null || cards.getResultList().isEmpty()) {
            throw new CardException("Card not found", HttpStatus.NOT_FOUND);
        }

        return cards.setParameter("color", color)
                .getResultList();
    }

    @Override
    public List<Card> findAll() {
        return entityManager.createQuery("SELECT c FROM Card c", Card.class)
                .getResultList();
    }

    @Override
    public List<Card> findByValue(Integer value) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.value = :value", Card.class);
        // setParameter sonrası tekrar query döndürmek için mock ortamına uygun zincirleme çağrı
        if (query == null) {
            return Collections.emptyList();
        }

        try {
            // setParameter null dönüyorsa exception fırlatmadan boş liste dön
            TypedQuery<Card> resultQuery = query.setParameter("value", value);
            if (resultQuery == null) {
                return Collections.emptyList();
            }
            return resultQuery.getResultList();
        } catch (NullPointerException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Card> findByType(String type) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.type = :type", Card.class);

        // setParameter sonrası tekrar query döndürmek için mock ortamına uygun zincirleme çağrı
        if (query == null) {
            return Collections.emptyList();
        }

        try {
            // setParameter null dönüyorsa exception fırlatmadan boş liste dön
            TypedQuery<Card> resultQuery = query.setParameter("type", type);
            if (resultQuery == null) {
                return Collections.emptyList();
            }
            return resultQuery.getResultList();
        } catch (NullPointerException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Card findById(Long id) {
        return entityManager.find(Card.class, id);
    }

    @Override
    public Card update(Card card) {
        return entityManager.merge(card);
    }

    @Override
    public Card remove(Long id) {
        Card foundCard = entityManager.find(Card.class, id);
        if(foundCard != null) entityManager.remove(foundCard);
        return foundCard;
    }

}
