package persistence;

import persistence.entities.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ProductImpl implements ProductDao {
    @Override
    public List<Product> findAll() {
        Connection connection = SingletonConnectionDatabase.getConnection();
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Product");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product p = new Product();
                p.setId(resultSet.getInt("id"));
                p.setName(resultSet.getString("name"));
                p.setPrice(resultSet.getFloat("price"));
                p.setQuantity_in_stock(resultSet.getInt("quantity_in_stock"));
                products.add(p);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findById(int id) {
        Connection connection = SingletonConnectionDatabase.getConnection();
        Product p = new Product();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM product WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                p.setId(resultSet.getInt("id"));
                p.setName(resultSet.getString("name"));
                p.setPrice(resultSet.getFloat("price"));
                p.setQuantity_in_stock(resultSet.getInt("quantity_in_stock"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public Product save(Product product) {
        Connection connection =  SingletonConnectionDatabase.getConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement(
                            "INSERT INTO Product(name, price,  quantity_in_stock)" + " VALUES(?, ?, ?)"
                    );
            statement.setString(1, product.getName());
            statement.setFloat(2, product.getPrice());
            statement.setInt(3, product.getQuantity_in_stock());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean delete(Product product) {
        try {
            Connection connection = SingletonConnectionDatabase.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE id = ?");
            statement.setInt(1, product.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            return false;
        }
        return true;
    }

    @Override
    public Product update(Product product) {
        Connection connection = SingletonConnectionDatabase.getConnection();
        try {
            var statement = connection.prepareStatement(
                    "UPDATE product SET name = ?, price = ?, quantity_in_stock = ? WHERE id = ?"
            );
            ((PreparedStatement) statement).setString(1, product.getName());
            statement.setFloat(2, product.getPrice());
            statement.setInt(3, product.getQuantity_in_stock());
            statement.setInt(4, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> findByKeyword(String keyword) {
        Connection connection = SingletonConnectionDatabase.getConnection();
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM Product WHERE name LIKE '%" + keyword + "%'");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product p = new Product();
                p.setId(resultSet.getInt("id"));
                p.setName(resultSet.getString("name"));
                p.setPrice(resultSet.getFloat("price"));
                p.setQuantity_in_stock(resultSet.getInt("quantity_in_stock"));
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}

