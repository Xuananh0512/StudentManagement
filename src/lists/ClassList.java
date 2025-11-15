package lists;

import interfaces.ILists;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import models.*;

public class ClassList implements ILists {
    private ArrayList<ClassroomModel> classList;
    private Scanner sc = new Scanner(System.in);
    private String titleHeader = "classId | className | teacherId";

    public ClassList() {
        classList = new ArrayList<>();
    }

    // ========================= ADD ============================
    @Override
    public void add() {
        while (true) {
            System.out.print("Enter class ID: ");
            String id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("Class ID cannot be empty. Please enter again.");
                continue;
            }

            int index = searchById(id);
            if (index != -1) {
                System.out.println("Class ID already exists, please enter again");
                continue;
            }

            System.out.print("Enter class name: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Class name cannot be empty. Please enter again.");
                continue;
            }

            System.out.println("Enter teacher ");
            String teacher = sc.nextLine().trim();
            if (teacher.isEmpty()) {
                System.out.println("Teacher info cannot be empty. Please enter again.");
                continue;
            }

            classList.add(new ClassroomModel(id, name, teacher));
            writeFile();
            System.out.println("Class added successfully!");
            return;
        }
    }

    // ========================= UPDATE ============================
    @Override
    public void update() {
        while (true) {
            System.out.print("Enter class ID to update: ");
            String id = sc.nextLine().trim();
            int index = searchById(id);

            if (index == -1) {
                System.out.println("Class not found!");
                // hỏi người dùng có muốn thử lại không? Ta cho loop tiếp
                continue;
            }

            ClassroomModel c = classList.get(index);

            System.out.println("1. Class name  2. Teacher info");
            System.out.print("Select field to update: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Choice must be a number.");
                sc.nextLine();
                continue;
            }
            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1: {
                    System.out.print("Enter new class name: ");
                    String newName = sc.nextLine().trim();
                    if (newName.isEmpty()) {
                        System.out.println("Class name cannot be empty.");
                    } else {
                        c.setClassName(newName);
                    }
                    break;
                }

                case 2: {
                    System.out.println("Enter new teacher info:");
                    String newTeacher = sc.nextLine().trim();
                    if (newTeacher.isEmpty()) {
                        System.out.println("Teacher info cannot be empty.");
                    } else {
                        c.setTeacherId(newTeacher);
                    }
                    break;
                }
                default: {
                    System.out.println("Invalid choice!");
                    continue; // cho người dùng chọn lại thay vì return
                }
            }

            System.out.println("Update successful!");
            writeFile();
            return;
        }
    }

    // ========================= REMOVE ============================
    @Override
    public void remove() {
        while (true) {
            System.out.print("Enter class ID to remove: ");
            String id = sc.nextLine().trim();
            int index = searchById(id);

            if (index == -1) {
                System.out.println("Class not found!");
                return;
            }

            System.out.print("Confirm delete (1: Yes / 2: No): ");
            if (!sc.hasNextInt()) {
                System.out.println("Please enter 1 or 2.");
                sc.nextLine();
                continue;
            }
            int confirm = sc.nextInt();
            sc.nextLine();

            if (confirm == 1) {
                classList.remove(index);
                System.out.println("Class removed successfully!");
                writeFile();
                return;
            } else {
                System.out.println("Delete cancelled!");
                return;
            }
        }
    }

    // ========================= SEARCH ============================
    @Override
    public void search() {
        while (true) {
            System.out.print("Enter class ID to search: ");
            String id = sc.nextLine().trim();
            int index = searchById(id);

            if (index == -1) {
                System.out.println("Class not found!");
                // cho phép nhập lại hoặc thoát; ở đây tiếp tục vòng lặp
                continue;
            } else {
                System.out.println("Search result:");
                classList.get(index).output();
                return;
            }
        }
    }

    // ========================= PRINT LIST ============================
    @Override
    public void println() {
        if (classList.isEmpty()) {
            System.out.println("Class list is empty!");
        } else {
            System.out.println("Class list:");
            System.out.println(titleHeader);
            for (ClassroomModel c : classList) {
                c.output();
            }
        }
    }

    // ========================= SEARCH BY ID ============================
    @Override
    public int searchById(String id) {
        if (classList.isEmpty()) {
            // Không in message ở đây nếu gọi nhiều lần (giữ form), nhưng vẫn trả -1
            return -1;
        }

        for (int i = 0; i < classList.size(); i++) {
            if (classList.get(i).getClassId().equalsIgnoreCase(id)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ClassroomModel searchObjectById(String id) {
        int index = searchById(id);
        return index == -1 ? null : classList.get(index);
    }

    @Override
    public void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("txt/classroom.txt"));
            String line;
            classList.clear();
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                // kiểm tra số cột tối thiểu
                if (parts.length < 3) {
                    // bỏ qua dòng lỗi để không crash
                    System.out.println("Warning: skipping malformed line in classroom.txt -> " + line);
                    continue;
                }

                String classId = parts[0].trim();
                String className = parts[1].trim();
                String teacherId = parts[2].trim();

                ClassroomModel temp = new ClassroomModel(classId, className, teacherId);
                classList.add(temp);
            }
            reader.close();
            System.out.println("✅ Đọc file thành công! Số lớp: " + classList.size());
        } catch (FileNotFoundException e) {
            System.out.println("File không tồn tại!");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
    }

    @Override
    public void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("txt/classroom.txt", false)); // false = ghi đè
            for (int i = 0; i < classList.size(); i++) {
                writer.write(classList.get(i).toString()); // classroom.toString() nên trả về đúng format "id|name|teacher"
                writer.newLine();
            }

            writer.close();
            System.out.println("✅ Ghi file thành công!");
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
        }
    }

    // ========================= MAIN TEST ============================
    public static void main(String[] args) {
        ClassList list = new ClassList();
        list.readFile();
        list.println();
        list.add();

        list.println();
        list.readFile();
        list.update();
        list.println();
        list.search();
        list.remove();
        list.println();
        list.readFile();
        list.println();
    }
}
