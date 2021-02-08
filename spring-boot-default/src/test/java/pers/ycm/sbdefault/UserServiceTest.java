package pers.ycm.sbdefault;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.ycm.sbdefault.config.BeanContext;
import pers.ycm.sbdefault.pojo.entity.User;
import pers.ycm.sbdefault.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanchengman
 * @date 2021-01-27
 */
@SpringBootTest(classes = ServiceInitializer.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testAop() {
        User user = new User();
        user.setName("jack chen");
        user.setBirthday(LocalDate.of(1993, 2, 27));
        UserService userService = BeanContext.getBean(UserService.class);
        User newUser = userService.getUser(user);
        UserService userService2 = BeanContext.getBean(UserService.class);
        userService2.getUser(user);

        System.out.println(JSON.toJSONString(newUser));
    }

    @Test
    public void testAop2(){
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setName("jack chen");
            user.setBirthday(LocalDate.of(1993, 2, 27));
            users.add(user);
        }

        List<User> newUsers = userService.getUsers(users);
        System.out.println(JSON.toJSONString(newUsers));
    }
}
