package pers.ycm.sbdefault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author yuanchengman
 * @date 2021-01-14
 */
@SpringBootApplication(scanBasePackages = {"pers.ycm.*"})
@ServletComponentScan(basePackages = "pers.ycm.")
public class ServiceInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ServiceInitializer.class, args);
    }
}
