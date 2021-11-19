package com.ssafy.cafe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import com.ssafy.cafe.model.dto.Comment;


class CommentServiceTest extends AbstractServiceTest {
    private Integer productId = 1;
    private Comment comment = new Comment("id 01", productId, 5.0, new Date().toString());
    
    @Test
    @Order(1)
    void addCommentTest() {
        cService.addComment(comment);
        List<Comment> comments = cService.selectByProduct(productId);
        last = comments.get(0);
        assertEquals(last.getComment(), comment.getComment());
    }
    
    static Comment last;
         
    
    @Test
    @Order(2)
    void updateCommentTest() {
        System.out.println(last);
        last.setComment("더 좋다");
        cService.updateComment(last);
        
        Comment selected = cService.selectComment(last.getId());
        assertEquals(selected.getComment(), last.getComment());
    }
    
    @Test
    @Order(3)
    void removeCommentTest() {
        Comment selected = cService.selectComment(last.getId());

        cService.removeComment( selected.getId());
    }




}
