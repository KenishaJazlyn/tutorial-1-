package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void whenCreateProductPage_thenReturnsCreateProductView() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void whenCreateProductPost_thenProductIsCreatedAndRedirectToList() throws Exception {
        mockMvc.perform(post("/product/create")
                        .flashAttr("product", new Product()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService).create(any(Product.class));
    }

    @Test
    void whenProductListPage_thenReturnsProductListViewWithProducts() throws Exception {
        when(productService.findAll()).thenReturn(Arrays.asList(new Product(), new Product()));

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ProductList"))
                .andExpect(model().attributeExists("products"));

        verify(productService).findAll();
    }

    @Test
    void whenDeleteProduct_thenProductIsDeletedAndRedirectToList() throws Exception {
        mockMvc.perform(get("/product/delete/{productId}", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService).deleteById("1");
    }

    @Test
    void whenFormEditProduct_thenReturnsEditProductViewWithProduct() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        when(productService.findById("1")).thenReturn(product);

        mockMvc.perform(get("/product/edit/{productId}", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("product"));

        verify(productService).findById("1");
    }

    @Test
    void whenEditProduct_thenProductIsUpdatedAndRedirectToList() throws Exception {
        mockMvc.perform(post("/product/edit")
                        .flashAttr("productEdited", new Product()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService).update(any(Product.class));
    }
}
