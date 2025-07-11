package dao;

import entity.Product;
import entity.StatictisProduct;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll();

    boolean isNameExist(String productName);

    boolean save(Product product);

    Product findById(int productId);

    boolean update(Product product);

    boolean delete(int productId);

    List<Product> searchByName(String productName);

    List<StatictisProduct> listStatiticProducts();
}
