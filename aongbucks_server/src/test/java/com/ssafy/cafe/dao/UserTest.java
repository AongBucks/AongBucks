package com.ssafy.cafe.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import com.ssafy.cafe.model.dto.User;

class UserTest extends AbstractDaoTest{

    User data = new User("새사용자","hong gil dong","1234", 0);
    
    
    @Test
    @Order(1)
    public void insertTest() {
        int result = uDao.insert(data);
        assertEquals(result, 1);
    }
    
    @Test
    @Order(2)
    public void selectTest() {
        User selected = uDao.select(data.getId());
        assertEquals(selected.getName(), data.getName());

    }

    @Test
    @Order(3)
    public void updateTest() {
        data.setName("jang gil san");
        int result = uDao.update(data);
        assertEquals(result, 1);
        
        User selected = uDao.select(data.getId());
        assertEquals(data.getName(), selected.getName());
    }
    
    @Test
    @Order(4)
    public void deleteTest() {
        int result = uDao.delete(data.getId());
        assertEquals(result, 1);
        
        User selected = uDao.select(data.getId());
        assertNull(selected);
    }
    
    @Test
    public void selectAl() {
        List<User> result = uDao.selectAll();
        
        User selected = result.get(0);
        assertEquals(selected.getId(), "id 01");
    }
}
