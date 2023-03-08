package com.zy.service;


import com.zy.beans.Course;
import com.zy.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("CourseService")
public class CourseService {
    private final CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }


    public List<Course> queryCoursesAll() {
        return courseMapper.queryCoursesAll();
    }

    public Course queryCourseByNo(String no) {
        return courseMapper.queryCourse(no);
    }

    public int insetCourse(Course course) { return courseMapper.insertCourse(course); }

    public List<Map<String, Object>> queryCourseSelect() { return  courseMapper.queryCourseSelect(); }

    public int addNumber(String courseNo) { return courseMapper.addNumber(courseNo); }

    public int reduceNumber(String courseNo) { return courseMapper.reduceNumber(courseNo); }

    public List<Course> getCheckCourses() { return courseMapper.queryCheckCourses();}
    public int addCheck(String courseNo) { return courseMapper.addCheck(courseNo); }
    public int reduceCheck(String courseNo) { return courseMapper.reduceCheck(courseNo);}
}
