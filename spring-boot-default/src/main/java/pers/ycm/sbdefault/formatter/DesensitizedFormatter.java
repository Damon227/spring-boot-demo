package pers.ycm.sbdefault.formatter;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.Formatter;
import pers.ycm.sbdefault.common.enums.SensitiveTypeEnum;
import pers.ycm.sbdefault.desensitized.DesensitizedUtils;

import java.text.ParseException;
import java.util.Locale;

/**
 * 可以将request model的字段转换
 *
 * @author yuanchengman
 * @date 2021-01-25
 */
public class DesensitizedFormatter implements Formatter<String> {
    private SensitiveTypeEnum typeEnum;

    public SensitiveTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(SensitiveTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    @Override
    public String parse(String s, Locale locale) throws ParseException {
        if (StringUtils.isNotBlank(s)) {
            switch (typeEnum) {
                case CHINESE_NAME:
                    s = DesensitizedUtils.chineseName(s);
                    break;
                case ID_CARD:
                    s = DesensitizedUtils.idCardNum(s);
                    break;
                case FIXED_PHONE:
                    s = DesensitizedUtils.fixedPhone(s);
                    break;
                case MOBILE_PHONE:
                    s = DesensitizedUtils.mobilePhone(s);
                    break;
                case ADDRESS:
                    s = DesensitizedUtils.address(s, 8);
                    break;
                case EMAIL:
                    s = DesensitizedUtils.email(s);
                    break;
                case BANK_CARD:
                    s = DesensitizedUtils.bankCard(s);
                    break;
                case PASSWORD:
                    s = DesensitizedUtils.password(s);
                    break;
                case CAR_NUMBER:
                    s = DesensitizedUtils.carNumber(s);
                    break;
                default:
            }
        }
        return s;
    }

    @Override
    public String print(String s, Locale locale) {
        return s;
    }
}
