package com.ssafy.cafe.model.service;

import java.util.List;
import java.util.Map;
import com.ssafy.cafe.model.dto.Grade;

public interface GradeService {
	public List<Grade> getAllGrade();
	
	public void updateGrade(Grade grade);
	
	public float getDiscount(int id);
}
