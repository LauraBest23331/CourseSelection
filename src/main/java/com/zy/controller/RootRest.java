package com.zy.controller;


import com.zy.beans.Result;
import com.zy.beans.Student;
import com.zy.beans.Teacher;
import com.zy.service.StudentService;
import com.zy.service.TeacherService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Root")
public class RootRest {
    private final StudentService studentService;
    private final TeacherService teacherService;

    public RootRest(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }
    @PostMapping("/GetStudentByNo")
    public Student getUserByNo(@RequestParam(name="studentNo") String id) {
        return studentService.queryStudentByNo(id);
    }
    @PostMapping("/GetTeacherByNo")
    public Teacher getTeacherByNo(@RequestParam(name="teacherNo") String id) {
        return teacherService.queryTeacherByNo(id);
    }
    @PostMapping("/isTeacherExist")
    public Result isTeacherExist(@RequestParam(name="teacherNo") String teacherNo) {
        if (teacherService.queryTeacherByNo(teacherNo) == null) {
            return new Result("账号不存在", 0);
        }
        else {
            return new Result("账号已经存在", -1);
        }
    }
    @PostMapping("/isStudentExist")
    public Result isStudentExist(@RequestParam(name="teacherNo") String teacherNo) {
        if (studentService.queryStudentByNo(teacherNo) == null) {
            return new Result("账号不存在", 0);
        }
        else {
            return new Result("账号已经存在", -1);
        }
    }


}
