package pers.ycm.sbdefault.pojo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import pers.ycm.sbdefault.converters.LocalDateTimeConverter;
import pers.ycm.sbdefault.desensitized.Desensitized;
import pers.ycm.sbdefault.common.enums.SensitiveTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author yuanchengman
 * @date 2021-01-25
 */
public class Book extends BookBase{
    @ExcelProperty({"书名", "bookName"})
    @Desensitized(type = SensitiveTypeEnum.CHINESE_NAME)
    private String name;
    @ExcelProperty("价格")
    private BigDecimal price;
    @ExcelProperty(value = "时间", converter = LocalDateTimeConverter.class)
    private LocalDateTime createTime;
    private String remark1;
    private String remark20;

    public Book(String name, BigDecimal price, LocalDateTime createTime) {
        this.name = name;
        this.price = price;
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
