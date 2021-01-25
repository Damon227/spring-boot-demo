package pers.ycm.sbdefault.common.vo;

/**
 * @author yuanchengman
 * @date 2021-01-25
 */
public class ResultVO<T> {
    /**
     * 状态码，比如1代表响应成功
     */
    public Integer code = 1;
    /**
     * 响应信息，用来说明响应情况
     */
    public String msg;
    /**
     * 响应的具体数据
     */
    public T data;

    public ResultVO() {

    }

    public ResultVO(T data) {
        this(1, "success", data);
    }

    public ResultVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
