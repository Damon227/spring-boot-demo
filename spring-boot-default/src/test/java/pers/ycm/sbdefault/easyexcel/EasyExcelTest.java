package pers.ycm.sbdefault.easyexcel;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanchengman
 * @date 2021-01-15
 */
public class EasyExcelTest {

    /**
     * 写入动态列
     */
    @Test
    public void test1() {
        String fileName = "demo.xlsx";

        List<List<Object>> data = new ArrayList<>() {
        };

        data.add(ListUtil.of("name", "age"));
        data.add(ListUtil.of("name", "age", "money", 123));

        ExcelWriter excelWriter = EasyExcel.write(fileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("sheet1").build();

        excelWriter.write(data, writeSheet);
        excelWriter.finish();
    }

    /**
     * 测试分批次写入同一文件
     */
    @Test
    public void test2() {
        String fileName = "demo.xlsx";

        List<List<Object>> data = new ArrayList<>() {
        };

        data.add(ListUtil.of("name", "age", "money", 123));

        ExcelWriter excelWriter = EasyExcel.write(fileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("sheet1").build();

        int count = 1000000;
        for (int i = 0; i < count; i++) {
            excelWriter.write(data, writeSheet);
        }

        excelWriter.finish();
    }

    @Test
    public void test3() {
        String fileName = "demo.xlsx";

        List<List<Object>> data = new ArrayList<>() {
        };

        ExcelWriter excelWriter = EasyExcel.write(fileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("sheet1").build();

        int count = 1000000;
        for (int i = 0; i < count; i++) {
            data.add(ListUtil.of("name", "age", "money", 123));
        }
        excelWriter.write(data, writeSheet);

        excelWriter.finish();
    }
}
