package pers.ycm.sbdefault.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pers.ycm.sbdefault.serializer.DesensitizedSerializer;

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
//若用fastjson，则不需要下面两个注解
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizedSerializer.class)
public @interface Zero2Null {
}
