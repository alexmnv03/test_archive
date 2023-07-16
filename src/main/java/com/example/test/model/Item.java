package com.example.test.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Item() {
    }
    public Item(Integer value) {
        this.value = value;
    }
}
