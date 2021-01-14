package pers.ycm.sbdefault.common.exception;

import pers.ycm.sbdefault.common.enums.CodeEnum;

/**
 * @author yuanchengman
 * @date 2021-01-14
 */
public class BizException extends RuntimeException {
    private int code;

    public int getCode() {
        return code;
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(CodeEnum codeEnum) {
        this(codeEnum.getCode(), codeEnum.getDesc());
    }
}
