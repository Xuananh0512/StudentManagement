package models;

import java.util.Scanner;

public class ScoreModel {
	private double math;
	private double literature;
	private double english;

	public ScoreModel() {
	}

	public ScoreModel(double math, double literature, double english) {
		this.math = math;
		this.literature = literature;
		this.english = english;
	}

	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Math score: ");
		math = sc.nextDouble();
		System.out.print("Enter Literature score: ");
		literature = sc.nextDouble();
		System.out.print("Enter English score: ");
		english = sc.nextDouble();
	}

	public double average() {
		return (math + literature + english) / 3.0;
	}

	@Override
	public String toString() {
		return math + " | " + literature + " | " + english ;
	}

	public void display() {
		System.out.println(this.toString());
	}

	// Getters & Setters
	public double getMath() {
		return math;
	}

	public void setMath(double math) {
		this.math = math;
	}

	public double getLiterature() {
		return literature;
	}

	public void setLiterature(double literature) {
		this.literature = literature;
	}

	public double getEnglish() {
		return english;
	}

	public void setEnglish(double english) {
		this.english = english;
	}

	
}
