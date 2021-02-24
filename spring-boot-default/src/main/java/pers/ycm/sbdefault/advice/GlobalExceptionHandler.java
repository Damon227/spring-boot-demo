package pers.ycm.sbdefault.advice;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.ycm.sbdefault.common.enums.CodeEnum;
import pers.ycm.sbdefault.common.vo.ResultVO;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yuanchengman
 * @date 2021-02-24
 */
@RestControllerAdvice(basePackages = {"pers.ycm.sbdefault.controller"})
public class GlobalExceptionHandler {
    private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        LOGGER.error("ValidateErr", e);

        BindingResult result = e.getBindingResult();

        List<String> list = new LinkedList<>();
        result.getFieldErrors().forEach(error -> {
            String field = error.getField();
            Object value = error.getRejectedValue();
            String msg = error.getDefaultMessage();
            list.add(field + " " + msg);
        });

        // 然后提取错误提示信息进行返回
        ResultVO bizResult = new ResultVO(CodeEnum.SYSTEM_PARAM_ERROR.getCode(), StringUtils.join(list, ","));
        return new ResultVO<>(bizResult);
    }
}
