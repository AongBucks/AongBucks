package com.ssafy.cafe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import com.ssafy.cafe.model.dto.Order;
import com.ssafy.cafe.model.dto.OrderDetail;


class OrderServiceTest extends AbstractServiceTest {

    

    @Test
    @org.junit.jupiter.api.Order(1)
    void makeOrderTest() {
        Order order = new Order(user.getId(), "table_test", new Date(), 'N');
        List<OrderDetail> details = new ArrayList<>();
        details.add(new OrderDetail(1, 1));
        details.add(new OrderDetail(2, 2));
        details.add(new OrderDetail(2, 2));
        order.setDetails(details);
        
        orderService.makeOrder(order);
    }
    
    static Order last;
    @Test
    @org.junit.jupiter.api.Order(2)
    void getOrderByUserTest() {
        List<Order> orders = orderService.getOrdreByUser(user.getId());
        System.out.println(last);
        last = orders.get(0);
        assertEquals(last.getOrderTable(), "table_test");
        
        orders = orderService.getOrdreByUser("id 02");
        assertEquals(orders.size(), 0);
    }
    
    
    @Test
    @org.junit.jupiter.api.Order(3)
    void getOrderWithDetailsTest() {
        Order order = orderService.getOrderWithDetails(last.getId());
        assertEquals(order.getUserId(), last.getUserId());
        assertEquals(order.getOrderTable(), last.getOrderTable());
        List<OrderDetail> details = order.getDetails();
        assertEquals(details.size(), 3);
        assertEquals(details.get(0).getQuantity(), 2);
        last = order;
    }


    
    @Test
    @org.junit.jupiter.api.Order(4)
    void updateOrderTest() {
        assertEquals(last.getCompleted(), 'N');
        last.setCompleted('Y');
        orderService.updateOrder(last);
        Order order = orderService.getOrderWithDetails(last.getId());
        assertEquals(order.getCompleted(), 'Y');
    }
    
    @Test
    @org.junit.jupiter.api.Order(5)
    void deleteOrderTest() {
        List<OrderDetail> details = last.getDetails();
        System.out.println("삭제 대상 목록: "+details);
        for(OrderDetail detail: details) {
            dDao.delete(detail.getId());
        }
        dDao.delete(last.getId());
    }
    
    @Test
    public void selectLastOrderTest() {
        List<Map<String, Object>> orders = orderService.getLastMonthOrder("id 08");
        System.out.println(orders);
    }

}
