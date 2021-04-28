package pers.ycm.sbdefault;

import cn.hutool.core.collection.ListUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanchengman
 * @date 2021-01-12
 */
public class ArrayTest {
    @Test
    public void test1(){
        String[] array = new String[5];

        String str = "1@2@3";
        String[] newArray = str.split("@");
        System.out.println(newArray.length);

        System.arraycopy(newArray, 0, array, 0, newArray.length);

        System.out.println(array.length);
        System.out.println(array[3]);
        System.out.println(array[4]);
    }

    @Test
    public void test2(){
        List<Integer> list1 = ListUtil.of(1,2,3,4);
        List<Integer> list2 = new ArrayList<>(list1);
        list2.set(0, 11);
        System.out.println("");
    }
}
