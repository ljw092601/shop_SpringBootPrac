package com.example.shop.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    private String name;
    private Integer price;
    private String category;
    private String image;
}
