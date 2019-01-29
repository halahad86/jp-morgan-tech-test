package builder;

import exceptions.InvalidOperationException;
import exceptions.InvalidSaleException;
import representation.*;
import utils.OperationType;

public class MessageBuilder {

    public static Sale buildMessage(Message message) throws InvalidOperationException, InvalidSaleException {
        if (message == null) {
            throw new InvalidSaleException("Message is null");
        }

        Product product = new Product(message.getProduct());
        Double value = Double.parseDouble(message.getValue());

        if (message.getQuantity() != null) {
            return buildQuantitySaleMessage(message, product, value);

        } else if (message.getAdjustment() != null) {
            return buildAdjustmentSaleMessage(message, product, value);
        } else {
            return buildSaleMessage(product, value);
        }
    }


    private static Sale buildSaleMessage(Product product, Double value) {
        return new Sale(product, value);
    }

    private static QuantitySale buildQuantitySaleMessage(Message message, Product product, Double value) {
        return new QuantitySale(product, value, Integer.parseInt(message.getQuantity()));
    }

    private static AdjustmentSale buildAdjustmentSaleMessage(Message message, Product product, Double value)
            throws InvalidOperationException {

        return new AdjustmentSale(product, value,
                determineOperation(message.getAdjustment(), message.getAdjustment_value()));
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

        Double value = Double.parseDouble(adjustmentValue);
        operation.setValue(value);

        return operation;
    }
}

