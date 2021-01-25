package pers.ycm.sbdefault.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pers.ycm.sbdefault.common.enums.SensitiveTypeEnum;
import pers.ycm.sbdefault.serializer.DesensitizedSerializer;

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
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizedSerializer.class)
public @interface Desensitized {
    SensitiveTypeEnum type();
}
