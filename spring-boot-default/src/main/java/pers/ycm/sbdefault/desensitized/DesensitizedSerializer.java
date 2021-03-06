package pers.ycm.sbdefault.desensitized;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import pers.ycm.sbdefault.common.enums.SensitiveTypeEnum;
import pers.ycm.sbdefault.config.BeanContext;
import pers.ycm.sbdefault.service.UserService;

import java.io.IOException;
import java.util.Objects;

/**
 * 数据脱敏序列化器
 *
 * @author yuanchengman
 * @date 2021-01-25
 */
public class DesensitizedSerializer extends JsonSerializer<String> implements ContextualSerializer {
    private SensitiveTypeEnum typeEnum;

    private UserService userService;

    public DesensitizedSerializer() {
    }

    public DesensitizedSerializer(SensitiveTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public DesensitizedSerializer(SensitiveTypeEnum typeEnum, UserService userService) {
        this(typeEnum);
        this.userService = userService;
    }

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(DesensitizedUtils.desensivize(this.typeEnum, s));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        // 为空直接跳过
        if (beanProperty != null) {
            // 非 String 类直接跳过
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                Desensitized desensitized = beanProperty.getAnnotation(Desensitized.class);
                if (desensitized == null) {
                    desensitized = beanProperty.getContextAnnotation(Desensitized.class);
                }

                // 如果能得到注解，就将注解的 value 传入 SensitiveInfoSerialize
                if (desensitized != null) {
                    UserService userService = BeanContext.getBean(UserService.class);
                    return new DesensitizedSerializer(desensitized.type(), userService);
                }
            }

            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }

        return serializerProvider.findNullValueSerializer(beanProperty);
    }
}
