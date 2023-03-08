package com.zy.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zy.beans.Course;
import com.zy.beans.Feedback;
import com.zy.beans.Student;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Root")
public class RootController {
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final CourseService courseService;
    @Autowired
    public RootController(StudentService studentService, TeacherService teacherService, CourseService courseService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @RequestMapping("/StudentList")
    public String showStudentList(Model model, HttpSession session) {
        List<Student> student_list = studentService.getUserList();
        model.addAttribute("user_list", student_list);
        model.addAttribute("error_msg", session.getAttribute("error_msg"));
        session.removeAttribute("error_msg");
        return "views/student_list";
    }
    @RequestMapping("/TeacherList")
    public String showTeacherList(Model model, HttpSession session) {
        List<Teacher> teacher_list = teacherService.getTeachersAll();
        model.addAttribute("user_list", teacher_list);
        model.addAttribute("error_msg", session.getAttribute("error_msg"));
        session.removeAttribute("error_msg");
        return "views/teacher_list";
    }
    @RequestMapping("/CourseSelectList")
    public String showCourseSelectList(Model model, HttpSession session) {
        List<Map<String, Object>> course_select = courseService.queryCourseSelect();
        model.addAttribute("select_list", course_select);

        model.addAttribute("error_msg", session.getAttribute("error_msg"));
        session.removeAttribute("error_msg");
        return "views/course_select_list";
    }
    @RequestMapping("Check")
    public String toCheck(Model model, HttpSession session) {
        List<Course> course_list = courseService.getCheckCourses();
        model.addAttribute("course_list", course_list);
        return "views/check";
    }
    @RequestMapping("/Feedback")
    public String toFeedback(Model model) {
        List<Feedback> feedbackList = teacherService.queryFeedbackAll();
        model.addAttribute("feedback_list", feedbackList);
        return "views/feedback";
    }
    @RequestMapping("/rootLogout")
    public String rootLogout(HttpSession session) {
        session.removeAttribute("login_root");

        return "redirect:/Login";

    }
    @PostMapping("/AddStudent.action")
    public String addStudent(@RequestParam(name = "studentName") String studentName,
                             @RequestParam(name = "studentNo") String studentNo,
                             @RequestParam(name = "passwd") String passwd,
                             @RequestParam(name = "sex") String sex,
                             @RequestParam(name = "age") Integer age,
                             HttpSession session,
                             Model model) {
        if (studentService.queryStudentByNo(studentNo) != null){
            session.setAttribute("error_msg", "添加失败！该账号已经存在");
        }else {
            session.removeAttribute("error_msg");
            studentService.addStudent(new Student(studentNo, studentName, passwd, sex, age));
        }
        return "redirect:/Root/StudentList";
    }
    @GetMapping("/DeleteStudent")
    public String deleteStudent(@RequestParam(name = "studentNo") String studentNo) {
        studentService.deleteStudent(studentNo);
        return "redirect:/Root/StudentList";
    }
    @GetMapping("/CheckAgree")
    public String checkAgree(@RequestParam(name = "courseNo") String courseNo) {
        courseService.addCheck(courseNo);
        return "redirect:/Root/Check";
    }
    @GetMapping("/CheckRefuse")
    public String checkRefuse(@RequestParam(name = "courseNo") String courseNo) {
        courseService.reduceCheck(courseNo);
        return "redirect:/Root/Check";
    }

    @PostMapping("/AlterStudent.action")
    public String AlterStudent(@RequestParam(name = "studentName") String studentName,
                               @RequestParam(name = "studentNo") String studentNo,
                               @RequestParam(name = "passwd") String passwd,
                               @RequestParam(name = "sex") String sex,
                               @RequestParam(name = "age") Integer age,
                               HttpSession session,
                               Model model) {
        studentService.deleteStudent(studentNo);
        if (studentService.queryStudentByNo(studentNo) != null){
            session.setAttribute("error_msg", "添加失败！该账号已经存在");
        }else {
            session.removeAttribute("error_msg");
            studentService.addStudent(new Student( studentNo, studentName, passwd, sex, age));
        }
        return "redirect:/Root/StudentList";
    }
    @PostMapping("/AddTeacher.action")
    public String addTeacher(
            @RequestParam(name = "teacherName") String teacherName,
            @RequestParam(name = "teacherNo") String teacherNo,
            @RequestParam(name = "passwd") String passwd,
            @RequestParam(name = "sex") String sex,
            @RequestParam(name = "age") Integer age,
            HttpSession session
    ) {
        if (teacherService.queryTeacherByNo(teacherNo) != null) {
            session.setAttribute("error_msg", "添加失败！账号已经存在");
        }else {
            teacherService.addTeacher(new Teacher( teacherNo,teacherName, passwd, sex, age));
        }
        return "redirect:/Root/TeacherList";
    }
    @GetMapping("/DeleteTeacher")
    public String deleteTeacher(@RequestParam(name = "teacherNo") String teacherNo) {
        teacherService.deleteTeacher(teacherNo);
        return "redirect:/Root/TeacherList";
    }
    @PostMapping("/AlterTeacher.action")
    public String alterTeacher( @RequestParam(name = "teacherName") String teacherName,
                                @RequestParam(name = "teacherNo") String teacherNo,
                                @RequestParam(name = "passwd") String passwd,
                                @RequestParam(name = "sex") String sex,
                                @RequestParam(name = "age") Integer age,
                                HttpSession session) {
        teacherService.deleteTeacher(teacherNo);
        if (teacherService.queryTeacherByNo(teacherNo) != null) {
            session.setAttribute("error_msg", "添加失败！账号已经存在");
        }else {
            teacherService.addTeacher(new Teacher(teacherNo,teacherName, passwd, sex, age));
        }
        return "redirect:/Root/TeacherList";
    }
}
