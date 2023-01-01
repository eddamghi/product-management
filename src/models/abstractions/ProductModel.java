package models.abstractions;

import persistence.entities.Product;

import java.util.List;

public interface ProductModel {
    Product addProduct(Product product);
    boolean delete(Product product);
    Product update(Product product);
    Product getById(int id);
    List<Product> getByKeyword(String keyword);
    List<Product> getAll();

}
