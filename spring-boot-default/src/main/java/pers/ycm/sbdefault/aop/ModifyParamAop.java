package pers.ycm.sbdefault.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yuanchengman
 * @date 2021-01-26
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModifyParamAop {
    /**
     * 要修改的字段名。
     *
     * @return string [ ]
     */
    public String[] fieldNames();
}
