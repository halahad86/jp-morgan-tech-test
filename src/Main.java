import representation.*;
import store.Sales;

import java.math.BigDecimal;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        BigDecimal saleValue = new BigDecimal(100);
        Product sampleProduct  = new Product();
        sampleProduct.setType("Apple");

        Sale sale = new Sale(sampleProduct, saleValue);

        //Create new store for sales

        Sales store = new Sales();

        store.addNewSale(sale);

    }
}
