package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Type;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

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
        return entityManager.createQuery("SELECT c FROM Card c WHERE c.color = :color", Card.class)
                .setParameter("color", color)
                .getResultList();
    }

    @Override
    public List<Card> findAll() {
        return entityManager.createQuery("SELECT c FROM Card c", Card.class)
                .getResultList();
    }

    @Override
    public List<Card> findByValue(Integer value) {
        return entityManager.createQuery("SELECT c FROM Card c WHERE c.value = :value", Card.class)
                .setParameter("value", value)
                .getResultList();
    }

    @Override
    public List<Card> findByType(String type) {
        return entityManager.createQuery("SELECT c FROM Card c WHERE c.type = :type", Card.class)
                .setParameter("type", type)
                .getResultList();
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
