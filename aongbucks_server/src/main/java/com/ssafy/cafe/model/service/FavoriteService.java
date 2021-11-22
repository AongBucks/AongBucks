package com.ssafy.cafe.model.service;

import com.ssafy.cafe.model.dto.Favorite;

public interface FavoriteService {

	
	/**
     * 즐겨찾기를 등록한 사용자와 해당 상품 번호를 등록한다.
     * @param favorite
     */
    void addFavorite(Favorite favorite);
	
	
	/**
     * userId와 productId에 해당하는 즐겨찾기 정보를 삭제한다.
     * @param favorite
     */
    void removeFavorite(Favorite favorite);
	
}
