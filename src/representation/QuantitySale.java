package representation;

/* This class represents Message Type 2 */
public class QuantitySale extends Sale {

    private int quantity;

    public QuantitySale(Product product, Double value, int quantity) {
        super(product, value);
        this.quantity = quantity;
    }


    public int getAmount() {
        return quantity;
    }

    public void setAmount(int quantity) {
        this.quantity = quantity;
    }


}
