package com.example.spring_mvc_lab.controller;

import com.example.spring_mvc_lab.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticsController {

    private List<Product> products = new ArrayList<>();

    public List<Product> findAll() {
        return products;
    }

    public Map<String, Long> countByCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
    }

    public Product getMostExpensive() {
        return products.stream()
                .max(Comparator.comparing(Product::getPrice))
                .orElse(null);
    }

    public Product getCheapest() {
        return products.stream()
                .min(Comparator.comparing(Product::getPrice))
                .orElse(null);
    }

    public double getAveragePrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0);
    }

    public long countLowStock() {
        return products.stream()
                .filter(p -> p.getStock() < 5)
                .count();
    }
}