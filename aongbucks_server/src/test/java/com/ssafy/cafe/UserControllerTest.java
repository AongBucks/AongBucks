package com.ssafy.cafe;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ssafy.cafe.controller.rest.UserRestController;

/**
 * @author itsmeyjc
 * @since 2021. 6. 28.
 */
@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserRestController uc;
    
    @Test
    public void testBean() {
        int stamp = 1;
        Map<String, Object> info = uc.getGrade(stamp);
        System.out.println(info);
        
        stamp = 11;
        info = uc.getGrade(stamp);
        System.out.println(info);
        stamp = 21;
        info = uc.getGrade(stamp);
        System.out.println(info);
        stamp = 31;
        info = uc.getGrade(stamp);
        System.out.println(info);
        stamp = 41;
        info = uc.getGrade(stamp);
        System.out.println(info);
        stamp = 551;
        info = uc.getGrade(stamp);
        System.out.println(info);
    }
}
