package com.example.webshopclient;

import com.example.webshopclient.shop.Category;
import com.example.webshopclient.shop.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Configuration
public class Nav implements CommandLineRunner {

    private final RestClient client;
    Scanner scan = new Scanner(System.in);

    boolean isRunning;

    @Autowired
    public Nav(RestClient restClient) {
        this.client = restClient;
    }

    @Override
    public void run(String... args) throws Exception {
        while(isRunning) {
            showMenu();
        }
    }

    private void showMenu() {

        System.out.println("""
                 1. View all products
                 2. Find product by id
                 3. Find product by name
                 4. Find Product by category
                 5. Add product
                 6. Update product price
                 0. Exit
                """
        );
        int select = scan.nextInt();
        scan.nextInt();
        switch (select) {
            case 0 -> isRunning = false;
            case 1 -> findAllProducts();
            case 2 -> findProductById();
            case 3 -> findProductByName();
            case 4 -> findProductByCategory();
            case 5 -> addProduct();
            case 6 -> updateProductPrice();
        }
    }

    private void findAllProducts() {
        System.out.println("All products : \n");
        System.out.println(client.getAllProducts());
        scan.nextLine();
    }

    private void findProductById() {
        System.out.println("Id of the product your looking for: ");
        int id = scan.nextInt();

        try {
            String product = client.findProductById(id);
            System.out.println(product);
        } catch (Exception e) {
            System.out.println("Product cannot be identified");
        }
        scan.nextLine();
    }


    private void findProductByName() {
        System.out.println("Name of the product your looking for: ");
        String name = scan.nextLine();

        try {
            List<Product> products = client.findProductByName(name);
            System.out.println(products);
        } catch (Exception e) {
            System.out.println("No products found");
        }
        scan.nextLine();
    }

    private void findProductByCategory() {
        Category category = category();
        System.out.println("Products in category:" + category + "\n");
        System.out.println(client.findProductByCategory(category));
        scan.nextLine();
    }
    private Category category() {
        while (true) {
            System.out.println("Product Categories:");
            Category[] categories = Category.values();
            Arrays.stream(categories)
                    .forEach(category -> System.out.println((Arrays.asList(categories)
                            .indexOf(category) + 1) + ": " + category.toString()));

            int i = scan.nextInt() - 1;
            scan.nextLine();

            try {
                return categories[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please pick a valid category");
            }
        }

    }

    private void addProduct() {
        Category category = category();
        System.out.println(category);
        System.out.println("Product name");
        String name = scan.nextLine();
        System.out.println("Product quantity");
        int quantity = scan.nextInt();
        BigDecimal price;
        while (true) {
            try {
                System.out.println("Price: ");
                price = scan.nextBigDecimal();
                break;
            } catch (Exception e) {
                System.out.println("Cannot assign price");
            }
        }
        String product = client.addProduct(new Product(name, price, quantity, category));
        System.out.println("Product successfully added" + product);
    }

    private void updateProductPrice() {
        System.out.println("Product id: ");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.println("Product price: ");
        int price = scan.nextInt();
        scan.nextLine();
        try {
            System.out.println(client.updateProductPrice(id, price));
        } catch (Exception e) {
            System.out.println("Cant find product and update its price");
        }
        scan.nextLine();
    }

}
