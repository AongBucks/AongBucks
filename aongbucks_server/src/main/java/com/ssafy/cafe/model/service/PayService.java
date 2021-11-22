package com.ssafy.cafe.model.service;

import java.util.List;
import java.util.Map;
import com.ssafy.cafe.model.dto.Pay;

public interface PayService {
	public void addUser(String userId);
	
	public Boolean isJoined(String userId);
	
	public void setPrice(Pay pay);
}
