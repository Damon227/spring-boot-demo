package pers.ycm.sbdefault;

import org.junit.Test;
import pers.ycm.sbdefault.desensitized.DesensitizedUtils;

import java.time.LocalDateTime;
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
}
