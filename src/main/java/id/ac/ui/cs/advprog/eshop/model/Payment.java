package id.ac.ui.cs.advprog.eshop.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    Order order;
    String status;
    
    public Payment(String id, String method, Map<String, String> paymentData, Order order){
        this.id = id;
        this.method = method;
        this.status = null;
        
        if (paymentData == null || paymentData.isEmpty()){
            throw new IllegalArgumentException("PaymentData is Empty or null");
        } else {
            this.paymentData = paymentData;
        }

        if (order == null) {
            throw new IllegalArgumentException("Order must have at least one product");
        } else {
            this.order = order;
        }
    }

    public Payment(String id, String method, Map<String, String> paymentData, Order order, String status){
        this(id, method, paymentData, order);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        String[] statusList = {"SUCCESS", "REJECTED"};
        if (Arrays.stream(statusList).noneMatch(item -> item.equals(status))) {
            throw new IllegalArgumentException();
        } else {
            this.status = status;
        }
    }

}
