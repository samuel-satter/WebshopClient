package com.example.webshopclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    public long id;

    public String name;

    public BigDecimal price;

    public Category category;

}
