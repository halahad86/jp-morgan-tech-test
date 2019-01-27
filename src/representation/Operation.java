package representation;

import utils.OperationType;

public class Operation {

    private OperationType operationType;

    private Value value;


    public Operation(OperationType operationType, Value value) {
        this.operationType = operationType;
        this.value = value;

    }


    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }


    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

}
