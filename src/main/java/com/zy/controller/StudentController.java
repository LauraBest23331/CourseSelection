package com.zy.controller;


import com.zy.beans.Course;
import com.zy.beans.Student;
import com.zy.service.CourseService;
import com.zy.service.StudentService;
import com.zy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/Student")
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;
    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @RequestMapping("/Appointment")
    public String toAppointment(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("login_student");

        model.addAttribute("login_student", student);
        List<Course> courses = courseService.queryCoursesAll();
        model.addAttribute("courses_list", courses);

        return "user_views/appointment";
    }

    @RequestMapping("/Check")
    public String toCheck(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("login_student");
        model.addAttribute("login_student", student);
        List<Course> courses = studentService.queryCoursesAll(student.getStudentno());
        model.addAttribute("select_list", courses);
        return "user_views/check";
    }
    @RequestMapping("/Information")
    public String toInformation(Model model, HttpSession session) {
        model.addAttribute("login_student", session.getAttribute("login_student"));
        return "user_views/information";
    }
    @RequestMapping("/Feedback")
    public String toFeedback(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("login_student");
        model.addAttribute("login_student", student);
        List<Course> course_list = studentService.queryCoursesAll(student.getStudentno());
        model.addAttribute("course_list", course_list);
        return "user_views/feedback";
    }
    @RequestMapping("/Logout")
    public String toLogout(HttpSession session) {
        session.removeAttribute("login_student");

        return "redirect:/Login";
    }
    @PostMapping("/deleteSelect")
    public String deleteSelect(@RequestParam(name = "courseNos[]") String[] courseNos, HttpSession session) {
        for (String item:
             courseNos) {
            studentService.deleteSelectByNo(item, ((Student)session.getAttribute("login_student")).getStudentno());
            courseService.reduceNumber(item);
        }
        return "redirect:/Student/Check";
    }
    @PostMapping("/Feedback.do")
    public String doFeedback(@RequestParam(name = "words") String words,
                             @RequestParam(name = "courseNo") String courseNo,
                             HttpSession session) {
        Student login_student = (Student) session.getAttribute("login_student");
        studentService.addFeedBack(login_student.getStudentno(), courseNo, words);

        return "redirect:/Student/Feedback";
    }
    @PostMapping("/updateStudent")
    public String updateStudent(@RequestParam(name = "username") String username,
                                @RequestParam(name = "age") Integer age,
                                @RequestParam(name = "sex") String sex,
                                @RequestParam(name = "passwd") String passwd,
                                HttpSession session) {
        String no = ((Student)session.getAttribute("login_student")).getStudentno();
        studentService.updateStudent(new Student(no, username, passwd, sex, age));
        session.setAttribute("login_student", studentService.queryStudentByNo(no));
        return "redirect:/Student/Information";
    }
}
