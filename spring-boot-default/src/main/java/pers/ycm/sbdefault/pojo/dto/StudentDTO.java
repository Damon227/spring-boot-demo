package pers.ycm.sbdefault.pojo.dto;

import pers.ycm.sbdefault.annotation.Desensitized;
import pers.ycm.sbdefault.common.enums.SensitiveTypeEnum;

import java.util.List;

/**
 * @author yuanchengman
 * @date 2021-01-25
 */
public class StudentDTO {
    @Desensitized(type = SensitiveTypeEnum.CHINESE_NAME)
    private String name;
    private int gender;
    private List<Book> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
