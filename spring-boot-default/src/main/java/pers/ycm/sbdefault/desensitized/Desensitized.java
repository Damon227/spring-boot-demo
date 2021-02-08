package pers.ycm.sbdefault.desensitized;

import pers.ycm.sbdefault.common.enums.SensitiveTypeEnum;

import java.lang.annotation.*;


/**
 * @author yuanchengman
 * @date 2021-01-25
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
//若用fastjson，则不需要下面两个注解
//@JacksonAnnotationsInside
//@JsonSerialize(using = DesensitizedSerializer.class)
public @interface Desensitized {
    SensitiveTypeEnum type();
}
