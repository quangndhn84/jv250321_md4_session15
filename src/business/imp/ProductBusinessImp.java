package business.imp;

import business.ProductBusiness;
import dao.ProductDAO;
import dao.imp.ProductDAOImp;
import entity.Product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ProductBusinessImp implements ProductBusiness {
    //DI - Dependecy Injection - tiêm sự phụ thuộc
    private final ProductDAO productDAO;

    //Tiêm phụ thuộc cho constructor
    public ProductBusinessImp() {
        productDAO = new ProductDAOImp();
    }

    @Override
    public void findAll() {
        List<Product> listProducts = productDAO.findAll();
        listProducts.forEach(System.out::println);
    }

    @Override
    public void createProduct(Scanner scanner) {
        Product product = new Product();
        product.inputData(scanner);
        boolean result = productDAO.save(product);
        if (result) {
            System.out.println("Thêm mới thành công");
        } else {
            System.err.println("Có lỗi trong quá trình thêm mới sản phẩm");
        }
    }

    @Override
    public void updateProduct(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm cần cập nhật:");
        int productId = Integer.parseInt(scanner.nextLine());
        Product product = productDAO.findById(productId);
        if (product != null) {
            boolean isNotExit = true;
            do {
                System.out.println("1. Cập nhật tên sản phẩm");
                System.out.println("2. Cập nhật giá sản phẩm");
                System.out.println("3. Cập nhật tiêu đề");
                System.out.println("4. Cập nhật ngày tạo");
                System.out.println("5. Cập nhật danh mục sản phẩm");
                System.out.println("6. Cập nhật trạng thái sản phẩm");
                System.out.println("7. Thoát");
                System.out.print("Lựa chọn của bạn: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Nhập vào tên mới sản phẩm:");
                        product.setProductName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Nhập vào giá mới sản phẩm:");
                        product.setPrice(Float.parseFloat(scanner.nextLine()));
                        break;
                    case 3:
                        System.out.println("Nhập vào tiêu đề mới của sản phẩm:");
                        product.setTitle(scanner.nextLine());
                        break;
                    case 4:
                        System.out.println("Nhập vào ngày tạo mới của sản phẩm:");
                        product.setCreated(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        break;
                    case 5:
                        System.out.println("Nhập vào danh mục mới của sản phẩm:");
                        product.setCatalog(scanner.nextLine());
                        break;
                    case 6:
                        System.out.println("Nhập vào trạng thái mới của sản phẩm:");
                        product.setStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    case 7:
                        isNotExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-7");
                }

            } while (isNotExit);
            //Thực hiện cập nhật vào database
            boolean result = productDAO.update(product);
            if (result) {
                System.out.println("Cập nhật thành công");
            } else {
                System.err.println("Có lỗi trong quá trình cập nhật sản phẩm");
            }
        } else {
            System.err.println("Mã sản phẩm không tồn tại");
        }
    }

    @Override
    public void deleteProduct(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm cần xóa:");
        int productId = Integer.parseInt(scanner.nextLine());
        Product product = productDAO.findById(productId);
        if (product != null) {
            boolean result = productDAO.delete(productId);
            if (result) {
                System.out.println("Xóa thành công");
            } else {
                System.err.println("Có lỗi trong quá trình xóa sản phẩm");
            }
        } else {
            System.err.println("Mã sản phẩm khng tồn tại");
        }
    }

    @Override
    public void searchProductByName(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm cần tìm:");
        String productName = scanner.nextLine();
        List<Product> listProducts = productDAO.searchByName(productName);
        if (listProducts == null) {
            System.out.println("Không tìm thấy sản phẩm thỏa mãn điều kiện tìm kiếm");
        } else {
            listProducts.forEach(System.out::println);
        }
    }

    @Override
    public void sortProductByPriceASC() {
        productDAO.findAll().stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .forEach(System.out::println);
    }

    @Override
    public void statitisProductByCatalog() {
        productDAO.listStatiticProducts().forEach(System.out::println);
    }
}
