package pers.ycm.sbdefault.formatter;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import pers.ycm.sbdefault.desensitized.Desensitized;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuanchengman
 * @date 2021-01-25
 */
public class DesensitizedAnnotationFormatterFactory implements AnnotationFormatterFactory<Desensitized> {
    @Override
    public Set<Class<?>> getFieldTypes() {
        Set<Class<?>> hashSet = new HashSet<>();
        hashSet.add(String.class);
        return hashSet;
    }

    @Override
    public Printer<?> getPrinter(Desensitized desensitized, Class<?> aClass) {
        return getFormatter(desensitized);
    }

    @Override
    public Parser<?> getParser(Desensitized desensitized, Class<?> aClass) {
        return getFormatter(desensitized);
    }

    private DesensitizedFormatter getFormatter(Desensitized desensitized) {
        DesensitizedFormatter formatter = new DesensitizedFormatter();
        formatter.setTypeEnum(desensitized.type());
        return formatter;
    }
}
