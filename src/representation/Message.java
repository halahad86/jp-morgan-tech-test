package representation;

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

    public void setMessageType(String messageType) { this.messageType = messageType; }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) { this.value = value; }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(String adjustment) {
        this.adjustment = adjustment;
    }

    public String getAdjustment_value() {
        return adjustment_value;
    }

    public void setAdjustment_value(String adjustment_value) {
        this.adjustment_value = adjustment_value;
    }


}
