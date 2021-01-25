package pers.ycm.sbdefault.annotation;

import java.lang.annotation.*;

/**
 * 标记的字段，若值为0，则会转换成null
 *
 * @author yuanchengman
 * @date 2021-01-25
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZeroToNull {
}
