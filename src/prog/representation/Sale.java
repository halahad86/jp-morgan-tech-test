package prog.representation;

/* This class represents Message Type 1 */
public class Sale {

    private String product;

    private Double value;

    private int quantity;


    public Sale(String product, Double value ,int quantity) {
        this.product = product;
        this.value = value;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
