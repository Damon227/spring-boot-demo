package pers.ycm.sbdefault.common.enums;

/**
 * 脱敏类型
 *
 * @author yuanchengman
 * @date 2021-01-25
 */
public enum SensitiveTypeEnum implements CommonEnum{
    /**
     * 中文姓名
     */
    CHINESE_NAME,
    /**
     * 身份证号
     */
    ID_CARD,
    /**
     * 座机号
     */
    FIXED_PHONE,
    /**
     * 手机号
     */
    MOBILE_PHONE,
    /**
     * 地址
     */
    ADDRESS,
    /**
     * 邮箱
     */
    EMAIL,
    /**
     * 银行卡号
     */
    BANK_CARD,
    /**
     * 密码
     */
    PASSWORD,
    /**
     * 车牌号
     */
    CAR_NUMBER;

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getDesc() {
        return null;
    }
}
