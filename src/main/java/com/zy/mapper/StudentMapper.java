package com.zy.mapper;


import com.zy.beans.Course;
import com.zy.beans.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {

    int addStudent(Student student);

    int deleteStudent(String no);

    List<Course> queryCoursesAll(String no);

    void updateStudent(Student student);

    int addCourseSelection(String courseno, String studentno);

    int queryCourseByNo(String courseno, String studentno);

    List<Student> queryStudentList();

    Student queryStudentByNo(String no);

    void deleteSelectByNo(String courseno, String studentno);

    void insertFeedback(String studentNo, String courseNo, String words);
}
