package com.zy.service;


import com.zy.beans.Course;
import com.zy.beans.Feedback;
import com.zy.beans.Teacher;
import com.zy.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("TeacherService")
public class TeacherService {
    private final TeacherMapper teacherMapper;
    @Autowired
    public TeacherService(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    public List<Teacher> getTeachersAll() {
        return teacherMapper.getTeachersAll();
    }

    public Teacher queryTeacherByNo(String no) {
        return teacherMapper.queryTeacherByNo(no);
    }

    public Course queryCourseByNo(String courseno, String teacherno) { return  teacherMapper.queryCourseByNo(courseno, teacherno);}

    public List<Map<String, Object>> queryCourseSelect(String teacherno) {
        return teacherMapper.queryCourseSelect(teacherno);
    }
    public Course queryCourseByNo(String courseno) { return teacherMapper.queryCourseByNo1(courseno);}

    public List<Course> queryCoursesAll(String teacherno) { return teacherMapper.queryCoursesAll(teacherno); }

    public int deleteCourse(String courseNo, String teacherNo) { return teacherMapper.deleteCourseByNo(courseNo, teacherNo); }

    public void addTeacher(Teacher teacher) {
        teacherMapper.addTeacher(teacher);
    }

    public List<Feedback> queryFeedback(String teacherNo) { return teacherMapper.getFeedback(teacherNo); }

    public int deleteTeacher(String no) { return teacherMapper.deleteTeacherByNo(no);}

    public List<Feedback> queryFeedbackAll() { return teacherMapper.getFeedbackAll();}
}
