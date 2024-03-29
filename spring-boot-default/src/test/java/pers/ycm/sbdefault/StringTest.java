package pers.ycm.sbdefault;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import pers.ycm.sbdefault.common.utils.JsonUtil;
import pers.ycm.sbdefault.common.utils.ModelConvertUtil;
import pers.ycm.sbdefault.desensitized.DesensitizedUtils;
import pers.ycm.sbdefault.model.DemoEntity;
import pers.ycm.sbdefault.model.DemoModel;
import pers.ycm.sbdefault.pojo.dto.StudentDTO;
import pers.ycm.sbdefault.pojo.entity.User;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        String cardNumber = "沪A9823";
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

    @Test
    public void testTime1(){
        String s = "2021-02-03 23:59:59.000";
        LocalDateTime parse = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        parse = parse.plusNanos(999000000);
        System.out.println(parse);
    }

    @Test
    public void test2()  {
        String format = MessageFormat.format("''{0}'',{1}", 11, 22);
        System.out.println(format);
    }

    @Test
    public void test3(){
        List<StudentDTO> list = new ArrayList<>();
        //list.add(new StudentDTO("1"));
        //list.add(new StudentDTO("2"));
        //list.add(new StudentDTO("3"));
        String s = list.stream().map(t -> t.getName()).collect(Collectors.joining(","));
        System.out.println(s);
        List<Long> collect = Arrays.stream(s.split(",")).distinct().map(t -> Long.parseLong(t.trim())).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test4(){
        String s = "无时间条件范围消费金额大于等于1";
        String encode = s == null ? null : URLEncoder.encode(s, StandardCharsets.UTF_8);
        System.out.println(encode);
        String decode = URLDecoder.decode(encode, StandardCharsets.UTF_8);
        System.out.println(decode);
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHMMss")));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
    }

    @Test
    public void test5(){
        int num = Integer.parseInt(String.format("%d01", 325));
        System.out.println(num);
    }

    @Test
    public void test6(){
        String value = "{\"provinceId\":310000,\"cityId\":310100,\"districtsId\":310105,\"province\":\"上海\",\"city\":\"上海市\",\"districts\":\"长宁区\"}";
        StringBuilder stringBuilder = new StringBuilder();
        JSONObject jsonObj = new JSONObject(value);
        String province = jsonObj.get("province", String.class);
        stringBuilder.append(province);
        String city = jsonObj.get("city", String.class);
        stringBuilder.append(city);
        String districts = jsonObj.get("districts", String.class);
        stringBuilder.append(districts);
        System.out.println(stringBuilder.toString());
    }

    @Test
    public void test7(){
        DemoEntity originModel = new DemoEntity();
        originModel.setId(0L);
        originModel.setName("rose");

        DemoModel model = ModelConvertUtil.toModel(originModel, DemoModel.class);
        DemoModel model2 = new DemoModel();
        ModelConvertUtil.toModel(originModel, model2);
        System.out.println(model.getCreateTime());
    }

    @Test
    public void test8(){
        Map map = new HashMap();
        map.put("id", 1);
        map.put("name", "李浩");

        String str = JsonUtil.obj2String(map);
        System.out.println(str);
    }
}
