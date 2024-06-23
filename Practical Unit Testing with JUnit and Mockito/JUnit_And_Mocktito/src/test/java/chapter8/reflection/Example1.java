package chapter8.reflection;

import chapter8.service.ProductService;

public class Example1 {

    public static void main(String[] args) {

        ProductService productService = new ProductService();
        productService.addProduct();
    }
}
