package prog.representation;

import prog.utils.OperationType;

public class Operation {

    private OperationType operationType;

    private Double value;

    public Operation() {

    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }


    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

}
