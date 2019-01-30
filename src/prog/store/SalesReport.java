package prog.store;

import prog.representation.AdjustmentSale;
import prog.representation.Sale;

import java.util.List;

public class SalesReport {

    public String productReport(String product, List<Sale> salesList) {
        int totalQuantity = 0;
        double totalValue = 0;
        for (Sale sale : salesList) {
            totalQuantity += sale.getQuantity();
            totalValue += sale.getValue();

        }
        return "Product: " + product + ", Total Sales: " + totalQuantity
                + ", Total Value: " + totalValue + "\n";
    }

    public String adjustmentReport(List<AdjustmentSale> adjustmentsList) {
        String report = "Adjustments made:\n";
        for (AdjustmentSale adjustmentSale : adjustmentsList) {

            report += "Product: " + adjustmentSale.getProduct() + ", Type: "
                    + adjustmentSale.getOperation().getOperationType().toString()
                    + ", Value: " + adjustmentSale.getOperation().getValue() + "\n";

        }
        return report;
    }

}


