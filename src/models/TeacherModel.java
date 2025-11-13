package models;

import java.util.Scanner;

public class TeacherModel {
	private String teacherId;
	private String fullName;
	private String subject;

	public TeacherModel() {
	}

	public TeacherModel(String teacherId, String fullName, String subject) {
		this.teacherId = teacherId;
		this.fullName = fullName;
		this.subject = subject;
	}

	public void input(Scanner sc) {
		System.out.print("Enter teacher ID: ");
		teacherId = sc.nextLine();
		System.out.print("Enter full name: ");
		fullName = sc.nextLine();
		System.out.print("Enter subject: ");
		subject = sc.nextLine();
	}

	public void display() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "Teacher ID: " + teacherId + " | Name: " + fullName + " | Subject: " + subject;
	}

	// Getters & Setters
	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
