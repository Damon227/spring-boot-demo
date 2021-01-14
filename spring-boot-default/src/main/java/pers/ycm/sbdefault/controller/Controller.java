package pers.ycm.sbdefault.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yuanchengman
 * @date 2021-01-11
 */
@RestController
public class Controller {
    @GetMapping("/invoke")
    public String home() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
