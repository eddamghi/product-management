package models;

import lombok.RequiredArgsConstructor;
import persistence.ProductDao;
import persistence.entities.Product;

import java.util.List;

@RequiredArgsConstructor
public class ProductModel implements models.abstractions.ProductModel {
    private final ProductDao dao;
    @Override
    public Product addProduct(Product product) {
        return dao.save(product);
    }

    @Override
    public boolean delete(Product product) {
        return dao.delete(product);
    }

    @Override
    public Product update(Product product) {
        return dao.update(product);
    }

    @Override
    public Product getById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Product> getByKeyword(String keyword) {
        return dao.findByKeyword(keyword);
    }

    @Override
    public List<Product> getAll() {
        return dao.findAll();
    }
}
