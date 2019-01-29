import builder.MessageBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.InvalidOperationException;
import exceptions.InvalidSaleException;
import org.apache.commons.io.FileUtils;
import representation.*;
import store.Sales;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Message> messages;
        ObjectMapper mapper = new ObjectMapper();


        try {

            String str = FileUtils.readFileToString(new File("C:\\Users\\mattf\\IdeaProjects\\jp-tech-test" +
                    "\\jp-morgan-tech-test\\src\\resources\\messages.json"));
            System.out.println(str);

            messages = mapper.readValue(str, new TypeReference<List<Message>>(){});
            Sales sales = new Sales();
            int messageCount = 0;
            for (Message msg : messages) {
                if (messageCount % 10 == 0) {
                    //TODO
                    // run report

                }

                if (messageCount == 50) {
                    //TODO
                    // run report
                    break;
                }

                Sale sale = MessageBuilder.buildMessage(msg);
                sales.addSaleForType(sale, msg.getMessageType());
                messageCount++;
            }

        } catch (IOException ex) {
            System.out.println("Unable to parse messages");

        } catch (InvalidOperationException ex) {
            System.out.println("Adjustment message failed as: " + ex);

        } catch (InvalidSaleException ex) {
            System.out.println("Persisting sale failed as: " + ex);
        }


    }


}

