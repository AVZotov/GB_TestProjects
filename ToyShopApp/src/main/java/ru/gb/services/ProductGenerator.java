package ru.gb.services;

import ru.gb.entities.Storage;

public class ProductGenerator {
    public static void fillStorage(Storage storage){
        storage.addProduct("TeddyBear", 20, 30);
        storage.addProduct("RailRoad", 35, 10);
        storage.addProduct("Postal Card", 200, 70);
        storage.addProduct("Puzzles", 30, 15);
        storage.addProduct("RedCar", 70, 45);
        storage.addProduct("Doll", 15, 15);
        storage.addProduct("Snipping top", 100, 60);
        storage.addProduct("Kite", 20, 50);
        storage.addProduct("Ball", 200, 70);
        storage.addProduct("Scooter", 20, 15);
        storage.addProduct("Drum", 20, 40);
        storage.addProduct("Toy Blocks", 300, 80);
        storage.addProduct("Balloons", 1000, 90);
        storage.addProduct("Bicycle", 10, 5);
        storage.addProduct("Yo-Yo", 50, 60);
    }
}
