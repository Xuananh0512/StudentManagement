
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
                case 2: {
                    TeacherMenu(tcl);
                    break;
                }
                case 3: {
                    ClassroomMenu(cll);
                }

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
                    stl.readFile();
                    stl.println();
                    break;
                }
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

    public static void TeacherMenu(TeacherList tcl){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("\n--- QUAN LY GIAO VIEN ---");
            System.out.println("1. Them giao vien");
            System.out.println("2. Cap nhat giao vien");
            System.out.println("3. Xoa giao vien");
            System.out.println("4. Tim kiem giao vien");
            System.out.println("5. Hien thi danh sach giao vien");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");

            int option;
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1: {
                    tcl.add();
                    break;
                }
                case 2: {
                    tcl.update();
                    break;
                }
                case 3: {
                    tcl.remove();
                    break;
                }
                case 4: {
                    tcl.search();
                    break;
                }
                case 5: {
                    tcl.readFile();
                    tcl.println();
                    break;
                }
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

    public static void ClassroomMenu(ClassList cll){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("\n--- QUAN LY LOP HOC ---");
            System.out.println("1. Them lop hoc");
            System.out.println("2. Cap nhat lop hoc");
            System.out.println("3. Xoa lop hoc");
            System.out.println("4. Tim kiem lop hoc");
            System.out.println("5. Hien thi danh sach lop hoc");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");

            int option;
            option = sc.nextInt();
            sc.nextLine();


            switch (option) {
                case 1: {
                    cll.add();
                    break;
                }
                case 2: {
                    cll.update();
                    break;
                }
                case 3: {
                    cll.remove();
                    break;
                }
                case 4: {
                    cll.search();
                    break;
                }
                case 5: {
                    cll.readFile();
                    cll.println();
                    break;
                }
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

}

