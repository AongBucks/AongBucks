package com.ssafy.cafe.model.dao;

import java.util.List;
import com.ssafy.cafe.model.dto.Comment;

public interface CommentDao {
    int insert(Comment comment);

    int update(Comment comment);

    int delete(Integer commentId);

    Comment select(Integer commentId);

    List<Comment> selectAll();

    List<Comment> selectByProduct(Integer productId);
}
