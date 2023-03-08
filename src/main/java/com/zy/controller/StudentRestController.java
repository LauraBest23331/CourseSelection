package com.zy.controller;


import com.zy.beans.Course;
import com.zy.beans.Result;
import com.zy.beans.Student;
import com.zy.service.CourseService;
import com.zy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class StudentRestController {
    private final StudentService studentService;
    private final CourseService courseService;
    @Autowired
    public StudentRestController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }
    @PostMapping("/addCourseSelection")
    public Result addCourseSelection(@RequestParam(name = "courseNos[]") String[] courseNos,
                                     HttpSession session) {
        int count = 0;
        int recount = 0;
        int code = 0;
        Student login_student = (Student) session.getAttribute("login_student");
        for (String item:
             courseNos) {
            if (studentService.queryCourseByNo(item, login_student.getStudentno())== 0){
                count += studentService.makeAppointment(item, login_student.getStudentno());
                Course course = courseService.queryCourseByNo(item);
                if (course.getMaxNumber().intValue() != course.getNowNumber().intValue()) {
                    courseService.addNumber(item);
                }
                else {
                    return new Result("选课失败, 课程已满", -1);
                }
            }else {
                recount++;

            }

        }
        if (count == 0) {
            code = -1;
        }
        return new Result("选课成功"+ count +"门, 重复选择了"+recount+"门", code);
    }
}
















