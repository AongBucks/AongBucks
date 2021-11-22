package com.ssafy.cafe.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.cafe.model.dao.FavoriteDao;
import com.ssafy.cafe.model.dto.Favorite;

@Service
public class FavoriteServiceImpl implements FavoriteService {
	
	@Autowired
    FavoriteDao fDao;

	@Override
	@Transactional
	public void addFavorite(Favorite favorite) {
		fDao.insert(favorite);
	}

	@Override
	@Transactional
	public void removeFavorite(Favorite favorite) {
		fDao.delete(favorite);
	}

}
