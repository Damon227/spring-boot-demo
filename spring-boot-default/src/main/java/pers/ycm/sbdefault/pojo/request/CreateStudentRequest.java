package pers.ycm.sbdefault.pojo.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @author yuanchengman
 * @date 2021-02-25
 */
@Data
public class CreateStudentRequest {
    @NotBlank
    @Length(max = 8)
    private String name;

    @Range(max = 120)
    private int age;

    private BigDecimal money;
}
