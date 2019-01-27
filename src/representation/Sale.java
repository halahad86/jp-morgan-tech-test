package representation;

import java.math.BigDecimal;

/* This class represents Message Type 1 */
public class Sale {

    private Product product;

    private BigDecimal value;


    public Sale(Product product, BigDecimal value) {
        this.product = product;
        this.value = value;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }



}
