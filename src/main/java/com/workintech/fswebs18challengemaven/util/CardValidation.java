package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import org.springframework.http.HttpStatus;

public class CardValidation {
    public static void validateCard(Card card) {
        if(card.getType() != null && card.getValue() != null) {
            throw new CardException("A card cannot have both a type and a value.", HttpStatus.BAD_REQUEST);
        }

        if(card.getType() == null && card.getValue() == null) {
            throw new CardException("A card must have either a type or a value.", HttpStatus.BAD_REQUEST);
        }

        if(card.getType() == Type.JOKER) {
            throw new CardException("A joker card cannot have a color or a value.", HttpStatus.BAD_REQUEST);
        }

    }
}
