package com.excode.model;

import java.util.List;

public class Exercise {
	
	public int exerciseID;
	public String creationDate;
	public String title;	
	public User author;
	public String statement;
	public String dificulty;
	public int duration;
	public String [] tags;
	public List <Solution> solutions;
	public List <TestCase> testCases;
	
	public Exercise(int exerciseID, String creationDate, String title, User author, String statement, String dificulty,
			int duration, String[] tags, List<Solution> solutions, List<TestCase> testCases) {
		super();
		this.exerciseID = exerciseID;
		this.creationDate = creationDate;
		this.title = title;
		this.author = author;
		this.statement = statement;
		this.dificulty = dificulty;
		this.duration = duration;
		this.tags = tags;
		this.solutions = solutions;
		this.testCases = testCases;
	}
	
	
	public int getExerciseID() {
		return exerciseID;
	}
	public void setExerciseID(int exerciseID) {
		this.exerciseID = exerciseID;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getDificulty() {
		return dificulty;
	}
	public void setDificulty(String dificulty) {
		this.dificulty = dificulty;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public List<Solution> getSolutions() {
		return solutions;
	}
	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}
	public List<TestCase> getTestCases() {
		return testCases;
	}
	public void setTestCases(List<TestCase> testCases) {
		this.testCases = testCases;
	}

	
	
}
	
	