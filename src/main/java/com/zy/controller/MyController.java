package com.zy.controller;


import com.zy.beans.Student;
import com.zy.beans.Teacher;
import com.zy.service.StudentService;
import com.zy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MyController {
    private final StudentService studentService;
    private final TeacherService teacherService;

    @Autowired
    public MyController(StudentService studentService,  TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }
    @PostMapping("/Login.action")
    public String toLogin(
            @RequestParam("account") String account,
            @RequestParam("password") String password,
            @RequestParam("actor") Integer actor,
            Model model, HttpSession session
    ) {
        if(actor == 0) {
            Student student = studentService.queryStudentByNo(account);
            if (student == null) {
                model.addAttribute("msg", "账号不存在登录失败");

                return "views/login";
            }
            if (!password.equals(student.getPasswd())) {
                model.addAttribute("msg", "密码错误登录失败");

                return "views/login";
            }
            session.setAttribute("login_student", student);

            return "redirect:/Student/Appointment";

        }
        else if (actor == 1) {
            Teacher teacher = teacherService.queryTeacherByNo(account);
            if (teacher == null) {
                model.addAttribute("msg", "账号不存在登录失败");

                return "views/login";
            }
            if (!password.equals(teacher.getPasswd())) {
                model.addAttribute("msg", "密码错误登录失败");

                return "views/login";
            }
            session.setAttribute("login_teacher",teacher);
            return "redirect:/Teacher/AddCourse";
        }
        else if (actor == 2){
            if (account.equals("admin") && password.equals("123456")){

                session.setAttribute("login_root", account);
                return "redirect:/Root/StudentList";
            }else{
                model.addAttribute("msg", "密码错误登录失败");
                return "views/login";
            }

        }
        else {
            model.addAttribute("msg", "密码错误登录失败");
            return "views/login";
        }

    }

}














