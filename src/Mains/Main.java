
package Mains;
import java.util.Scanner;
import models.SpecializedStudentModel;
import models.StudentModel;
import lists.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StudentList stl = new StudentList();
        ClassList cll = new ClassList();
        TeacherList tcl = new TeacherList();
        ScoreList scl = new ScoreList();

        while (true) {
            System.out.println("\n===== QUAN LY TRUONG HOC =====");
            System.out.println("1. Quan ly hoc sinh");
            System.out.println("2. Quan ly giao vien");
            System.out.println("3. Quan ly lop hoc");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            int option;
            option = sc.nextInt(); sc.nextLine();

            switch (option) {
                case 1: {
                    StudentMenu(stl);
                    break;
                }
//                case 2: {
//                    TeacherMenu(tcl);
//                    break;
//                }
//                case 3: {
//                    ClassroomMenu(cll);
//                }
//                case 4: {
//                    ScoreMenu(scl);
//                }
                case 0: {
                    System.out.println("baibai");
                    return;
                }
                default: {
                    System.out.println("Khong hop le");
                }
            }
        }
    }
    public static void StudentMenu(StudentList stl){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("\n--- QUAN LY HOC SINH ---");
            System.out.println("1. Them hoc sinh");
            System.out.println("2. Cap nhat hoc sinh");
            System.out.println("3. Xoa hoc sinh");
            System.out.println("4. Tim kiem hoc sinh");
            System.out.println("5. Hien thi danh sach hoc sinh");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            int option;
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1: {
                    stl.add();
                    break;
                }
                case 2: {
                    stl.update();
                    break;
                }
                case 3: {
                    stl.remove();
                    break;
                }
                case 4: {
                    stl.search();
                    break;
                }
                case 5: {
                    stl.println();
                    break;
                }
                case 0: {
                    return;
                }
                default: {
                    System.out.println("Khong hop le");
                }
            }
        }
    }
}

