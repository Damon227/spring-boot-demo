package pers.ycm.sbdefault.common.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 对象转换工具类
 *
 * @author yuanchengman
 * @date 2021-05-18
 */
public class ModelConvertUtil {
    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static <T> T toModel(Object dto, Class<T> clazz) {
        if (dto == null) {
            return null;
        }
        return modelMapper.map(dto, clazz);
    }

    public static void toModel(Object dto, Object clazz) {
        if (dto != null) {
            modelMapper.map(dto, clazz);
        }
    }

    public static <T> List<T> toModels(List<?> dos, Class<T> clazz) {
        if (CollectionUtils.isEmpty(dos)) {
            return new ArrayList<>();
        }
        return dos.stream()
                .map(obj -> toModel(obj, clazz))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
