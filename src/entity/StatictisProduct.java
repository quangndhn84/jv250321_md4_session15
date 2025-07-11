package entity;

public class StatictisProduct {
    private String catalog;
    private int countProduct;

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public int getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }

    @Override
    public String toString() {
        return String.format("Danh mục: %s - Số lượng sản phẩm: %d", this.catalog, this.countProduct);
    }
}
