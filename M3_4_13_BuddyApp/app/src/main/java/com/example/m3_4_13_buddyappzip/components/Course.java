package com.example.m3_4_13_buddyappzip.components;

import java.io.Serializable;

public class Course implements Serializable {
    private String courseId;
    private String courseName;

    private CourseDetails courseDetails;

    private StudyProgram program;
    private int ect;
    private String semester;

    public Course(String courseId, String courseName, int ect, String semester){
        this.courseId = courseId;
        this.courseName = courseName;
        this.ect = ect;
        this.semester = semester;
    }
    public Course(String courseId, String courseName, int ect, String semester, StudyProgram program) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.ect = ect;
        this.semester = semester;
        this.program = program;
    }

    public Course(String courseId, String courseName, CourseDetails courseDetails, int ect, String semester, StudyProgram program) {
      this(courseId, courseName, ect, semester, program);
      this.courseDetails = courseDetails;
    }


    public String getCourseName() {
        return this.courseName;
    }

    public CourseDetails getCourseDetails(){
        return this.courseDetails;
    }

    public StudyProgram getStudyProgram() {
        return this.program;
    }

    public String getSemester() {
        return this.semester;
    }
}



