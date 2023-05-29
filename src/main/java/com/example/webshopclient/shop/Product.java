package com.example.webshopclient.shop;

import com.example.webshopclient.shop.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {


    public String name;

    public BigDecimal price;

    public int quantity;

    public Category category;

}
