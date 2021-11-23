package com.ssafy.cafe.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cafe.model.service.PayService;

import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;
import com.ssafy.cafe.model.dto.Grade;
import com.ssafy.cafe.model.dto.Pay;

@RestController
@RequestMapping("rest/pay")
@CrossOrigin("*")
public class PayRestController {
	@Autowired
	private PayService pService;
	
	@PostMapping("/{userId}")
	@ApiOperation(value="pay에 user id를 추가한다.(가입)", response = Boolean.class)
	public Boolean postUserId(@PathVariable String userId) {
		pService.addUser(userId);
		return true;
	}

	@GetMapping("/{userId}")
	@ApiOperation(value="user가 가입된 상태이면 pay정보를 반환한다.", response = Pay.class)
	public Pay getGradeInfo(@PathVariable String userId) {
		return pService.select(userId);
	}
	
	@PatchMapping("/price")
	@ApiOperation(value="price잔액을 바꾼다.", response = Boolean.class)
	public Boolean updatePrice(@RequestBody Pay pay) {
		pService.setPrice(pay);
		return true;
	}
}
