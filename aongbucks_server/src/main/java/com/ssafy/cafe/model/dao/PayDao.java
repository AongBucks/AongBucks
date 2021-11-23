package com.ssafy.cafe.model.dao;

import com.ssafy.cafe.model.dto.Pay;

public interface PayDao {
	
	/**
	 * pay 등록 
	 * @param userId
	 */
	void insert(String userId);
	
	Pay select(String userId);
	
	void update(Pay pay);
}
