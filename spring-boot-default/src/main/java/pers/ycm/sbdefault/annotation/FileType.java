package pers.ycm.sbdefault.annotation;

import java.lang.annotation.*;

/**
 * 校验文件类型
 *
 * @author yuanchengman
 * @date 2021-01-25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FileType {
    String[] value();
}
