package models;

import java.util.Scanner;

public class RegularStudentModel extends StudentModel {
	private String conduct; // hạnh kiểm (Excellent / Good / Average / Weak)

	public RegularStudentModel() {
	}

	public RegularStudentModel(String id, String fullName, String address, ClassroomModel classModel, ScoreModel score, String conduct) {
		super(id, fullName, address, classModel, score);
		this.conduct = conduct;
	}

	@Override
	public void input() {
		super.input();
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter conduct (Excellent/Good/Average/Weak): ");
		conduct = sc.nextLine();
	}

	@Override
	public void display() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return super.toString() +" | "+ conduct + " | null | null" ;
	}

	public String getConduct() {
		return conduct;
	}

	public void setConduct(String conduct) {
		this.conduct = conduct;
	}
}
