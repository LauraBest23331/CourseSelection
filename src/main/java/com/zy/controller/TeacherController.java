package com.zy.controller;


import com.zy.beans.Course;
import com.zy.beans.Feedback;
import com.zy.beans.Teacher;
import com.zy.service.CourseService;
import com.zy.service.StudentService;
import com.zy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Controller
@RequestMapping("/Teacher")
public class TeacherController {
    private final TeacherService teacherService;
    private final CourseService courseService;
    private final StudentService studentService;
    @Autowired
    public TeacherController(TeacherService teacherService, CourseService courseService, StudentService studentService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @RequestMapping("/AddCourse")
    public String addCourse(HttpSession session, Model model) {
        Teacher teacher = (Teacher) session.getAttribute("login_teacher");
        model.addAttribute("login_teacher", teacher);
        return "teacher_views/add_course";
    }
    @RequestMapping("/Check")
    public String toCheck(HttpSession session, Model model) {
        Teacher teacher = (Teacher) session.getAttribute("login_teacher");
        model.addAttribute("login_teacher", teacher);
        List<Map<String, Object>> maps = teacherService.queryCourseSelect(teacher.getTeacherno());

        model.addAttribute("select_list", maps);
        return "teacher_views/check";
    }
    @RequestMapping("/MyCourse")
    public String toMyCourse(HttpSession session, Model model) {

        Teacher teacher = (Teacher) session.getAttribute("login_teacher");
        model.addAttribute("alter_msg",session.getAttribute("alter_msg"));
        model.addAttribute("login_teacher", teacher);
        model.addAttribute("course_list", teacherService.queryCoursesAll(teacher.getTeacherno()));
        session.removeAttribute("alter_msg");
        return "teacher_views/my_course";
    }
    @RequestMapping("/Feedback")
    public String toFeedback(HttpSession session, Model model) {
        Teacher login_teacher = (Teacher) session.getAttribute("login_teacher");
        model.addAttribute("login_teacher", login_teacher);
        List<Feedback> feedback_list = teacherService.queryFeedback(login_teacher.getTeacherno());
        model.addAttribute("feedback_list", feedback_list);

        return "teacher_views/feedback";
    }
    @PostMapping("/addCourse.do")
    public String doAddCourse(@RequestParam(name = "courseName") String courseName,
                              @RequestParam(name = "courseNo") String courseNo,
                              @RequestParam(name = "beginTime") String beginTime,
                              @RequestParam(name = "maxNumber") Integer maxNumber,
                              HttpSession session, Model model) {
        Teacher teacher = (Teacher) session.getAttribute("login_teacher");
        model.addAttribute("login_teacher", teacher);
        if (teacherService.queryCourseByNo(courseNo) != null) {
            model.addAttribute("alter_msg", "添加失败，课程已经存在！");
            return "teacher_views/add_course";
        }else{
            courseService.insetCourse(new Course(courseName, courseNo, teacher.getUsername(),
                    beginTime,teacher.getTeacherno(), maxNumber, 0, 0));
            return "redirect:/Teacher/AddCourse";
        }

    }
    @PostMapping("/addCourse.action")
    public String doAddCourse1(@RequestParam(name = "courseName") String courseName,
                              @RequestParam(name = "courseNo") String courseNo,
                              @RequestParam(name = "beginTime") String beginTime,
                              @RequestParam(name = "maxNumber") Integer maxNumber,
                              HttpSession session, Model model) {
        session.removeAttribute("alter_msg");
        Teacher teacher = (Teacher) session.getAttribute("login_teacher");
        model.addAttribute("login_teacher", teacher);

        if (teacherService.queryCourseByNo(courseNo) != null) {
            session.setAttribute("alter_msg", "添加失败，课程已经存在！");
        }else{
            courseService.insetCourse(new Course(courseName, courseNo, teacher.getUsername(),
                    beginTime,teacher.getTeacherno(), maxNumber, 0 , 0));
        }

        return "redirect:/Teacher/MyCourse";

    }
    @PostMapping("/AlterCourse.action")
    public String alterCourse(@RequestParam(name = "courseName") String courseName,
                              @RequestParam(name = "courseNo") String courseNo,
                              @RequestParam(name = "beginTime") String beginTime,
                              @RequestParam(name = "maxNumber") Integer maxNumber,
                              @RequestParam(name = "nowNumber") Integer nowNumber,
                              @RequestParam(name = "isCheck") Integer isCheck,
                              HttpSession session, Model model) {
        Teacher login_teacher = (Teacher) session.getAttribute("login_teacher");
        teacherService.deleteCourse(courseNo, login_teacher.getTeacherno());
        courseService.insetCourse(new Course(courseName, courseNo, login_teacher.getUsername()
                , beginTime,login_teacher.getTeacherno(), maxNumber, nowNumber, isCheck));

        return "redirect:/Teacher/MyCourse";
    }
    @GetMapping("/DeleteCourse")
    public String deleteCourse(@RequestParam(name = "courseNo") String courseNo,
                               HttpSession session) {
        Teacher login_teacher = (Teacher) session.getAttribute("login_teacher");
        teacherService.deleteCourse(courseNo, login_teacher.getTeacherno());
        return "redirect:/Teacher/MyCourse";
    }

    @RequestMapping("/Logout")
    public String teacherLogout(HttpSession session) {
        session.removeAttribute("login_teacher");
        return "redirect:/Login";
    }
}














