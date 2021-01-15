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
}
