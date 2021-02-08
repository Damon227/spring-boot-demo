package pers.ycm.sbdefault.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import pers.ycm.sbdefault.annotation.Zero2Null;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * convert 2 to null 序列化器
 *
 * @author yuanchengman
 * @date 2021-01-26
 */
public class Zero2NullSerializer extends JsonSerializer<Object> implements ContextualSerializer {

    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNull();
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        // 为空直接跳过
        if (beanProperty != null) {
            // 非数据类直接跳过
            if (Objects.equals(beanProperty.getType().getRawClass(), Integer.class)
                    || Objects.equals(beanProperty.getType().getRawClass(), Long.class)
                    || Objects.equals(beanProperty.getType().getRawClass(), Float.class)
                    || Objects.equals(beanProperty.getType().getRawClass(), Double.class)
                    || Objects.equals(beanProperty.getType().getRawClass(), BigDecimal.class)
            ) {
                Zero2Null zero2Null = beanProperty.getAnnotation(Zero2Null.class);
                if (zero2Null == null) {
                    zero2Null = beanProperty.getContextAnnotation(Zero2Null.class);
                }

                // 如果能得到注解，就将注解的 value 传入 SensitiveInfoSerialize
                if (zero2Null != null) {
                    return new Zero2NullSerializer();
                }
            }

            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }

        return serializerProvider.findNullValueSerializer(beanProperty);
    }
}
