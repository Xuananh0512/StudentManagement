package models;

import java.util.Scanner;

public class SpecializedStudentModel extends StudentModel {
    private String majorSubject; // Môn chuyên
    private double majorScore;   // Điểm môn chuyên

    public SpecializedStudentModel() {}

    public SpecializedStudentModel(String id, String fullName,
                                   String address,  ClassroomModel classId, ScoreModel score,
                                   String majorSubject, double majorScore) {
        super(id, fullName, address, classId, score);
        this.majorSubject = majorSubject;
        this.majorScore = majorScore;
    }

    @Override
    public void input() {
        super.input();
        Scanner sc = new Scanner(System.in);
   
        System.out.print("Enter specialized subject: ");
        majorSubject = sc.nextLine();
        System.out.print("Enter major score: ");
        majorScore = sc.nextDouble();
    }

    @Override
    public void display() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return super.toString() + "null" + " | "+ majorSubject + " | " + majorScore;

             
    }



    // Getters & Setters
    public String getMajorSubject() { return majorSubject; }
    public void setMajorSubject(String majorSubject) { this.majorSubject = majorSubject; }

    public double getMajorScore() { return majorScore; }
    public void setMajorScore(double majorScore) { this.majorScore = majorScore; }
}
