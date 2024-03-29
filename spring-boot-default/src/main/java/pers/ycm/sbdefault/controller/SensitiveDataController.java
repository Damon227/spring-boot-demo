package pers.ycm.sbdefault.controller;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.ycm.sbdefault.aop.ModifyReturnAop;
import pers.ycm.sbdefault.pojo.dto.Book;
import pers.ycm.sbdefault.pojo.dto.StudentDTO;
import pers.ycm.sbdefault.pojo.request.CreateStudentRequest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author yuanchengman
 * @date 2021-01-25
 */
@RestController
@RequestMapping("/sensitivedata")
public class SensitiveDataController {

    @Resource
    private SensitiveDataController self;

    @GetMapping("/get")
    @ModifyReturnAop
    public StudentDTO get() {
        StudentDTO dto = self.getStudentDTO();
        // master 1
        // master 2
        // master 3
        // master 5
        //dev 4
        //dev 6
        // master7
        //dev8
        //dev9
        return dto;
    }

    @PostMapping("/test")
    public void test(@RequestBody StudentDTO request){
        System.out.println(JSON.toJSONString(request));
        //dev11
        //dev12
        //dev13
        //dev14
        //dev15
        //dev16
        //dev17
    }

    @PostMapping("/create")
    public StudentDTO create(@RequestBody @Validated CreateStudentRequest request){
        return self.getStudentDTO();
    }

    public StudentDTO getStudentDTO(){
        StudentDTO student = new StudentDTO();
        student.setName("失也好二");
        student.setBooks(ListUtil.of(
                new Book("语文", BigDecimal.valueOf(11), LocalDateTime.now()),
                new Book("英语master", BigDecimal.valueOf(13), LocalDateTime.now())));
        return student;
    }
}
