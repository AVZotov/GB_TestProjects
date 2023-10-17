package ru.gb.entities;

import java.util.UUID;

public class Product {
    private final String id;
    private final String name;
    private int weight;

    public Product(String name, int weight) {
        this.name = name;
        this.weight = weight;
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("*".repeat(40) + "\nName: %s\tChance to win: %d\nID: %s\n" + "*".repeat(40), name, weight, id);
    }
}
