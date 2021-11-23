package com.ssafy.cafe.model.dao;

import java.util.List;
import java.util.Map;
import com.ssafy.cafe.model.dto.Grade;

public interface GradeDao {
	List<Grade> selectAll();
	
	int update(Grade grade);
	
	float selectDiscount(String id);
}
