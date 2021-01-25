package pers.ycm.sbdefault.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.ycm.sbdefault.formatter.DesensitizedAnnotationFormatterFactory;
import pers.ycm.sbdefault.interceptor.FileTypeInterceptor;

/**
 * @author yuanchengman
 * @date 2021-01-25
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Autowired
    private FileTypeInterceptor fileTypeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(fileTypeInterceptor).addPathPatterns("/file/*").excludePathPatterns("/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(new DesensitizedAnnotationFormatterFactory());
    }

//    /**
//     * 配置fastjson为默认JSON转换
//     *
//     * @return
//     */
//    @Bean
//    public HttpMessageConverters fastJsonHttpMessageConverters() {
//        // 1.定义一个converters转换消息的对象
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        // 2.添加fastjson的配置信息，比如: 是否需要格式化返回的json数据
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        // 添加自己写的拦截器
//        fastJsonConfig.setSerializeFilters(new DesensitizeValueFilter());
//        // 3.在converter中添加配置信息
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        // 4.将converter赋值给HttpMessageConverter
//        HttpMessageConverter<?> converter = fastConverter;
//        // 5.返回HttpMessageConverters对象
//        return new HttpMessageConverters(converter);
//    }
}
