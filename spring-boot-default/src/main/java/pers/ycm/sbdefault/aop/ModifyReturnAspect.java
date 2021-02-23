package pers.ycm.sbdefault.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import pers.ycm.sbdefault.desensitized.Desensitized;
import pers.ycm.sbdefault.desensitized.DesensitizedUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yuanchengman
 * @date 2021-01-29
 */
@Aspect
@Component
public class ModifyReturnAspect {

    @Pointcut("@annotation(pers.ycm.sbdefault.aop.ModifyReturnAop)")
    private void pointcut() {
    }

    @AfterReturning(value = "pointcut() && @annotation(modifyReturnAop)", returning = "returnValue")
    public void after(JoinPoint joinPoint, Object returnValue, ModifyReturnAop modifyReturnAop) {
        try {
            changeValue(returnValue);
        } catch (Exception e) {

        }
    }

    private void changeValue(Object obj) throws Exception {
        //基本类型不作操作
        if (obj instanceof Map) {
            //changeMapValue(obj);
        } else if (obj instanceof List) {
            @SuppressWarnings("unchecked")
            List<Object> list = (List<Object>) obj;
            for (Object _obj : list) {
                if (_obj instanceof Map) {
                    //changeMapValue(_obj);
                } else {
                    changObjectValue(_obj);
                }
            }
        } else {
            changObjectValue(obj);
        }
    }

    private void changObjectValue(Object obj) throws Exception {
        Class<?> resultClz = obj.getClass();
        // 获取class里的所有字段  父类字段获取不到    注：如果出现加密解密失败 请先查看idno是否在父类中
        Field[] fieldInfo = resultClz.getDeclaredFields();
        for (Field field : fieldInfo) {
            Desensitized desensitized = field.getAnnotation(Desensitized.class);
            if (desensitized != null) {
                //成员变量为private,故必须进行此操
                field.setAccessible(true);
                Object fieldValue = field.get(obj);
                String afterValue = getAfterValue(desensitized, fieldValue);
                if (Objects.nonNull(afterValue)) {
                    field.set(obj, afterValue);
                }
            }
        }
    }

    private String getAfterValue(Desensitized desensitized, Object originValue) {
        return DesensitizedUtils.desensivize(desensitized.type(), (String) originValue);
    }
}
