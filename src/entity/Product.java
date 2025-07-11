package entity;

import dao.ProductDAO;
import dao.imp.ProductDAOImp;
import validate.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Product {
    private int productId;
    private String productName;
    private float price;
    private String title;
    private LocalDate created;
    private String catalog;
    private boolean status;

    private final ProductDAO productDAO;


    public Product() {
        productDAO = new ProductDAOImp();
    }

    public Product(int productId, String productName, float price, String title, LocalDate created, String catalog, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.title = title;
        this.created = created;
        this.catalog = catalog;
        this.status = status;
        productDAO = new ProductDAOImp();
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner) {
        this.productName = inputProductName(scanner);
        System.out.println("Nhập vào giá sản phẩm:");
        this.price = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập vào tiêu đề sản phẩm:");
        this.title = scanner.nextLine();
        System.out.println("Nhập vào ngày tạo sản phẩm:");
        this.created = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("Nhập vào danh mục sản phẩm:");
        this.catalog = scanner.nextLine();
        this.status = true;
    }

    public String inputProductName(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm:");
        do {
            String productName = scanner.nextLine();
            if (Validator.isEmpty(productName)) {
                System.err.println("Vui lòng nhập tên sản phẩm");
            } else {
                if (productDAO.isNameExist(productName)) {
                    System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại");
                } else {
                    return productName;
                }
            }
        } while (true);
    }

    @Override
    public String toString() {
        return String.format("Mã SP: %d - Tên SP: %s - Giá: %f - Ngày tạo: %s\n" +
                        "Tiêu đề: %s - Danh mục: %s - Trạng thái: %s\n",
                this.productId, this.productName, this.price, this.created, this.title, this.catalog, this.status);
    }
}
