package pers.ycm.sbdefault.filter;

import com.alibaba.fastjson.serializer.ValueFilter;
import pers.ycm.sbdefault.desensitized.Desensitized;
import pers.ycm.sbdefault.common.enums.SensitiveTypeEnum;
import pers.ycm.sbdefault.desensitized.DesensitizedUtils;

import java.lang.reflect.Field;

/**
 * @author yuanchengman
 * @date 2021-01-25
 */
public class DesensitizeValueFilter implements ValueFilter {
    @Override
    public Object process(Object object, String name, Object value) {
        if (null == value || !(value instanceof String) || ((String) value).length() == 0) {
            return value;
        }
        try {
            Field field = object.getClass().getDeclaredField(name);
            Desensitized desensitization;
            if (String.class != field.getType() || (desensitization = field.getAnnotation(Desensitized.class)) == null) {
                return value;
            }
            String valueStr = (String) value;
            SensitiveTypeEnum type = desensitization.type();
            switch (type) {
                case CHINESE_NAME:
                    return DesensitizedUtils.chineseName(valueStr);
                case ID_CARD:
                    return DesensitizedUtils.idCardNum(valueStr);
                case FIXED_PHONE:
                    return DesensitizedUtils.fixedPhone(valueStr);
                case MOBILE_PHONE:
                    return DesensitizedUtils.mobilePhone(valueStr);
                case ADDRESS:
                    return DesensitizedUtils.address(valueStr, 8);
                case EMAIL:
                    return DesensitizedUtils.email(valueStr);
                case BANK_CARD:
                    return DesensitizedUtils.bankCard(valueStr);
                case PASSWORD:
                    return DesensitizedUtils.password(valueStr);
                case CAR_NUMBER:
                    return DesensitizedUtils.carNumber(valueStr);
                default:
            }
        } catch (NoSuchFieldException e) {
            return value;
        }
        return value;
    }
}
