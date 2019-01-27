package representation;

import java.math.BigDecimal;

/* This class represents Message Type 2 */
public class QuantitySale extends Sale {

    private int amount;

    public QuantitySale(Product product, BigDecimal value, int amount) {
        super(product, value);
        this.amount = amount;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
