package ru.gb.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Storage {
    private final List<StorageCell> storage;

    public Storage() {
        this.storage = new ArrayList<>();
    }

    public List<StorageCell> getStorage() {
        return storage;
    }

    public void addProduct(String productName, int quantity, int weightInLottery) {

        if (quantity < 0) return;

        for (StorageCell storageCell : storage) {
            if (Objects.equals(productName, storageCell.getProduct().getName())) {
                storageCell.setQuantity(quantity);
                return;
            }
        }

        storage.add(new StorageCell(new Product(productName, weightInLottery), quantity));
    }

    public void removeProduct(Product product, int quantity) {
        for (int i = 0; i < storage.size(); i++) {
            StorageCell storageCell = storage.get(i);

            if (storageCell.getProduct() == product) {
                if (storageCell.getQuantity() == quantity) {
                    storage.remove(storageCell);
                }

                if (storageCell.getQuantity() > quantity) {
                    storageCell.setQuantity(-quantity);
                }
            }
        }
    }
}
