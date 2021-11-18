package com.ssafy.cafe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.ssafy.cafe.model.dto.Product;
import com.ssafy.cafe.model.dto.User;
import com.ssafy.cafe.model.service.UserService;


class ProductServiceTest extends AbstractServiceTest{
    
    
    @Test
    void listTest() {
        List<Product> list = prodService.getProductList();
        assertEquals(list.size(), 10);
        Product p = list.get(0);
        assertEquals(p.getName(), "cookie");
    }
}
