package com.workintech.fswebs18challengemaven.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer value;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Color color;

    @PrePersist
    @PreUpdate
    private void validateCard() {
        if(type == Type.JOKER) {
            this.color = null;
            this.value = null;
        } else if(type != null && value != null) {
            throw new IllegalArgumentException("A card cannot have both a type and a value.");
        } else if (type == null && value == null) {
            throw new IllegalArgumentException("A card must have either a type or a value.");
        }
    }

}
