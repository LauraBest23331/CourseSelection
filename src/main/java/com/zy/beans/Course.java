package com.zy.beans;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@NoArgsConstructor
public class Course {
    private String courseName;
    private String courseNo;
    private String username;
    private String beginTime;
    private String teacherNo;
    private Integer maxNumber;
    private Integer nowNumber;
    private Integer isCheck;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public Integer getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(Integer maxNumber) {
        this.maxNumber = maxNumber;
    }

    public Integer getNowNumber() {
        return nowNumber;
    }

    public void setNowNumber(Integer nowNumber) {
        this.nowNumber = nowNumber;
    }

    public Course(String courseName, String courseNo, String username, String beginTime, String teacherNo, Integer maxNumber, Integer nowNumber, Integer isCheck) {
        this.courseName = courseName;
        this.courseNo = courseNo;
        this.username = username;
        this.beginTime = beginTime;
        this.teacherNo = teacherNo;
        this.maxNumber = maxNumber;
        this.nowNumber = nowNumber;
        this.isCheck = isCheck;
    }



    public String getCourseName() {
        return courseName;
    }



    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getTeacherName() {
        return username;
    }

    public void setTeacherName(String teacherName) {
        this.username = teacherName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }
}
