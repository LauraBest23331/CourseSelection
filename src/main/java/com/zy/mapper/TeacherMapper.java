package com.zy.mapper;

import com.zy.beans.Course;
import com.zy.beans.Feedback;
import com.zy.beans.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TeacherMapper {
    public List<Teacher> getTeachersAll();

    public Teacher queryTeacherByNo(String no);

    public Course queryCourseByNo(String courseno, String teacherno);

    public List<Map<String, Object>> queryCourseSelect(String teacherno);

    public List<Course> queryCoursesAll(String no);

    public int deleteCourseByNo(String courseNo, String teacherNo);

    public void addTeacher(Teacher teacher);

    public int deleteTeacherByNo(String no);

    public Course queryCourseByNo1(String courseNo);

    public List<Feedback> getFeedback(String teacherNo);

    public List<Feedback> getFeedbackAll();


}
