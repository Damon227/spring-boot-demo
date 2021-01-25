package pers.ycm.sbdefault.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.ycm.sbdefault.filter.SensitiveDataFilter;

/**
 * @author yuanchengman
 * @date 2021-01-25
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<SensitiveDataFilter> traceFilter() {
        FilterRegistrationBean<SensitiveDataFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new SensitiveDataFilter());
        registration.addUrlPatterns("/*");
        registration.setName("sensitiveDataFilter");
        registration.setOrder(1);
        return registration;
    }
}
