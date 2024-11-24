package com.example.m3_4_13_buddyappzip.components;

import java.io.Serializable;
import java.util.List;

public class StudyProgram implements Serializable {
	private String studyField;
	private int ect;

	private List<Course> courses;
	private AcademicTitle academicTitle;

	public StudyProgram(String studyField){
		this.studyField = studyField;
	}

	public StudyProgram(String studyField, int ect, AcademicTitle title){
		this.studyField = studyField;
		this.ect = ect;
		this.academicTitle = title;
	}

	public String getStudyField(){
		return studyField;
	}

	public AcademicTitle getAcademicTitle(){
		return this.academicTitle;
	}

	@Override
	public String toString(){
		return studyField;
	}

}
