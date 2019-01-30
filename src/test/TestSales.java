package test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import prog.builder.MessageBuilder;
import prog.exceptions.InvalidOperationException;
import prog.exceptions.InvalidSaleException;
import prog.exceptions.NoSuchProductException;
import prog.representation.Message;
import prog.representation.Sale;
import prog.store.Sales;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSales {

    private static List<Message> messages;
    private Sales sales = new Sales();

    @BeforeClass
    public static void setUpBeforeClass() {
        ObjectMapper mapper = new ObjectMapper();

        String str;

        {
            try {
                str = FileUtils.readFileToString(new File("src\\test\\resources\\messages.json").getAbsoluteFile());

                messages = mapper.readValue(str, new TypeReference<List<Message>>(){});


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    @Test(expected = IOException.class)
    public void testReadFile() throws IOException {
        File file = new File("incorrectPath.json");
        FileUtils.readFileToString(file);
    }


    @Test
    public void testMultiplyAdjustment() throws InvalidOperationException, InvalidSaleException, NoSuchProductException {
        Message message = messages.get(2);
        Sale sale = MessageBuilder.buildMessage(message);
        sales.addSaleForType(sale,message.getMessageType());

        List<Sale> salesList = sales.getListOfProductSales(message.getProduct());
        Double result = salesList.get(0).getValue();
        Double expected  = 0.2;
        assertEquals(expected, result);

    }

    @Test
    public void testSubtractAdjustment() throws InvalidOperationException, InvalidSaleException, NoSuchProductException {
        Message message = messages.get(1);
        Sale sale = MessageBuilder.buildMessage(message);
        sales.addSaleForType(sale,message.getMessageType());

        List<Sale> salesList = sales.getListOfProductSales(message.getProduct());
        Double result = salesList.get(0).getValue();
        Double expected  = 0.05;
        assertEquals(expected, result);

    }

    @Test
    public void testAddAdjustment() throws InvalidOperationException, InvalidSaleException, NoSuchProductException {
        Message message = messages.get(2);
        Sale sale = MessageBuilder.buildMessage(message);
        sales.addSaleForType(sale,message.getMessageType());

        List<Sale> salesList = sales.getListOfProductSales(message.getProduct());
        Double result = salesList.get(0).getValue();
        Double expected  = 0.2;
        assertEquals(expected, result);

    }
}
