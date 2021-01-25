package pers.ycm.sbdefault.pojo.dto;

import pers.ycm.sbdefault.annotation.Desensitized;
import pers.ycm.sbdefault.common.enums.SensitiveTypeEnum;

/**
 * @author yuanchengman
 * @date 2021-01-25
 */
public class StudentDTO {
    @Desensitized(type = SensitiveTypeEnum.CHINESE_NAME)
//    @JsonSerialize(using = DesensitizedSerializer.class)
    private String name;
    private int gender;

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
}
