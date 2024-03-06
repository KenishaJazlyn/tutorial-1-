package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;

class PaymentTest {
    private Map<String, String> paymentData ;
    private Order order;
    private List<Product> products;

    @BeforeEach
    void setup() {
        this.paymentData = new HashMap<>();
        
        this.products = new ArrayList<>();
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

        order= new Order(
            "dbd4aff4-9a7f-4603-92c2-eaf529271cc9", 
            products, 
            1708560000L, 
            "Safira Sudrajat"
        );
    }

    void loadBankTransferPaymentData() {  
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "0987654321");
    }

    void loadVoucherCodePaymentData() {  
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test 
    void testCreatePaymentWithEmptyPaymentData(){
        loadVoucherCodePaymentData();
        paymentData.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("dbd4aff4-9a7f-4603-92c2-eaf529271cc9",  PaymentMethod.VOUCHER.getValue(),  paymentData, order);
         });
    }
    @Test
    void testCreatePaymentWithNullOrder() {
        loadVoucherCodePaymentData();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("e45d7d21-fd29-4533-a569-abbe0819579a",  PaymentMethod.VOUCHER.getValue(), paymentData, null);
        });
    }
    @Test 
    void testCreatePaymentWithInvalidMethod(){
        loadVoucherCodePaymentData();
        paymentData.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("dbd4aff4-9a7f-4603-92c2-eaf529271cc9", "INVALID",  paymentData, order);
         });
    }

    @Test
    void testCreatePaymentWithBankMethod(){
        loadVoucherCodePaymentData();
        loadBankTransferPaymentData();
        Payment payment = new Payment("dbd4aff4-9a7f-4603-92c2-eaf529271cc7",  PaymentMethod.BANK.getValue(), paymentData, order);
        assertSame(this.order, payment.getOrder());
        assertEquals("dbd4aff4-9a7f-4603-92c2-eaf529271cc9", payment.getOrder().getId());
        assertEquals(1708560000L, payment.getOrder().getOrderTime());
        assertEquals("Safira Sudrajat", payment.getOrder().getAuthor());
        assertEquals("WAITING_PAYMENT", payment.getOrder().getStatus());

        assertEquals("dbd4aff4-9a7f-4603-92c2-eaf529271cc7", payment.getId());
        assertEquals( PaymentMethod.BANK.getValue(), payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    } 

    @Test
    void testCreatePaymentWithVoucherMethod(){
        loadVoucherCodePaymentData();
        loadBankTransferPaymentData();
        Payment payment = new Payment("dbd4aff4-9a7f-4603-92c2-eaf529271cc7",  PaymentMethod.VOUCHER.getValue(), paymentData, order);
        assertSame(this.order, payment.getOrder());
        assertEquals("dbd4aff4-9a7f-4603-92c2-eaf529271cc9", payment.getOrder().getId());
        assertEquals(1708560000L, payment.getOrder().getOrderTime());
        assertEquals("Safira Sudrajat", payment.getOrder().getAuthor());
        assertEquals("WAITING_PAYMENT", payment.getOrder().getStatus());

        assertEquals("dbd4aff4-9a7f-4603-92c2-eaf529271cc7", payment.getId());
        assertEquals( PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    } 
}

