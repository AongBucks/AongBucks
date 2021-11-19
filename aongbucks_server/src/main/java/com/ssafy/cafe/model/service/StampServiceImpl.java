package com.ssafy.cafe.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.cafe.model.dao.StampDao;
import com.ssafy.cafe.model.dto.Stamp;

/**
 * @author itsmeyjc
 * @since 2021. 6. 23.
 */
@Service
public class StampServiceImpl implements StampService{

    @Autowired
    StampDao sDao;
    
    @Override
    public List<Stamp> selectByUser(String id) {
        return sDao.selectByUserId(id);
    }

}
