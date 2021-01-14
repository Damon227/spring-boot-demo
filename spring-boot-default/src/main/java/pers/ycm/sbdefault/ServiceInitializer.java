package pers.ycm.sbdefault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yuanchengman
 * @date 2021-01-14
 */
@SpringBootApplication(scanBasePackages = {"pers.ycm.*"})
public class ServiceInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ServiceInitializer.class, args);
    }
}
