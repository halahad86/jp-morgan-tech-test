package prog.store;

import prog.exceptions.InvalidOperationException;
import prog.exceptions.InvalidSaleException;
import prog.exceptions.NoSuchProductException;
import prog.exceptions.ProductAlreadyExistsException;
import prog.representation.*;
import prog.utils.OperationType;

import java.util.*;

public class Sales {

    private Map<String,List<Sale>> totalSales;

    public Sales() {
        this.totalSales = new HashMap<>();
    }

    public Map<String,List<Sale>> getTotalSales() {
        return totalSales;
    }

    public List<Sale> getListOfProductSales(String product) throws NoSuchProductException{
        if(!totalSales.containsKey(product)) {
            throw new NoSuchProductException("No such product in sales");
        }
            return totalSales.get(product);
    }

    public void addSaleForType(Sale sale, String messageType) throws InvalidSaleException {
        if (messageType == null || messageType.equals("")) {
            throw new InvalidSaleException("Message Type null or empty");
        }
        switch (messageType) {
            case "sale" :addNewSale(sale);
                break;
            case "quantity" : addNewSale(sale);
                break;
            case "adjustment" : addNewSale((AdjustmentSale) sale);
        }

    }

    private void addNewSale (Sale sale) {
        if(sale.getProduct() != null) {
            try {
                addNewProduct(sale.getProduct());

            } catch (ProductAlreadyExistsException e) {
                // could do something specific here
            } finally {
                totalSales.get(sale.getProduct()).add(sale);
            }
        }
    }

    private void addNewSale (AdjustmentSale sale) throws InvalidSaleException{
        if(sale.getProduct() != null) {
            try {
                addNewProduct(sale.getProduct());

            } catch (ProductAlreadyExistsException e) {
                // could do something specific here
            }

            totalSales.get(sale.getProduct()).add(sale);

            try {
                updateProductForOperation(sale.getProduct(), sale.getOperation());

            } catch (InvalidOperationException e) {
               throw new InvalidSaleException(e.getMessage());
            }
        }


    }


    private void addNewProduct(String product) throws ProductAlreadyExistsException {
        if (totalSales == null || totalSales.containsKey(product)) {
            throw new ProductAlreadyExistsException("This product already exists");
        }

        totalSales.put(product, new ArrayList<>());
    }


    private void updateProductForOperation(String product, Operation operation) throws InvalidOperationException{
        if (operation == null) {
            throw new InvalidOperationException("Invalid operation provided");
        }

        if (operation.getOperationType().equals(OperationType.ADD)){
           for (Sale sale : totalSales.get(product)) {
               sale.setValue(sale.getValue() + operation.getValue());
           }

        } else if (operation.getOperationType().equals(OperationType.SUBTRACT)) {
            for (Sale sale : totalSales.get(product)) {
                sale.setValue(sale.getValue() - operation.getValue());
            }


        } else if (operation.getOperationType().equals(OperationType.MULTIPLY)) {
            for (Sale sale : totalSales.get(product)) {
                sale.setValue(sale.getValue() * operation.getValue());
            }
        }
    }

}
