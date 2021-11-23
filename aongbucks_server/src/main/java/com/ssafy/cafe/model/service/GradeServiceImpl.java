package com.ssafy.cafe.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.cafe.model.dao.GradeDao;
import com.ssafy.cafe.model.dto.Grade;

@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	GradeDao gDao;
	
	@Override
	public List<Grade> getAllGrade() {
		return gDao.selectAll();
	}

	@Override
	public void updateGrade(Grade grade) {
		gDao.update(grade);
	}

	@Override
	public float getDiscount(int id) {
		return gDao.selectDiscount(id);
	}

}
