package models;

import java.util.Scanner;

public abstract class StudentModel {
    protected String id;
    protected String fullName;
    protected String address;
    protected ClassroomModel classModel;
    protected ScoreModel score;

    public StudentModel() {


    }

    public StudentModel(String id, String fullName, String address, ClassroomModel classModel, ScoreModel score) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.classModel = classModel;
        this.score = score;
    }

    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        id = sc.nextLine();
        System.out.print("Enter full name: ");
        fullName = sc.nextLine();
        System.out.print("Enter class: ");
        System.out.print("Enter address: ");
        address = sc.nextLine();
        score.input();


        classModel.input(new Scanner(System.in));



    }

    public abstract void display();

    @Override
    public String toString() {
        return id + " | " + fullName + " | " + address + " | " + classModel.toString() + " | " + score.toString();
    }
// 3123411016 | Nguyen Le Qaun
    // Getters & Setters
    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {


        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setScore(ScoreModel score) {
        this.score = score;
    }
    public ScoreModel getScore() {
        return score;
    }

    public void setClassModel(ClassroomModel classModel) {
        this.classModel = classModel;
    }
    public ClassroomModel getClassroomModel() {
        return classModel;
    }


}
