package business;

import java.util.Scanner;

public interface ProductBusiness {
    void findAll();

    void createProduct(Scanner scanner);

    void updateProduct(Scanner scanner);

    void deleteProduct(Scanner scanner);

    void searchProductByName(Scanner scanner);

    void sortProductByPriceASC();

    void statitisProductByCatalog();
}
