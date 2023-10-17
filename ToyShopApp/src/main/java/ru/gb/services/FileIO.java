package ru.gb.services;

import ru.gb.entities.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileIO {
    public void save(Product product){
        try (FileWriter writer = new FileWriter("SalesLog.txt", true)){
            writer.write(String.format("\n\nDate of product release: %s\n\n", getDateTime()) + product.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String getDateTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
}
