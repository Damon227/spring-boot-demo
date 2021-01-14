package pers.ycm.sbdefault.common.exception.enums;

import java.util.EnumSet;
import java.util.Objects;

/**
 * 通用枚举接口，提供方法进行枚举转换。
 *
 * @author yuanchengman
 * @date 2021-01-11
 */
public interface CommonEnum {


    /**
     * 获取枚举值
     *
     * @return
     */
    int getCode();

    /**
     * 获取枚举描述
     *
     * @return
     */
    String getDesc();

    /**
     * 通过枚举值获取枚举
     *
     * @param code  枚举值
     * @param clazz
     * @param <E>
     * @return
     */
    static <E extends Enum<E> & CommonEnum> E getEnum(Integer code, Class<E> clazz) {
        Objects.requireNonNull(code);
        EnumSet<E> allEnums = EnumSet.allOf(clazz);
        E myEnum = allEnums.stream().filter(t -> t.getCode() == code).findFirst().orElseThrow();
        return myEnum;
    }

    /**
     * 通过枚举值获取枚举
     *
     * @param code  枚举值
     * @param clazz
     * @param <E>
     * @return
     */
    static <E extends Enum<E> & CommonEnum> E getEnum(int code, Class<E> clazz) {
        EnumSet<E> allEnums = EnumSet.allOf(clazz);
        E myEnum = allEnums.stream().filter(t -> t.getCode() == code).findFirst().orElseThrow();
        return myEnum;
    }
}
