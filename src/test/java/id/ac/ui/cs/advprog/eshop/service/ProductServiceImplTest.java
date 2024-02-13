package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        product.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        when(productRepository.findAll()).thenReturn(Arrays.asList(product).iterator());

        Product result = productService.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");

        assertNotNull(result);
        assertEquals("a0f9de46-90b1-437d-a0bf-d0821dde9096", result.getProductId());
    }
     @Test
    void whenFindByIdAndProductDoesNotExist() {
        // Arrange
        String nonExistentProductId = "non-existent-id";
        when(productRepository.findAll()).thenReturn(Collections.emptyIterator());

        // Act
        Product result = productService.findById(nonExistentProductId);

        // Assert
        assertNull(result, "Expected findById to return null when product does not exist");
        
        // Verify that productRepository.findAll() was called
        verify(productRepository).findAll();
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
