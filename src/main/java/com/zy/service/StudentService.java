package com.zy.service;

import com.zy.beans.Course;
import com.zy.beans.Student;
import com.zy.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("student_service")
public class StudentService {
    private final StudentMapper studentMapper;
    @Autowired
    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public int makeAppointment(String courseno, String studentno) {
        return studentMapper.addCourseSelection(courseno, studentno);
    }

    public int queryCourseByNo(String courseno, String studentno) { return studentMapper.queryCourseByNo(courseno, studentno); }
    public void addStudent(Student student) {
        studentMapper.addStudent(student);
    }
    public List<Student> getUserList(){ return studentMapper.queryStudentList();}
    public void deleteStudent(String no) {
        studentMapper.deleteStudent(no);
    }
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }
    public Student queryStudentByNo(String no) { return studentMapper.queryStudentByNo(no); }
    public List<Course> queryCoursesAll(String no) { return studentMapper.queryCoursesAll(no); }
    public void deleteSelectByNo(String courseno, String studentno) {
        studentMapper.deleteSelectByNo(courseno, studentno);
    }
    public void addFeedBack(String studentNo, String courseNo, String words) {
        studentMapper.insertFeedback(studentNo, courseNo, words);
    }
}
