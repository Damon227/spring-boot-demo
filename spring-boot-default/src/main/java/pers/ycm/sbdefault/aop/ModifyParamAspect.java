package pers.ycm.sbdefault.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.ycm.sbdefault.pojo.entity.User;
import pers.ycm.sbdefault.service.UserService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 参考文档：https://www.cnblogs.com/azhqiang/p/10231507.html
 *
 * @author yuanchengman
 * @date 2021-01-26
 */
@Aspect
@Component
public class ModifyParamAspect {

    @Autowired
    private UserService userService;

    private User user;

    @Pointcut("@annotation(pers.ycm.sbdefault.aop.ModifyParamAop)")
    private void pointcut() {
    }

    @Before("pointcut() && @annotation(modifyParamAop)")
    public void before(JoinPoint joinPoint, ModifyParamAop modifyParamAop) {
        String[] fieldNames = modifyParamAop.fieldNames();
        // 返回被织入增强处理的目标对象       getThis：返回AOP框架为目标对象生成的代理对象
        Object target = joinPoint.getTarget();
        // 获取目标对象方法参数
        Object[] args = joinPoint.getArgs();

        // 遍历参数 修改特定字段对象的值 （map list<domain> domain）
        for (Object obj : args) {
            try {
                changeValue(obj, fieldNames);
            } catch (Exception e) {

            }
        }

        System.out.println(this.hashCode());
    }

    private void changeValue(Object obj, String[] fieldNames) throws Exception {
        //基本类型不作操作
        if (obj instanceof Map) {
            changeMapValue(obj, fieldNames);
        } else if (obj instanceof List) {
            @SuppressWarnings("unchecked")
            List<Object> list = (List<Object>) obj;
            for (Object _obj : list) {
                if (_obj instanceof Map) {
                    changeMapValue(_obj, fieldNames);
                } else {
                    changObjectValue(_obj, fieldNames);
                }
            }
        } else {
            changObjectValue(obj, fieldNames);
        }
    }

    private void changeMapValue(Object obj, String[] fieldNames) throws Exception {
        Map<String, Object> map = (Map<String, Object>) obj;
        for (int i = 0; i < fieldNames.length; i++) {
            if (map.containsKey(fieldNames[i])) {
                Object fieldValue = map.get(fieldNames[i]);
                Object afterValue = getAfterValue(i);
                if (Objects.nonNull(afterValue)) {
                    map.put(fieldNames[i], afterValue);
                }
            }
        }
    }

    private void changObjectValue(Object obj, String[] fieldNames) throws Exception {
        Class<?> resultClz = obj.getClass();
        // 获取class里的所有字段  父类字段获取不到    注：如果出现加密解密失败 请先查看idno是否在父类中
        Field[] fieldInfo = resultClz.getDeclaredFields();
        for (Field field : fieldInfo) {
            for (int i = 0; i < fieldNames.length; i++) {

                if (fieldNames[i].equals(field.getName())) {
                    //成员变量为private,故必须进行此操
                    field.setAccessible(true);
                    Object fieldValue = field.get(obj);
                    Object afterValue = getAfterValue(i);
                    if (Objects.nonNull(afterValue)) {
                        field.set(obj, afterValue);
                    }
                    break;
                }
            }
        }
    }

    private Object getAfterValue(int index) {
        user = userService.getCurrent();
        return userService.getUserCreateTime(index);
    }
}
