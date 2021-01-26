package pers.ycm.sbdefault.pojo.dto;

import pers.ycm.sbdefault.desensitized.Desensitized;
import pers.ycm.sbdefault.common.enums.SensitiveTypeEnum;

import java.math.BigDecimal;

/**
 * @author yuanchengman
 * @date 2021-01-25
 */
public class Book {
    @Desensitized(type = SensitiveTypeEnum.CHINESE_NAME)
    private String name;
    private BigDecimal price;

    public Book(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
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
}
