package com.ssafy.cafe.controller.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cafe.model.dto.Product;
import com.ssafy.cafe.model.service.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/product")
@CrossOrigin("*")
public class ProductRestController {
    @Autowired
    ProductService pService;
    
    @GetMapping()
    @ApiOperation(value="전체 상품의 목록을 반환한다.", response = List.class)
    public List<Product> getProductList(){
        return pService.getProductList();
    }
    
    @GetMapping("/favorite")
    @ApiOperation(value="사용자가 즐겨찾기로 등록한 상품의 목록을 반환한다.", response = List.class)
    public List<Product> getProductList(String userId){
        return pService.getFavoriteProducts(userId);
    }
    
    @GetMapping("/{productId}")
    @ApiOperation(value="{productId}에 해당하는 상품의 정보를 comment와 함께 반환한다."
            + "이 기능은 상품의 comment를 조회할 때 사용된다."
    		+ "이때 현재 로그인한 사용자가 해당 상품을 즐겨찾기로 등록했는지 여부도 함께 반환한다.", response = List.class)
    public List<Map<String, Object>> getProductWithComments(@PathVariable Integer productId, String userId){
        return pService.selectWithComment(productId, userId);
    }
}
