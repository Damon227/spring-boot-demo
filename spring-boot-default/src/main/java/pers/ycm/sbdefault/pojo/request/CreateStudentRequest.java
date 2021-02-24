package pers.ycm.sbdefault.pojo.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * @author yuanchengman
 * @date 2021-02-24
 */
@Data
public class CreateStudentRequest {
    /**
     * 姓名
     */
    @NotBlank
    @Length(max = 8)
    private String name;
    /**
     * 年龄
     */
    @Range(min = 10)
    private Integer age;

    private String mobile;
}
