package id.ac.ui.cs.advprog.eshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import java.util.Map;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData){
        if(paymentRepository.findById(order.getId()) == null){
            Payment payment = new Payment(order.getId(), method, paymentData, order);
            paymentRepository.save(payment);
            return payment;
        }
        return null;
    };
    
    @Override
    public Payment setStatus(Payment payment, String status){
        if(!status.equals("SUCCESS") && !status.equals("REJECTED")){
            throw new IllegalArgumentException("Invalid payment status");
        }
        Payment paymentX = paymentRepository.findById(payment.getId());
        if (paymentX != null) {
            Payment newPayment = new Payment(payment.getId(), payment.getMethod(), payment.getPaymentData(), payment.getOrder());
            newPayment.setStatus(status);
            if(newPayment.getStatus().equals("SUCCESS")){
                orderRepository.findById(payment.getId()).setStatus("SUCCESS");
            }
            else if(newPayment.getStatus().equals("REJECTED")){
                orderRepository.findById(payment.getId()).setStatus("FAILED");
            }
            else{
                throw new IllegalArgumentException("Invalid payment status");
            }
            paymentRepository.save(newPayment);
            return newPayment;
        } else {
            throw new NoSuchElementException();
        }
    };

    @Override
    public Payment getPayment(String paymentId){
        return paymentRepository.findById(paymentId);
    }
    @Override
    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }
}