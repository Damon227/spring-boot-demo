package pers.ycm.sbdefault.service;

import org.springframework.stereotype.Service;
import pers.ycm.sbdefault.aop.ModifyParamAop;
import pers.ycm.sbdefault.pojo.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @author yuanchengman
 * @date 2021-01-26
 */
@Service
public class UserService {

    public User getCurrent() {
        User user = new User();
        user.setId(1L);
        user.setName("yapi");
        return user;
    }

    @ModifyParamAop(fieldNames = {"createTime"})
    public User getUser(User user) {
        return user;
    }

    @ModifyParamAop(fieldNames = {"createTime"})
    public List<User> getUsers(List<User> users) {
        return users;
    }

    public LocalDateTime getUserCreateTime(int index) {
        if (index > 1) {
            return null;
        }

        int month = 6;
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().withDayOfMonth(1), LocalTime.MIN).minusMonths(month);
        LocalDateTime[] range = {startTime, LocalDateTime.now()};
        return range[index];
    }
}
