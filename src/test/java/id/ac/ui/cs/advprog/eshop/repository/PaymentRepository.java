package id.ac.ui.cs.advprog.eshop.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.expression.MapAccessor;

import com.github.dockerjava.api.model.Repository;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    private Map<String, String> paymentDataA = new HashMap<>();
    private Map<String, String> paymentDataB = new HashMap<>();
    private Order order;
    private List<Product> products;
    List<Payment> payments;

    @BeforeEach
    void setup(){        
        this.products = new ArrayList<>();
        payments = new ArrayList<>();
        paymentRepository = new PaymentRepository();

        Product product1 = new Product();
        product1.setProductId("e45d7d21-fd29-4533-a569-abbe0819579a");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("8a76b99c-a0b3-46d2-a688-4c1831b21119");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);

        this.products.add(product1);
        this.products.add(product2);

        order = new Order("dbd4aff4-9a7f-4603-92c2-eaf529271cc9", products, 1708560000L, "Safira Sudrajat");

        paymentDataA.put("bankName", "BCA");
        paymentDataA.put("referenceCode", "0987654321");
        paymentDataB.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", paymentDataA , order);
        Payment payment2 = new Payment("7f9e15bb-4b15-42f4-aebc-c3af385fb078", "VOUCHER_CODE", paymentDataB, order);

        payments.add(payment1);
        payments.add(payment2);
    }


    @Test
    void testSaveCreate(){
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);
        Payment findResult = paymentRepository.findById(payments.get(1).getId());

        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }
    @Test
    void testSaveUpdate(){
        Payment payment = payments.get(1);
        paymentRepository.save(payment);

        Payment newPayment = new Payment(payment.getId(), payment.getMethod(), payment.getPaymentData(), order);
        Payment result = paymentRepository.save(newPayment);
        Payment findResult = paymentRepository.findById(payments.get(1).getId());

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
        assertEquals(payment.getStatus(), result.getStatus());
    }
    @Test
    void testFindByIdIfIdFound(){
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }
        Payment findResult = paymentRepository.findById(payments.get(1).getId());

        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
    }
    @Test
    void testFindByIdIfIdNotFound(){
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }
        Payment findResult = paymentRepository.findById("zczc");

        assertNull(findResult);
    }
    @Test
    void testFindAll(){
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }
        List<Payment> findResult = paymentRepository.findAll();

        assertEquals(payments.size(), findResult.size());
    }

}