package com.example.webshopclient;

import com.example.webshopclient.shop.Category;
import com.example.webshopclient.shop.Product;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RestClient {

    WebClient client = WebClient.create("https://localhost:8082/rest");

    public List<Product> getAllProducts() {
        Flux<Product> flux = client
                .get()
                .uri("all-products")
                .retrieve()
                .bodyToFlux(Product.class);
        return flux.collectList().block();
    }

    public String findProductById(int id) {
        Mono<String> mono = client
                .get()
                .uri("get-by-id/", id)
                .retrieve()
                .bodyToMono(String.class);
        return mono.block();
    }

    public List<Product> findProductByName(String name) {
        Flux<Product> flux = client
                .get()
                .uri("get-by-name/", name)
                .retrieve()
                .bodyToFlux(Product.class);
        return flux.collectList().block();
    }

    public List<Product> findProductByCategory(Category category) {
        Flux<Product> flux = client
                .get()
                .uri("get-by-category/{category}" + category)
                .retrieve()
                .bodyToFlux(Product.class);
        return flux.collectList().block();
    }

    public String addProduct(Product product) {
        Mono<String> mono = client
                .post()
                .uri("add-Product")
                .bodyValue(product)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
        return mono.block();
    }

    public String updateProductPrice(int id, int price) {
        Mono<String> mono = client
                .put()
                .uri("update-price/{id}/{price}", id, price)
                .retrieve()
                .bodyToMono(String.class);
        return mono.block();
    }

}
