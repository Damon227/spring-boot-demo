package pers.ycm.sbdefault.pojo.dto;

import lombok.Data;
import pers.ycm.sbdefault.annotation.Zero2Null;
import pers.ycm.sbdefault.common.enums.SensitiveTypeEnum;
import pers.ycm.sbdefault.desensitized.Desensitized;

import java.util.List;

/**
 * @author yuanchengman
 * @date 2021-01-25
 */
@Data
public class StudentDTO {
    @Desensitized(type = SensitiveTypeEnum.CHINESE_NAME)
    private String name;
    @Zero2Null
    private Integer gender;
    private List<Book> books;

    public StudentDTO() {
    }

    public StudentDTO(String name) {
        this.name = name;
    }
}
