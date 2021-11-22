package com.ssafy.cafe.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cafe.model.service.GradeService;

import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;
import com.ssafy.cafe.model.dto.Grade;

@RestController
@RequestMapping("rest/grade")
@CrossOrigin("*")
public class GradeRestController {
	@Autowired
	private GradeService gService;

	@GetMapping("/all")
	@ApiOperation(value="전체 등급 정보를 목록 형태로 반환한다.", response = List.class)
	public List<Grade> getGradeInfo() {
		return gService.getAllGrade();
	}
	
	@PatchMapping("/info")
	@ApiOperation(value="grade 정보를 바꾼다.", response = Integer.class)
	public Boolean update(@RequestBody Grade grade) {
		gService.updateGrade(grade);
		return true;
	}
}
