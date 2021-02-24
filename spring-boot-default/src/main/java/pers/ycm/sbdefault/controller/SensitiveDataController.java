package pers.ycm.sbdefault.controller;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.ycm.sbdefault.aop.ModifyReturnAop;
import pers.ycm.sbdefault.pojo.dto.Book;
import pers.ycm.sbdefault.pojo.dto.StudentDTO;
import pers.ycm.sbdefault.pojo.request.CreateStudentRequest;

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

    @PostMapping("/test")
    public void test(@RequestBody StudentDTO request){
        System.out.println(JSON.toJSONString(request));
    }

    public StudentDTO getStudentDTO(){
        StudentDTO student = new StudentDTO();
        student.setName("失也好二");
        student.setBooks(ListUtil.of(
                new Book("语文", BigDecimal.valueOf(11)),
                new Book("英语", BigDecimal.valueOf(12))));
        return student;
    }

    @PostMapping("/create")
    public StudentDTO create(@RequestBody @Validated CreateStudentRequest request){
        return self.getStudentDTO();
    }
}
