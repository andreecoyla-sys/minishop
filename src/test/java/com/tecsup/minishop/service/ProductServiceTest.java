package com.tecsup.minishop.service;

import com.tecsup.minishop.model.Product;
import com.tecsup.minishop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setup() {
        ProductRepository repository = mock(ProductRepository.class);
        productService = new ProductService(repository);
    }

    @Test
    void save_precioCero_lanzaExcepcion() {
        // Arrange
        Product product = Product.builder().name("Mouse").price(0.0).stock(5).build();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> productService.save(product));
    }

    @Test
    void save_stockNegativo_lanzaExcepcion() {
        Product product = Product.builder().name("Mouse").price(50.0).stock(-1).build();

        assertThrows(IllegalArgumentException.class, () -> productService.save(product));
    }

    @Test
    void save_productoValido_noLanzaExcepcion() {
        Product product = Product.builder().name("Mouse").price(50.0).stock(5).build();

        assertDoesNotThrow(() -> productService.save(product));
    }
}