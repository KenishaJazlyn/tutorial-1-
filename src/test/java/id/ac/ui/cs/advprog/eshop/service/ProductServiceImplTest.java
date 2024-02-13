package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        when(productRepository.create(product)).thenReturn(product);
        
        Product createdProduct = productService.create(product);
        
        verify(productRepository).create(product);
        assertEquals(product.getProductId(), createdProduct.getProductId());
    }

    @Test
    void testFindAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> productList = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(productList.iterator());

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(productRepository).findAll();
    }

    @Test
    void testFindProductById() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        Product createProduct1 = productService.create(product);
        when(productRepository.findAll()).thenReturn(Arrays.asList(product).iterator());

        Product result = productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertNotNull(result);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", result.getProductId());
        assertNotEquals("a0f9de46-90b1-437d-a0bf-d0821dde9096", result.getProductId());
        Product testProduct2 = productService.findById("non-existent-id");
        assertNull(testProduct2);
    }
    @Test
    public void testFindByIdProductIfDoesNotExist() {
        Product product2 = new Product();
        product2.setProductName("Product 2");
        product2.setProductQuantity(200);
        when(productRepository.findAll()).thenReturn(Arrays.asList(product2).iterator());

        Product findedProduct = productService.findById("non-exist-id");
        assertNull(findedProduct);
    }

    @Test
    void testFindByIdProductIfMoreThanOneProduct() {
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setProductName(String.format("Product %d", i + 1));
            product.setProductQuantity(100 + i);
            productRepository.create(product);
        }

        Product lastProduct = new Product();
        lastProduct.setProductName("Last Product");
        lastProduct.setProductQuantity(130);
        productRepository.create(lastProduct);
        when(productRepository.findAll()).thenReturn(Arrays.asList(lastProduct).iterator());


        Product foundProduct = productService.findById(lastProduct.getProductId());
        assertEquals(lastProduct.getProductId(), foundProduct.getProductId());
        assertEquals(lastProduct.getProductName(), foundProduct.getProductName());
        assertEquals(lastProduct.getProductQuantity(), foundProduct.getProductQuantity());
    }
    
    @Test
    void testDeleteProductById() {
        Product product = new Product();
        product.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        when(productRepository.findAll()).thenReturn(Arrays.asList(product).iterator());
        doNothing().when(productRepository).delete(product);

        productService.deleteById("a0f9de46-90b1-437d-a0bf-d0821dde9096");

        verify(productRepository).delete(product);
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        doNothing().when(productRepository).updateProduct(product);

        productService.update(product);

        verify(productRepository).updateProduct(product);
    }
}
