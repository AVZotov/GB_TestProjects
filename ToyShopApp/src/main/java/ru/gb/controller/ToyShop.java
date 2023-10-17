package ru.gb.controller;

import ru.gb.entities.Product;
import ru.gb.entities.Storage;
import ru.gb.entities.StorageCell;
import ru.gb.services.FileIO;
import ru.gb.services.ProductGenerator;
import ru.gb.ui.ConsoleIO;

import java.util.*;

public class ToyShop {
    private final Storage storage;
    private final List<Product> promoProducts;
    private final ConsoleIO consoleIO;
    private final FileIO fileIO;

    public ToyShop() {
        this.fileIO = new FileIO();
        this.storage = new Storage();
        ProductGenerator.fillStorage(storage);
        this.promoProducts = new ArrayList<>();
        this.consoleIO = new ConsoleIO();
    }

    public void run() {
        boolean isProgramRun = true;

        while (isProgramRun) {
            consoleIO.printMainMenu();
            consoleIO.printMessage("\nSelect menu point to continue: ");

            switch (consoleIO.getUserInput()) {
                case "1":
                    consoleIO.printStock(storage.getStorage());
                    break;
                case "2":
                    startSale();
                    break;
                case "3":
                    isProgramRun = false;
                    break;
                default:
                    consoleIO.printMessage("\n\nWarning! Unexpected value\n");
            }
        }
    }

    private void startSale() {
        boolean isSale = true;
        while (isSale) {
            consoleIO.printSaleMenu();
            consoleIO.printMessage("\nSelect menu point to continue: ");

            switch (consoleIO.getUserInput()) {
                case "1":
                    setProductWeight();
                    break;
                case "2":
                    releasePromoProducts();
                    isSale = false;
                    break;
                case "3":
                    isSale = false;
                    break;
                default:
                    consoleIO.printMessage("\n\nWarning! Unexpected value\n");
            }


        }
    }

    private void releasePromoProducts() {
        generatePromoProducts();
        boolean continuePromoAction = true;

        while (continuePromoAction) {
            consoleIO.printReleaseMenu();
            consoleIO.printMessage("\nSelect menu point to continue: ");

            switch (consoleIO.getUserInput()) {
                case "1":
                    continuePromoAction = release();
                    break;
                case "2":
                    continuePromoAction = false;
                    break;
                default:
                    consoleIO.printMessage("\n\nWarning! Unexpected value\n");
            }
        }
    }

    private boolean release() {
        if (promoProducts.isEmpty()){
            consoleIO.printMessage("No more products for sale! Exiting sales event\n");
            return false;
        }
        Product product = promoProducts.get(0);
        consoleIO.printMessage(String.format("Product: %s released to customer\n", product.getName()));
        fileIO.save(product);
        promoProducts.remove(0);
        return true;
    }

    private void setProductWeight() {
        consoleIO.printMessage("Enter product name: ");
        String productName = consoleIO.getUserInput();
        boolean isProductFound = false;

        for (StorageCell storageCell : storage.getStorage()) {
            if (Objects.equals(storageCell.getProduct().getName(), productName)) {
                consoleIO.printMessage("Enter new product weight in limits from 1 to 100: ");
                storageCell.getProduct().setWeight(consoleIO.getIntFromUser());
                isProductFound = true;
            }
        }

        if (!isProductFound) System.out.printf("No products with name: %s found\n\n", productName);
    }

    private void generatePromoProducts() {
        int totalProductsOnSale = 5;
        consoleIO.printMessage(String.format("Total number of products on sale are: %d\n", totalProductsOnSale));
        consoleIO.printMessage("Randomly choosing products for sale...\n");

        while (promoProducts.size() < totalProductsOnSale) {
            for (StorageCell storageCell : storage.getStorage()) {
                if (hasProductSelected(storageCell.getProduct())) {
                    storage.removeProduct(storageCell.getProduct(), 1);
                    promoProducts.add(storageCell.getProduct());
                    if (promoProducts.size() == totalProductsOnSale) break;
                }
            }
        }

        consoleIO.printMessage("Promo products for sale successfully generated:\n");
        System.out.println(promoProducts);
    }

    private boolean hasProductSelected(Product product) {
        Random random = new Random();
        int max_chance = 100;

        return product.getWeight() <= random.nextInt(max_chance + 1);
    }
}
