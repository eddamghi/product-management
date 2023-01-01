package persistence;

import persistence.entities.Product;

import java.util.List;

public interface ProductDao extends Dao<Product> {
    List<Product> findByKeyword(String keyword);
}

