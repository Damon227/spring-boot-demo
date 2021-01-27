package pers.ycm.sbdefault;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import pers.ycm.sbdefault.desensitized.DesensitizedUtils;
import pers.ycm.sbdefault.pojo.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yuanchengman
 * @date 2021-01-12
 */
public class StringTest {
    @Test
    public void stringTest1(){
        Pattern pattern = Pattern.compile("[0-9]{4}[-][0-9]{1,2}[-][0-9]{1,2}[ ][0-9]{1,2}[:][0-9]{1,2}[:][0-9]{1,2}");
        Matcher matcher = pattern.matcher("2、开标时间：2018-08-02 14:00:00.2。2、开标时间：2018-08-02 16:00:00。2、开标时间：2018-08-02 15:00:00。");
        while(matcher.find()){
            System.out.println(matcher.group());
        }

        LocalDateTime parse = LocalDateTime.parse("2018-08-02 14:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(parse);
    }

    @Test
    public void testIdCardNum(){
        String id = "12345";
        System.out.println(DesensitizedUtils.idCardNum(id));
    }

    @Test
    public void testEmail(){
        String email = "abcd@qq.com";
        System.out.println(DesensitizedUtils.email(email));
    }

    @Test
    public void testAddress(){
        String address = "上海市浦东新区";
        System.out.println(DesensitizedUtils.address(address,9));
        System.out.println(DesensitizedUtils.address(address,3));
    }

    @Test
    public void testCardNumber(){
        String cardNumber = "沪A983D4";
        System.out.println(DesensitizedUtils.carNumber(cardNumber));
    }

    @Test
    public void testJSON(){
        User user = new User();
        user.setName("jack chen");
        user.setBirthday(LocalDate.of(1993, 2, 27));
        user.setCreateTime(LocalDateTime.now());

        String json = JSON.toJSONString(user);
        System.out.println(json);
    }

    @Test
    public void testTime(){
        LocalDateTime today_start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(today_start);
        System.out.println(df.format(today_start));

        String s = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-01 00:00:01"));
        LocalDateTime startTime = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .minusMonths(6);
        System.out.println(startTime);
        System.out.println(LocalDateTime.now());
    }
}
