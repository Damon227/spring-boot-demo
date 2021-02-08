package pers.ycm.sbdefault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author yuanchengman
 * @date 2021-01-14
 */
@SpringBootApplication(scanBasePackages = {"pers.ycm.*"})
@ServletComponentScan(basePackages = "pers.ycm.")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ServiceInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ServiceInitializer.class, args);
    }
}
