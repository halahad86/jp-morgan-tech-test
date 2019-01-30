package prog;

import prog.builder.MessageBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import prog.exceptions.InvalidOperationException;
import prog.exceptions.InvalidSaleException;
import org.apache.commons.io.FileUtils;
import prog.exceptions.NoSuchProductException;
import prog.representation.AdjustmentSale;
import prog.representation.Message;
import prog.representation.Sale;
import prog.store.Sales;
import prog.store.SalesReport;

import java.io.*;
import java.util.List;
import java.util.Map;

public class MessageProcessor {

    public static void main(String[] args) {

        List<Message> messages;
        ObjectMapper mapper = new ObjectMapper();

        try {

            String str = FileUtils.readFileToString((new File("src\\resources\\messages.json").getAbsoluteFile()));

            messages = mapper.readValue(str, new TypeReference<List<Message>>(){});

            Sales sales = new Sales();
            SalesReport salesReport = new SalesReport();
            int messageCount = 0;
            for (Message msg : messages) {

                messageCount ++;

                if ((messageCount % 10) == 0) {
                    System.out.println("########################## 10th Sale Report #################################\n");
                    for (Map.Entry<String, List<Sale>> entry : sales.getTotalSales().entrySet()) {
                        String product = entry.getKey();
                        List<Sale> salesList = sales.getListOfProductSales(product);
                        System.out.println(salesReport.productReport(product, salesList));
                    }
                }

                if (messageCount == 50) {
                    try {
                        System.out.println("PAUSING\n");
                        System.out.println("########################## 50th Sale Report #################################\n");

                        List<AdjustmentSale> adjustmentSales = MessageBuilder.getAdjustmentsList();
                        System.out.println(salesReport.adjustmentReport(adjustmentSales));

                        Thread.sleep(10000);

                    } catch (InterruptedException e) {
                        System.out.println("Unable to pause application - continuing");
                    }
                }

                // Unmarshalling message. Adding the sale to store
                Sale sale = MessageBuilder.buildMessage(msg);
                sales.addSaleForType(sale, msg.getMessageType());
            }

        } catch (IOException ex) {
            System.out.println("Unable to parse messages");

        } catch (InvalidOperationException ex) {
            System.out.println("Adjustment message failed as: " + ex);

        } catch (InvalidSaleException ex) {
            System.out.println("Persisting sale failed as: " + ex);

        } catch (NoSuchProductException ex) {
            System.out.println("Unable to publish report as: " + ex);
        }

    }
}

