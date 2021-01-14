package pers.ycm.sbdefault;


import org.junit.Test;

import java.math.BigDecimal;
import java.text.MessageFormat;

/**
 * @author yuanchengman
 * @date 2021-01-11
 */
public class NumberTest {
    /**
     * 字符串格式化测试
     */
    @Test
    public void test1() {
        Long id = 10012334556L;
        System.out.println(MessageFormat.format("id:{0}", id));
        System.out.println(MessageFormat.format("id:{0}", id.toString()));
        System.out.println(MessageFormat.format("id:{0,number,#}", id));

        BigDecimal bigDecimal = BigDecimal.valueOf(1234567890555.678);
        System.out.println(MessageFormat.format("id:{0}", bigDecimal));
        System.out.println(MessageFormat.format("id:{0,number,#}", bigDecimal));
        System.out.println(MessageFormat.format("id:{0,number,#.##}", bigDecimal));
        System.out.println(MessageFormat.format("id:{0,number,#}", null));
    }

    @Test
    public void test2(){
        Long id = null;
        Long age = 20L;
        System.out.println(age.equals(id));
    }
}
