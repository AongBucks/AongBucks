package com.ssafy.cafe.model.dao;

import com.ssafy.cafe.model.dto.Favorite;

public interface FavoriteDao {
	
	int insert(Favorite favorite);
	
	int delete(Favorite favorite);

}
