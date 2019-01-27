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


    public Map<Product ,List<Sale>> getTotalSales() {
        return totalSales;
    }


    public void addNewSale (Sale sale) {
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

    public void addNewSale (QuantitySale sale) {
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

    public void addNewSale (AdjustmentSale sale) throws InvalidSaleException{
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
               sale.setValue(sale.getValue().add(operation.getValue().getValue()));
           }

        } else if (operation.getOperationType().equals(OperationType.SUBTRACT)) {
            for (Sale sale : totalSales.get(product)) {
                sale.setValue(sale.getValue().subtract(operation.getValue().getValue()));
            }


        } else if (operation.getOperationType().equals(OperationType.MULTIPLY)) {
            for (Sale sale : totalSales.get(product)) {
                sale.setValue(sale.getValue().multiply(operation.getValue().getValue()));
            }
        }
    }

}
