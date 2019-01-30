package prog.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {


    @JsonProperty("message_type")
    private String messageType;

    @JsonProperty("product_type")
    private String product;

    @JsonProperty("value")
    private String value;

    @JsonProperty("quantity")
    private String quantity;

    @JsonProperty("adjustment")
    private String adjustment;

    @JsonProperty("adjustment_value")
    private String adjustment_value;



    public String getMessageType() { return messageType; }


    public String getProduct() {
        return product;
    }


    public String getValue() {
        return value;
    }


    public String getQuantity() {
        return quantity;
    }


    public String getAdjustment() {
        return adjustment;
    }


    public String getAdjustment_value() {
        return adjustment_value;
    }



}
