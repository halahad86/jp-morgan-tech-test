package store;

import exceptions.*;
import representation.*;
import utils.*;

import java.util.*;

public class Sales {

    private Map<Product ,List<Sale>> totalSales;


    public Sales() {
        this.totalSales = new HashMap<>();
    }


    public void addSaleForType(Sale sale, String messageType) throws InvalidSaleException {
        switch (messageType) {
            case "sale" :addNewSale(sale);
                break;
            case "quantity" : addNewSale((QuantitySale) sale);
                break;
            case "adjustment" : addNewSale((AdjustmentSale) sale);
        }

    }

    private void addNewSale (Sale sale) {
        System.out.println("Adding Sale");
        if(sale.getProduct() != null) {
            try {
                addNewProduct(sale.getProduct());

            } catch (ProductAlreadyExistsException e) {
                System.out.println("Not adding new Product, already exists");

            } finally {
                totalSales.get(sale.getProduct()).add(sale);
            }
        }
    }

    private void addNewSale (QuantitySale sale) {
        System.out.println("Adding Quantity Sale");
        if(sale.getProduct() != null) {
            try {
                addNewProduct(sale.getProduct());

            } catch (ProductAlreadyExistsException e) {
                System.out.println("Not adding new Product, already exists");

            } finally {
                totalSales.get(sale.getProduct()).add(sale);
            }
        }

    }

    private void addNewSale (AdjustmentSale sale) throws InvalidSaleException{
        System.out.println("Adding Adjustment Sale");
        if(sale.getProduct() != null) {
            try {
                addNewProduct(sale.getProduct());

            } catch (ProductAlreadyExistsException e) {
                System.out.println("Not adding new Product, already exists");
            }

            totalSales.get(sale.getProduct()).add(sale);

            try {
                updateProductForOperation(sale.getProduct(), sale.getOperation());

            } catch (InvalidOperationException e) {
               throw new InvalidSaleException(e.getMessage());
            }
        }


    }


    private void addNewProduct(Product product) throws ProductAlreadyExistsException {
        if (totalSales == null || totalSales.containsKey(product)) {
            throw new ProductAlreadyExistsException("This product already exists");
        }

        totalSales.put(product, new ArrayList<>());
    }


    private void updateProductForOperation(Product product, Operation operation) throws InvalidOperationException{
        if (operation == null) {
            throw new InvalidOperationException("Invalid utils");
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
