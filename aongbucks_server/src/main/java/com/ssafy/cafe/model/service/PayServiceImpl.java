package com.ssafy.cafe.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.cafe.model.dao.GradeDao;
import com.ssafy.cafe.model.dao.PayDao;
import com.ssafy.cafe.model.dto.Grade;
import com.ssafy.cafe.model.dto.Pay;

@Service
public class PayServiceImpl implements PayService {

	@Autowired
	PayDao pDao;

	@Override
	public void addUser(String userId) {
		pDao.insert(userId);
	}

	@Override
	public Pay select(String userId) {
		return pDao.select(userId);
	}

	@Override
	public void setPrice(Pay pay) {
		pDao.update(pay);
	}
	

}
