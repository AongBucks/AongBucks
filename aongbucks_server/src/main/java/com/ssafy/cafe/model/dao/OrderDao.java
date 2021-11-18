package com.ssafy.cafe.model.dao;

import java.util.List;
import java.util.Map;
import com.ssafy.cafe.model.dto.Order;

public interface OrderDao {
    int insert(Order order);

    int update(Order order);

    int delete(Integer orderId);

    Order select(Integer orderId);

    List<Order> selectAll();
    
    Order selectWithDetail(int id);
    
    List<Order> selectByUser(String userId);
    // back end 관통에서 추가함
    List<Map> selectOrderTotalInfo(int id); 
    
    /**
     * 최근 1개월의 주문 내역을 반환한다.
     * 관통 6단계에서 추가됨
     * @param id
     * @return
     */
    List<Map<String, Object>> getLastMonthOrder(String id);
}
