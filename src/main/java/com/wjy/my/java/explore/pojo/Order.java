package com.wjy.my.java.explore.pojo;

public class Order {

    private String id;

    private Order() {
        System.out.println("Order constructor non param");
    }

    private Order(String id) {
        this.id = id;
        System.out.println("Order constructor 1 param");
    }

    public String getId() {
        return this.id;
    }

}
