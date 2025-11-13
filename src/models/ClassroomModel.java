package models;

import java.util.Scanner;

public class ClassroomModel {
	private String classId;
	private String className;
	private String teacherId; // giáo viên chủ nhiệm

	public ClassroomModel() {
	}

	public ClassroomModel(String classId, String className, String teacherId) {
		this.classId = classId;
		this.className = className;
		this.teacherId = teacherId;
	}

	public void input(Scanner sc) {
		System.out.print("Enter class ID: ");
		classId = sc.nextLine();
		System.out.print("Enter class name: ");
		className = sc.nextLine();
		System.out.println("Enter teacher ID:");
        teacherId = sc.nextLine();

	}

	public void output() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return classId + " | " + className + " | " + teacherId;
	}

	// Getters & Setters
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
}
