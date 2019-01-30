package prog.builder;

import prog.exceptions.InvalidOperationException;
import prog.exceptions.InvalidSaleException;
import prog.representation.*;

import prog.utils.OperationType;

import java.util.ArrayList;
import java.util.List;

public class MessageBuilder {

    private static List<AdjustmentSale> adjustmentsList = new ArrayList<>();

    public static List<AdjustmentSale> getAdjustmentsList() {
        return adjustmentsList;
    }

    public static Sale buildMessage(Message message) throws InvalidOperationException, InvalidSaleException {
        if (message == null) {
            throw new InvalidSaleException("Message is null");
        }

        String product = message.getProduct();
        double value = Double.parseDouble(message.getValue());

        if (message.getQuantity() != null) {
            return buildSaleWithQuantityMessage(message, product, value);

        } else if (message.getAdjustment() != null) {
            return buildAdjustmentSaleMessage(message, product, value);
        } else {
            return buildSaleMessage(product, value);
        }
    }

    private static void addAdjustmentToTracker (AdjustmentSale adjustmentSale) {
        adjustmentsList.add(adjustmentSale);
    }

    private static Sale buildSaleMessage(String product, Double value) {
        return new Sale(product, value, 1);
    }

    private static Sale buildSaleWithQuantityMessage(Message message, String product, Double value) {
        return new Sale(product, value, Integer.parseInt(message.getQuantity()));
    }

    private static AdjustmentSale buildAdjustmentSaleMessage(Message message, String product, Double value)
            throws InvalidOperationException {
        AdjustmentSale adjustmentSale = new AdjustmentSale(product, value,
                determineOperation(message.getAdjustment(), message.getAdjustment_value()));
        addAdjustmentToTracker(adjustmentSale);
        return adjustmentSale;
    }


    private static Operation determineOperation(String adjustment, String adjustmentValue) throws InvalidOperationException{
        Operation operation = new Operation();

        switch(adjustment) {
            case "add":
                operation.setOperationType(OperationType.ADD);
                break;
            case "subtract":
                operation.setOperationType(OperationType.SUBTRACT);
                break;
            case "multiply":
                operation.setOperationType(OperationType.MULTIPLY);
                break;
            default: throw new InvalidOperationException("Unable to parse operation type, unknown operation");
        }

        double value = Double.parseDouble(adjustmentValue);
        operation.setValue(value);

        return operation;
    }
}

