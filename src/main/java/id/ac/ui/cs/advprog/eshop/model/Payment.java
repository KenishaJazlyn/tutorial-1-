package id.ac.ui.cs.advprog.eshop.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;


@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    Order order;
    String status;
    
    public Payment(String id, String method, Map<String, String> paymentData, Order order){
        this.id = id;
        this.setMethod(method);
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

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)){
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid Payment Status");
        }
    }
    public void setMethod(String method) {
        if (PaymentMethod.contains(method)) {
            this.method = method;
        } else {
            throw new IllegalArgumentException("Invalid Payment ethod");
        }
    }

}
