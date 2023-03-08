package com.zy.beans;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Feedback {
    private String courseNo;
    private String studentNo;
    private String courseName;
    private String username;
    private String words;

    public Feedback(String courseNo, String studentNo, String courseName, String username, String words) {
        this.courseNo = courseNo;
        this.studentNo = studentNo;
        this.courseName = courseName;
        this.username = username;
        this.words = words;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
}
