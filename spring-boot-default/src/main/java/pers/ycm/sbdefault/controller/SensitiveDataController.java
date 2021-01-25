package pers.ycm.sbdefault.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ycm.sbdefault.pojo.dto.StudentDTO;

/**
 * @author yuanchengman
 * @date 2021-01-25
 */
@RestController
@RequestMapping("/sensitivedata")
public class SensitiveDataController {
    @GetMapping("/get")
    public StudentDTO get() {
        StudentDTO dto = new StudentDTO();
        dto.setName("失也好二");
        return dto;
    }
}
