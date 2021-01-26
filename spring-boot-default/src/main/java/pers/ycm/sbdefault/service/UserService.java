package pers.ycm.sbdefault.service;

import org.springframework.stereotype.Service;
import pers.ycm.sbdefault.pojo.entity.User;

/**
 * @author yuanchengman
 * @date 2021-01-26
 */
@Service
public class UserService {

    public User getCurrent(){
        User user = new User();
        user.setId(1L);
        user.setName("yapi");
        return user;
    }
}
