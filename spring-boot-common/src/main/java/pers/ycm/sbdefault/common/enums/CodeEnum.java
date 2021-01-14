package pers.ycm.sbdefault.common.enums;

/**
 * 错误码枚举<br/>
 * 系统错误码：[1,1000)<br/>
 * 业务错误码：[1001,5000)<br/>
 *
 * @author yuanchengman
 * @date 2021-01-14
 */
public enum CodeEnum implements CommonEnum {
    // 系统错误码
    /**
     * 参数错误
     */
    SYSTEM_PARAM_ERROR(1, "参数错误"),

    // 业务错误码

    BIZ_STRATEGY_EMPTY(1001, "至少需要实现一个策略"),
    BIZ_STRATEGY_NAME_NOT_EXIST(1002, "策略名称无法找到对应的策略实现");

    private int code;
    private String desc;

    CodeEnum(int code, String desc) {
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
