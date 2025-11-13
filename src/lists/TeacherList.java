package lists;

import interfaces.ILists;

import java.util.ArrayList;
import java.util.Scanner;

import models.TeacherModel;

public class TeacherList implements ILists {
    private ArrayList<TeacherModel> teacherList;
    private Scanner sc = new Scanner(System.in);

    public TeacherList() {

        teacherList = new ArrayList<>();

//        teacherList.add(new TeacherModel("T001", "Nguyễn Văn A", "Toán"));
//        teacherList.add(new TeacherModel("T002", "Trần Thị B", "Văn"));
//        teacherList.add(new TeacherModel("T003", "Lê Văn C", "Anh"));
//        teacherList.add(new TeacherModel("T004", "Phạm Thị D", "Lý"));
//        teacherList.add(new TeacherModel("T005", "Hoàng Văn E", "Hóa"));
//        teacherList.add(new TeacherModel("T006", "Đặng Thị F", "Sinh"));
//        teacherList.add(new TeacherModel("T007", "Vũ Văn G", "Sử"));
//        teacherList.add(new TeacherModel("T008", "Bùi Thị H", "Địa"));
//        teacherList.add(new TeacherModel("T009", "Ngô Văn I", "Tin"));
//        teacherList.add(new TeacherModel("T010", "Phan Thị K", "GDCD"));
    }

    // ========================= ADD ============================
    @Override
    public void add() {
        while (true) {
            System.out.print("Enter teacher ID: ");
            String id = sc.nextLine();

            if (searchById(id) != -1) {
                System.out.println("ID already exists! Please enter another one.");
                continue;
            }

            System.out.print("Enter full name: ");
            String fullName = sc.nextLine();

            System.out.print("Enter subject: ");
            String subject = sc.nextLine();

            teacherList.add(new TeacherModel(id, fullName, subject));
            writeFile();
            System.out.println("Teacher added successfully!");

            break;
        }
    }

    // ========================= UPDATE ============================
    @Override
    public void update() {
        System.out.print("Enter teacher ID to update: ");
        String id = sc.nextLine();
        int index = searchById(id);

        if (index == -1) {
            System.out.println("Teacher not found!");
            return;
        }

        TeacherModel t = teacherList.get(index);
        System.out.println("Select the field to update:");
        System.out.println("1. Full name");
        System.out.println("2. Subject");
        System.out.println("3. All");
        System.out.print("Your choice: ");
        int opt = sc.nextInt();
        sc.nextLine();

        switch (opt) {
            case 1:
                System.out.print("Enter new full name: ");
                t.setFullName(sc.nextLine());
                break;
            case 2:
                System.out.print("Enter new subject: ");
                t.setSubject(sc.nextLine());
                break;
            case 3:
                System.out.print("Enter new full name: ");
                t.setFullName(sc.nextLine());
                System.out.print("Enter new subject: ");
                t.setSubject(sc.nextLine());
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        System.out.println("Teacher updated successfully!");
    }

    // ========================= REMOVE ============================
    @Override
    public void remove() {
        System.out.print("Enter teacher ID to remove: ");
        String id = sc.nextLine();
        int index = searchById(id);

        if (index == -1) {
            System.out.println("Teacher not found!");
            return;
        }

        System.out.print("Are you sure you want to delete this teacher? (1: Yes / 2: No): ");
        int confirm = sc.nextInt();
        sc.nextLine();

        if (confirm == 1) {
            teacherList.remove(index);
            System.out.println("Teacher removed successfully!");
        } else {
            System.out.println("Remove cancelled!");
        }
    }

    // ========================= SEARCH ============================
    @Override
    public void search() {
        System.out.print("Enter teacher ID to search: ");
        String id = sc.nextLine();
        int index = searchById(id);

        if (index == -1) {
            System.out.println("Teacher not found!");
        } else {
            System.out.println("Search result:");
            teacherList.get(index).display();
        }
    }

    // ========================= PRINT LIST ============================
    @Override
    public void println() {
        if (teacherList.isEmpty()) {
            System.out.println("The teacher list is empty!");
        } else {
            System.out.println("===== TEACHER LIST =====");
            for (TeacherModel t : teacherList) {
                t.display();
            }
        }
    }

    // ========================= SEARCH BY ID ============================
    @Override
    public int searchById(String id) {
        if (teacherList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < teacherList.size(); i++) {
            if (teacherList.get(i).getTeacherId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object searchObjectById(String id) {
        int index = searchById(id);
        return index == -1 ? null : teacherList.get(index);
    }
    @Override
    public void readFile(){

    }
    @Override
    public void writeFile() {

    }
    // ========================= MAIN TEST ============================
    public static void main(String[] args) {
        TeacherList list = new TeacherList();
        list.add();
//        list.println();
        list.update();
//        list.println();
        list.search();
        list.remove();
        list.println();
    }
}
