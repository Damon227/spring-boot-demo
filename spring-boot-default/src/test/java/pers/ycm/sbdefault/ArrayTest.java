package pers.ycm.sbdefault;

import org.junit.Test;

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
}
