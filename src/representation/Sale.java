package representation;

/* This class represents Message Type 1 */
public class Sale {

    private Product product;

    private Double value;


    public Sale(Product product, Double value) {
        this.product = product;
        this.value = value;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }



}
