package com.zy.mapper;


import com.zy.beans.Course;
import com.zy.beans.Student;
import com.zy.service.CourseService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CourseMapper {

    public List<Course> queryCoursesAll();
    public Course queryCourse(String no);
    public int insertCourse(Course course);
    public List<Map<String, Object>> queryCourseSelect();
    public int addNumber(String courseNo);
    public int reduceNumber(String courseNo);
    public List<Course> queryCheckCourses();
    public int addCheck(String courseNo);
    public int reduceCheck(String courseNo);
}
