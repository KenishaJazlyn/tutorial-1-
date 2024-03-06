package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.*;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();

    public Payment save(Payment payment){
        int i = 0;
        for(Payment savedPayment : paymentData){
            if(savedPayment.getId().equals(payment.getId())){
                paymentData.remove(i);
                paymentData.add(i, payment);
                return payment;
            }
            i++;
        }
        paymentData.add(payment);
        return payment;
    }

    public List<Payment> findAll() {
        return paymentData;
    }

    public Payment findById(String id) {
        for (Payment savedPayment : paymentData) {
            if (isIdEqual(savedPayment.getId(), id)) {
                return savedPayment;
            }
        }
        return null;
    }

    public Boolean isIdEqual(String id1, String id2){
        if (id1.equals(id2)){
            return true;
        } else {
            return false;
        }
    }
}