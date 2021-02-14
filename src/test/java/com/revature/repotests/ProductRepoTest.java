/**
 * 
 */
package com.revature.repotests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.beans.Product;
import com.revature.repositories.ProductRepository;

/**
 * @author james
 *
 */
@RunWith(SpringRunner.class)
//@DataJpaTest
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ComponentScan("com.revature.demo")
//@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepoTest {
    @Autowired
    ProductRepository repository;
     
    @Test
    public void testRepository() 
    {
        Product prod = new Product();
        prod.setProduct_name("Lokesh");
        prod.setProduct_cost(5.00);
        prod.setStock_in_warehouse(1);
         
        repository.save(prod);
         
        Assert.assertNotNull(prod.getId());
    }
}