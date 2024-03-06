package id.ac.ui.cs.advprog.eshop.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    Order order;
    String status;
    
    public Payment(String id, String method, Map<String, String> paymentData, Order order){
        this.id = id;
        this.setMethod(method);
        
        
        if (paymentData == null || paymentData.isEmpty()){
            throw new IllegalArgumentException("PaymentData is Empty or null");
        } else {
            this.paymentData = paymentData;
        }

        this.setStatus();

        if (order == null) {
            throw new IllegalArgumentException("Order must have at least one product");
        } else {
            this.order = order;
        }
    }

    public void setStatus() {
        if (this.method.equals("BANK_TRANSFER")){
            if (isValidBankTransfer()){
                this.status = "SUCCESS";
            } else {
                this.status = "REJECTED";
            }     
        } else {
            if (paymentData.containsKey("voucherCode")){
                if (isValidVoucherCode(paymentData.get("voucherCode"))){
                    this.status = "SUCCESS";
                } else {
                    this.status = "REJECTED";
                }     
            } else {
                this.status = "REJECTED";
            }
           
        }
    }
    public boolean isValidBankTransfer() {
        if (this.paymentData.containsKey("bankName") && 
            this.paymentData.containsKey("referenceCode") ){
                if (paymentData.get("bankName")!= null){
                    return true;
                } else {
                    return false;
                }
        } else {
            return false;
        }
    }
    
    public boolean isValidVoucherCode(String voucherCode) {
        if (voucherCode.length() != 16) {
            return false;
        }
        if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }
        String numericalPart = voucherCode.substring(5, 15);
        if (!numericalPart.matches("\\d{8}")) {
            return false;
        }
        return true;
    }

    public void setMethod(String method) {
        if (PaymentMethod.contains(method)) {
            this.method = method;
        } else {
            throw new IllegalArgumentException("Invalid Payment Method");
        }
    }

}
