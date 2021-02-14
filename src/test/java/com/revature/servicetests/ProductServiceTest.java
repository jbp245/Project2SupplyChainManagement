/**
 * 
 */
package com.revature.servicetests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.beans.Product;
import com.revature.repositories.ProductRepository;
import com.revature.services.ProductService;
import com.revature.services.ProductServiceImpl;

/**
 * @author james
 *
 */
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @TestConfiguration
    static class ProductServiceImplTestContextConfiguration {
 
        @Bean
        public ProductService employeeService() {
            return new ProductServiceImpl();
        }
    }

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        Product test = new Product("test_product", 1, 1);

        Mockito.when(productRepository.findByName(test.getProduct_name()))
          .thenReturn(test);
        
        System.out.println(productRepository.findAll().toString());
    }
    
    @Test
    public void whenValidName_thenProductShouldBeFound() {
        String name = "test_product";
        Product found = productService.getProduct(0);
     
         assertThat(found.getProduct_name())
          .isEqualTo(name);
     }
}