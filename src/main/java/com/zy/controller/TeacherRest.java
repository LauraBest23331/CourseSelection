package com.zy.controller;


import com.zy.beans.Course;
import com.zy.beans.Result;
import com.zy.beans.Teacher;
import com.zy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/Teacher")
public class TeacherRest {
    private final TeacherService teacherService;

    @Autowired
    public TeacherRest(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/isCourseExist")
    public Result isCourseExist(@RequestParam(name = "courseNo") String courseNo, HttpSession session) {
        Teacher login_teacher = (Teacher) session.getAttribute("login_teacher");
        if (teacherService.queryCourseByNo(courseNo) == null) {
            return new Result("账号不存在", 0);
        }else {

            return new Result("账号存在", -1);
        }
    }
    @PostMapping("/getCourse")
    public Course getCourse(@RequestParam(name = "courseNo") String courseNo, HttpSession session) {
        Teacher login_teacher = (Teacher) session.getAttribute("login_teacher");
        return teacherService.queryCourseByNo(courseNo, login_teacher.getTeacherno());
    }
}
