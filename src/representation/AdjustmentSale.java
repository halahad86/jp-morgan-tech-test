package representation;

/* This class represents Message Type 3 */
public class AdjustmentSale extends Sale {

    private Operation operation;

    public AdjustmentSale(Product product, Double value, Operation operation) {
        super(product, value);
        this.operation = operation;
    }


    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

}
