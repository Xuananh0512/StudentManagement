package lists;

import interfaces.ILists;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import models.TeacherModel;

public class TeacherList implements ILists {
    private ArrayList<TeacherModel> teacherList;
    private Scanner sc = new Scanner(System.in);
    private String titleHeader = "teacherId | fullName | subject";

    public TeacherList() {
        teacherList = new ArrayList<>();
        readFile(); // Load dữ liệu khi khởi tạo
    }

    // ========================== ADD ==============================
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

    // ========================== UPDATE ==============================
    @Override
    public void update() {
        while (true) {
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

            writeFile();
            System.out.println("Teacher updated successfully!");
            return;
        }
    }

    // ========================== REMOVE ==============================
    @Override
    public void remove() {
        while (true) {
            System.out.print("Enter teacher ID to remove: ");
            String id = sc.nextLine();
            int index = searchById(id);

            if (index == -1) {
                System.out.println("Teacher not found!");
                return;
            }

            System.out.print("Are you sure? (1: Yes / 2: No): ");
            int confirm = sc.nextInt();
            sc.nextLine();

            if (confirm == 1) {
                teacherList.remove(index);
                writeFile();
                System.out.println("Teacher removed successfully!");
            } else {
                System.out.println("Remove cancelled!");
            }
        }
    }

    // ========================== SEARCH ==============================
    @Override
    public void search() {
        while (true) {
            System.out.print("Enter teacher ID to search: ");
            String id = sc.nextLine();
            int index = searchById(id);

            if (index == -1) {
                System.out.println("Teacher not found!");
            } else {
                teacherList.get(index).display();
                return;
            }
        }
    }

    // ========================== PRINT LIST ==============================
    @Override
    public void println() {
        if (teacherList.isEmpty()) {
            System.out.println("The teacher list is empty!");
        } else {
            System.out.println("===== TEACHER LIST =====");
            System.out.println(titleHeader);
            for (TeacherModel t : teacherList) {
                t.display();
            }
        }
    }

    // ========================== SEARCH BY ID ==============================
    @Override
    public int searchById(String id) {
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

    // ========================== READ FILE ===============================
    @Override
    public void readFile() {
        File file = new File("txt/teacher.txt");

        if (!file.exists()) {
            System.out.println("⚠ File teacher.txt không tồn tại! (sẽ tạo mới khi ghi)");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            teacherList.clear();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] p = line.split("\\|");
                if (p.length < 3) continue;

                String id = p[0];
                String name = p[1];
                String subject = p[2];

                teacherList.add(new TeacherModel(id, name, subject));
            }

            System.out.println("✅ Đọc file thành công! Số giáo viên: " + teacherList.size());

        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file: " + e.getMessage());
        }
    }

    // ========================== WRITE FILE ===============================
    @Override
    public void writeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("txt/teacher.txt"))) {

            for (TeacherModel t : teacherList) {
                writer.write(t.getTeacherId() + "|" + t.getFullName() + "|" + t.getSubject());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
        }
    }

    // ========================== MAIN TEST ===============================
    public static void main(String[] args) {
        TeacherList list = new TeacherList();
//        list.readFile();
//        list.println();
//        list.add();
//        list.readFile();
//        list.println();
//        list.update();
//        list.readFile();
//        list.println();
//        list.search();
        list.readFile();
        list.println();
        list.remove();
        list.readFile();
        list.println();
    }
}
