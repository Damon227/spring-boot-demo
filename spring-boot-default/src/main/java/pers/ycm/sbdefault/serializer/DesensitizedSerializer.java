package pers.ycm.sbdefault.serializer;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import pers.ycm.sbdefault.annotation.Desensitized;
import pers.ycm.sbdefault.common.enums.SensitiveTypeEnum;
import pers.ycm.sbdefault.formatter.DesensitizedUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * @author yuanchengman
 * @date 2021-01-25
 */
public class DesensitizedSerializer extends JsonSerializer<String> implements ContextualSerializer {
    private SensitiveTypeEnum typeEnum;

    public DesensitizedSerializer() {
    }

    public DesensitizedSerializer(SensitiveTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        switch (this.typeEnum) {
            case CHINESE_NAME: {
                jsonGenerator.writeString(DesensitizedUtils.chineseName(s));
                break;
            }
            case ID_CARD: {
                jsonGenerator.writeString(DesensitizedUtils.idCardNum(s));
                break;
            }
            case FIXED_PHONE: {
                jsonGenerator.writeString(DesensitizedUtils.fixedPhone(s));
                break;
            }
            case MOBILE_PHONE: {
                jsonGenerator.writeString(DesensitizedUtils.mobilePhone(s));
                break;
            }
            case ADDRESS: {
                jsonGenerator.writeString(DesensitizedUtils.address(s, 4));
                break;
            }
            case EMAIL: {
                jsonGenerator.writeString(DesensitizedUtils.email(s));
                break;
            }
            case BANK_CARD: {
                jsonGenerator.writeString(DesensitizedUtils.bankCard(s));
                break;
            }
            default:
                break;
        }
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
                    return new DesensitizedSerializer(desensitized.type());
                }
            }

            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }

        return serializerProvider.findNullValueSerializer(beanProperty);
    }
}
