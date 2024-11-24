package com.example.m3_4_13_buddyappzip.components;

import java.io.Serializable;

public class CourseDetails implements Serializable {
	private String courseDifficulty;
	private int preparation;
	private String requiredKnowledge;
	private String tips;

	public CourseDetails(String courseDifficulty, int preparation, String requiredKnowledge, String tips){
		this.courseDifficulty = courseDifficulty;
		this.preparation = preparation;
		this.requiredKnowledge = requiredKnowledge;
		this.tips = tips;
	}

	public String getCourseDifficulty() {
		return courseDifficulty;
	}

	public void setCourseDifficulty(String courseDifficulty) {
		this.courseDifficulty = courseDifficulty;
	}

	public int getPreparation() {
		return preparation;
	}

	public void setPreparation(int preparation) {
		this.preparation = preparation;
	}

	public String getRequiredKnowledge() {
		return requiredKnowledge;
	}

	public void setRequiredKnowledge(String requiredKnowledge) {
		this.requiredKnowledge = requiredKnowledge;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}
}
