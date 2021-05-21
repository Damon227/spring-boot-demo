package pers.ycm.sbdefault.easyexcel;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.Test;
import pers.ycm.sbdefault.pojo.dto.Book;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        String fileName = "target/demo.xlsx";

        List<List<Object>> data = new ArrayList<>() {
        };

        data.add(ListUtil.of("name", "age", "money", 123));

        ExcelWriter excelWriter = EasyExcel.write(fileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("sheet1").build();

        int count = 100;
        for (int i = 0; i < count; i++) {
            excelWriter.write(data, writeSheet);
        }

        excelWriter.finish();
    }

    @Test
    public void test3() {
        String fileName = "target/demo.xlsx";

        List<List<Object>> data = new ArrayList<>() {
        };

        // 自定义表头
        List<List<String>> headers = new ArrayList<>();
        headers.add(ListUtil.of("姓名"));
        headers.add(ListUtil.of("年龄"));
        headers.add(ListUtil.of("存款"));
        headers.add(ListUtil.of("数字"));

        ExcelWriter excelWriter = EasyExcel.write(fileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("sheet1").head(headers).build();

        int count = 100;
        for (int i = 0; i < count; i++) {
            data.add(ListUtil.of("name", "age", "money", 123));
        }
        excelWriter.write(data, writeSheet);

        excelWriter.finish();
    }

    @Test
    public void test4() throws NoSuchFieldException, IllegalAccessException {
        String fileName = "target/demo.xlsx";

        List<Book> data = new ArrayList<>();

        // 自定义表头
        List<List<String>> headers = getHeaders(Book.class);

        ExcelWriter excelWriter = EasyExcel.write(fileName, rebuildObject(Book.class)).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("sheet1").build();

        int count = 100;
        for (int i = 0; i < count; i++) {
            Book book = new Book("book" + i, BigDecimal.valueOf(i), LocalDateTime.now());
            data.add(book);
        }
        excelWriter.write(data, writeSheet);

        excelWriter.finish();
    }

    private List<List<String>> getHeaders(Class<?> clazz) {
        List<List<String>> headers = new ArrayList<>();
        List<Field> fields = new ArrayList<>();
        Class tempClazz = clazz;
        while (tempClazz != null){
            fields.addAll(new ArrayList<>(Arrays.asList(tempClazz.getDeclaredFields())));
            tempClazz = tempClazz.getSuperclass();
        }

        for (Field field : fields) {
            ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
            if (annotation != null) {
                ArrayList<String> list = new ArrayList<>(Arrays.asList(annotation.value()));
                headers.add(list);
            }
            else{
                String name = field.getName();
                headers.add(new ArrayList<>(Arrays.asList(name)));
            }
        }

        return headers;
    }

    private Class<?> rebuildObject(Class<?> clazz) throws NoSuchFieldException, IllegalAccessException {
        List<Field> fields = new ArrayList<>();
        Class tempClazz = clazz;
        while (tempClazz != null){
            fields.addAll(new ArrayList<>(Arrays.asList(tempClazz.getDeclaredFields())));
            tempClazz = tempClazz.getSuperclass();
        }

        for (Field field : fields) {
            field.setAccessible(true);
            ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
            if (annotation != null) {
                InvocationHandler ih = Proxy.getInvocationHandler(annotation);
                Field annotationField = ih.getClass().getDeclaredField("memberValues");
                annotationField.setAccessible(true);
                Map memberValues  = (Map)annotationField.get(ih);
                String[] newValues = new String[1];
                newValues[0] = "哈哈";
                memberValues .put("value", newValues);
            }
            else{

            }
        }

        return clazz;
    }
}
