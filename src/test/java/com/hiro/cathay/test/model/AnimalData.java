package com.hiro.cathay.test.model;

import lombok.Data;

import java.util.Map;

@Data
public class AnimalData {

    private String name;

    private int count;

    public AnimalData(Map<String, String> animal) {
        this.name = animal.get("name");
        this.count = Integer.parseInt(animal.get("count"));
    }
}
