package com.capitole.ecommerce.price.model.brand;

public class Brand {
    private long id;
    private String name;

    public Brand() {
    }

    public Brand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
