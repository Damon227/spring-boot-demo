package pers.ycm.sbdefault.pojo.dto;

import pers.ycm.sbdefault.annotation.Desensitized;
import pers.ycm.sbdefault.annotation.Zero2Null;
import pers.ycm.sbdefault.common.enums.SensitiveTypeEnum;

import java.util.List;

/**
 * @author yuanchengman
 * @date 2021-01-25
 */
public class StudentDTO {
    @Desensitized(type = SensitiveTypeEnum.CHINESE_NAME)
    private String name;
    @Zero2Null
    private Integer gender;
    private List<Book> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
