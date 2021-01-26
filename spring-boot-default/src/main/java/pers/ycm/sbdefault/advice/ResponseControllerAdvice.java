package pers.ycm.sbdefault.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import pers.ycm.sbdefault.annotation.Zero2Null;
import pers.ycm.sbdefault.common.vo.ResultVO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzc
 */
@RestControllerAdvice(basePackages = {"pers.ycm.sbdefault.controller"})
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    private static final String ZERO_TO_NULL_PACKAGE_PREFIX = "pers.ycm.sbdefault.*";

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果接口返回的类型本身就是ResultVO那就没有必要进行额外的操作，返回false
        return !returnType.getGenericParameterType().equals(ResultVO.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse response) {
        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResultVO里后，再转换为json字符串响应给前端
                return objectMapper.writeValueAsString(new ResultVO<>(data));
            } catch (JsonProcessingException e) {
                //throw new APIException("");
            }
        }

        try {
//            Object dto = ((BizResult) data).getData();
//            zeroToNullCore(data);
        } catch (Exception e) {
            // do nothing
        }

        // 将原本的数据包装在ResultVO里
        ResultVO<Object> resultVO = new ResultVO<>(data);
        return resultVO;
    }

    /**
     * 将标记了 ZeroToNull 注解的字段 0 转成 null
     *
     * @param obj
     */
    private void zeroToNullCore(Object obj) {
        try {
            Class<?> clazz = obj.getClass();
            // 若实体上有 ZeroToNull 注解
            if (clazz.getPackageName().startsWith(ZERO_TO_NULL_PACKAGE_PREFIX)) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    Class<?> type = field.getType();
                    if (type.equals(List.class)) {
                        Method method = clazz.getMethod("get" + firstToUpper(field.getName()));
                        Object va = method.invoke(obj);
                        if (va == null) {
                            continue;
                        }
                        ArrayList list = (ArrayList) va;
                        for (Object obj2 : list) {
                            zeroToNullCore(obj2);
                        }
                    } else if (field.isAnnotationPresent(Zero2Null.class)) {
                        if (type.equals(Integer.class)) {
                            field.setAccessible(true);
                            Integer val = (Integer) field.get(obj);
                            if (Integer.valueOf(0).equals(val)) {
                                field.set(obj, null);
                            }
                        }
                        if (type.equals(Long.class)) {
                            field.setAccessible(true);
                            Long val = (Long) field.get(obj);
                            if (Long.valueOf(0L).equals(val)) {
                                field.set(obj, null);
                            }
                        }
                    }
                }

                Class<?> superclass = clazz.getSuperclass();
                if (superclass != null) {
                    zeroToNullCore(obj, superclass);
                }
            }
        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
    }

    private void zeroToNullCore(Object obj, Class<?> clazz) {
        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Class<?> type = field.getType();
                if (type.equals(List.class)) {
                    Method method = clazz.getMethod("get" + firstToUpper(field.getName()));
                    Object va = method.invoke(obj);
                    if (va == null) {
                        continue;
                    }
                    ArrayList list = (ArrayList) va;
                    for (Object obj2 : list) {
                        zeroToNullCore(obj2);
                    }
                } else if (field.isAnnotationPresent(Zero2Null.class)) {
                    if (type.equals(Integer.class)) {
                        field.setAccessible(true);
                        Integer val = (Integer) field.get(obj);
                        if (Integer.valueOf(0).equals(val)) {
                            field.set(obj, null);
                        }
                    }
                    if (type.equals(Long.class)) {
                        field.setAccessible(true);
                        Long val = (Long) field.get(obj);
                        if (Long.valueOf(0L).equals(val)) {
                            field.set(obj, null);
                        }
                    }
                }
            }
        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
    }

    private String firstToUpper(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
    }
}
