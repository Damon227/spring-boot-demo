package pers.ycm.sbdefault.common.exception.enums;

/**
 * 数据状态枚举
 *
 * @author yuanchengman
 * @date 2021-01-11
 */
public enum BizStatusEnum implements CommonEnum {
    NONE(0, ""),
    NORMAL(1, "正常"),
    FROZEN(2, "冻结"),
    DELETED(3, "删除");

    private int code;
    private String desc;

    BizStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
