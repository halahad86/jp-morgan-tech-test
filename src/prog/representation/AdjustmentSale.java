package prog.representation;

/* This class represents Message Type 3 */
public class AdjustmentSale extends Sale {

    private Operation operation;

    public AdjustmentSale(String product, Double value, Operation operation) {
        super(product, value, 1);
        this.operation = operation;
    }


    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

}
