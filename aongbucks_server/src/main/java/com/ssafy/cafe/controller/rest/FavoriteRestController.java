package com.ssafy.cafe.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cafe.model.dto.Favorite;
import com.ssafy.cafe.model.service.FavoriteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/favorite")
@CrossOrigin("*")
public class FavoriteRestController {

    @Autowired
    FavoriteService fService;
    
    @PostMapping
    @Transactional
    @ApiOperation(value="하트를 누른 사용자와 해당 상품의 번호를 추가한다.", response = Boolean.class)
    public Boolean insert(@RequestBody Favorite favorite) {
        fService.addFavorite(favorite);
        return true;
    }

    @DeleteMapping
    @Transactional
    @ApiOperation(value="userId와 productId에 해당하는 Favorite을 삭제한다.", response = Boolean.class)
    public Boolean delete(@RequestBody Favorite favorite) {
    	fService.removeFavorite(favorite);
        return true;
    }
}
