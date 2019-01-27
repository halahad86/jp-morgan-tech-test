package representation;

import utils.Denomination;

import java.math.BigDecimal;

public class Value {

    private BigDecimal amount;

    private Denomination denomination;


    public Value(BigDecimal amount, Denomination denomination) {
        this.amount = amount;
        this.denomination = denomination;

    }

    public BigDecimal getValue() {

        if (denomination.equals(Denomination.PENCE)) {
            return amount.multiply(BigDecimal.valueOf(100));

        } else {
            return amount;
        }
    }

    public void setValue(BigDecimal amount) {
        this.amount = amount;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public void setDenomination(Denomination denomination) {
        this.denomination = denomination;
    }



}
