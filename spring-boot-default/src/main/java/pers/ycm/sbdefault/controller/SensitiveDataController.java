package pers.ycm.sbdefault.controller;

import cn.hutool.core.collection.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ycm.sbdefault.aop.ModifyReturnAop;
import pers.ycm.sbdefault.pojo.dto.Book;
import pers.ycm.sbdefault.pojo.dto.StudentDTO;

import java.math.BigDecimal;

/**
 * @author yuanchengman
 * @date 2021-01-25
 */
@RestController
@RequestMapping("/sensitivedata")
public class SensitiveDataController {

    @Autowired
    private SensitiveDataController self;

    @GetMapping("/get")
    @ModifyReturnAop
    public StudentDTO get() {
        StudentDTO dto = self.getStudentDTO();

        return dto;
    }

    public StudentDTO getStudentDTO(){
        StudentDTO student = new StudentDTO();
        student.setName("失也好二");
        student.setBooks(ListUtil.of(
                new Book("语文", BigDecimal.valueOf(11)),
                new Book("英语", BigDecimal.valueOf(12))));
        return student;
    }
}
