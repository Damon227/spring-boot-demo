package pers.ycm.sbdefault.aop;

import java.lang.annotation.*;

/**
 * 修改方法返回值
 *
 * @author yuanchengman
 * @date 2021-01-29
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ModifyReturnAop {
}
