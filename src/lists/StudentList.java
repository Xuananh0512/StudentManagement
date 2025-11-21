


package lists;

import interfaces.ILists;
import models.*;
import validators.Validator; // nếu Validator nằm trong thư mục validators

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class StudentList implements ILists {
    private ArrayList<StudentModel> studentList;
    private Scanner sc = new Scanner(System.in);
    private String titleHeader = "Id | Name | Address | ClassId | ClassName | TeacherId | Math | Literature | English | Conduct | majorSubject | majorScore";

    private static int totalStudents = 0; // static counter

    // Thêm static getter
    public static int getTotalStudents() {
        return totalStudents;
    }
    public static void setTotalStudents(int total) {
        totalStudents = total;
    }


    public StudentList() {
        studentList = new ArrayList<>();
        readFile();
    }

    // ========================= ADD ============================

    @Override
    public void add() {
        while (true) {
            String id;

            while (true) {
                System.out.print("Enter ID to add: ");
                id = sc.nextLine().trim();

                if (id.isEmpty()) {
                    System.out.println("ID cannot be empty. Please enter again!");
                    continue;
                }

                if (searchById(id) != -1) {
                    System.out.println("ID already exists. Please enter again!");
                    continue;
                }

                if (!Validator.isValidId(id)) {
                    System.out.println("Invalid ID (only letters and numbers, 2-10 characters).");
                    continue;
                }
                break;

            }

            int option;

            while (true) {

                System.out.println("1. Add regular student");
                System.out.println("2. Add specialized student");
                System.out.print("Choose type: ");
                if (!sc.hasNextInt()) {
                    System.out.println("Choice must be a number!");

                    sc.nextLine();

                    continue;

                }
                option = sc.nextInt();
                sc.nextLine();
                if (option == 1 || option == 2)
                    break;
                System.out.println("Invalid choice!");
            }

            String fullName;
            while (true) {

                System.out.print("Enter full name: ");
                fullName = sc.nextLine().trim();
                if (!Validator.isValidName(fullName)) {
                    System.out.println("Invalid name (letters only, up to 50 characters).");
                    continue;
                }
                break;
            }

            String address;

            while (true) {
                System.out.print("Enter address: ");
                address = sc.nextLine().trim();
                if (address.length() < 3) {
                    System.out.println("Address must be at least 3 characters.");
                    continue;
                }
                break;
            }

            ScoreModel score;
            while (true) {
                double math, literature, english;

                while (true) {
                    System.out.println("Enter Math score: (0 <= score <= 10)");
                    math = sc.nextDouble();
                    if (!Validator.isValidScore(math)) {
                        System.out.println("Invalid Math score! Must be 0-10.");
                        continue;
                    }
                    break;
                }

                while (true) {
                    System.out.println("Enter Literature score: (0 <= score <= 10)");
                    literature = sc.nextDouble();
                    if (!Validator.isValidScore(literature)) {
                        System.out.println("Invalid Literature score! Must be 0-10.");
                        continue;
                    }
                    break;
                }

                while (true) {
                    System.out.println("Enter English score: (0 <= score <= 10)");
                    english = sc.nextDouble();
                    if (!Validator.isValidScore(english)) {
                        System.out.println("Invalid English score! Must be 0-10.");
                        continue;
                    }
                    break;
                }

                sc.nextLine();
                score = new ScoreModel(math, literature, english);
                break;
            }

            ClassroomModel classModel;

            String classId;
            while (true) {
                System.out.println("Enter class ID: ");
                classId = sc.nextLine().trim();
                if (!Validator.isValidId(classId)) {
                    System.out.println("Invalid class ID! Only letters/numbers, 2-10 chars.");
                    continue;
                }
                break;
            }

            String className;
            while (true) {
                System.out.println("Enter class name: ");
                className = sc.nextLine().trim();
                if (!Validator.isValidName(className)) {
                    System.out.println("Invalid class name! Only letters and spaces, 3-50 chars.");
                    continue;
                }
                break;
            }

            String teacherId;
            while (true) {
                System.out.println("Enter homeroom teacher name: ");
                teacherId = sc.nextLine().trim();
                if (!Validator.isValidName(teacherId)) {
                    System.out.println("Invalid teacher name! Only letters and spaces, 3-50 chars.");
                    continue;
                }
                break;
            }

            classModel = new ClassroomModel(classId, className, teacherId);

            if (option == 1) {
                String conduct;
                while (true) {
                    System.out.print("Enter conduct: ");
                    conduct = sc.nextLine().trim();
                    if (!Validator.isValidConduct(conduct)) {
                        System.out.println("Invalid conduct (letters only, up to 20 characters).");
                        continue;
                    }
                    break;
                }
                StudentModel temp = new RegularStudentModel(id, fullName, address, classModel, score, conduct);
                studentList.add(temp);
                writeFile();
                System.out.println("Regular student added successfully!");
                return;
            } else {
                String majorSubject;
                while (true) {
                    System.out.print("Enter specialized subject: ");
                    majorSubject = sc.nextLine().trim();
                    if (!Validator.isValidName(majorSubject)) {
                        System.out.println("Invalid specialized subject (letters only, up to 30 characters).");
                        continue;
                    }
                    break;
                }
                double majorScore;
                while (true) {
                    System.out.print("Enter major score: ");
                    if (!sc.hasNextDouble()) {
                        System.out.println("Score must be a number!");
                        sc.nextLine();
                        continue;
                    }
                    majorScore = sc.nextDouble();
                    sc.nextLine();
                    if (majorScore < 0 || majorScore > 10) {
                        System.out.println("Score must be between 0 and 10!");
                        continue;
                    }
                    break;
                }
                StudentModel temp = new SpecializedStudentModel(id, fullName, address, classModel, score, majorSubject,
                        majorScore);
                studentList.add(temp);
                totalStudents++;
                writeFile();
                System.out.println("Specialized student added successfully!");
                return;

            }

        }

    }

    // ========================= UPDATE ============================

    @Override

    public void update() {
        while (true) {
            System.out.print("Enter ID to update: ");
            String id = sc.nextLine();
            int index = searchById(id);

            if (index == -1) {
                System.out.println("Student not found!");
                continue;
            }

            StudentModel s = studentList.get(index);

            if (s instanceof RegularStudentModel) {
                System.out.println(
                        "1. Name  2. Address  3. Class Name  4. Teacher  5. Math  6. Literature  7. English  8. Conduct");
                System.out.print("Select field to update: ");
                int opt = sc.nextInt();
                sc.nextLine();

                switch (opt) {
                    case 1: { // Name
                        String newName;
                        while (true) {
                            System.out.print("Enter new name: ");
                            newName = sc.nextLine().trim();
                            if (!Validator.isValidName(newName)) {
                                System.out.println("Invalid name! Only letters and spaces, 3-50 chars.");
                                continue;
                            }
                            break;
                        }
                        s.setFullName(newName);
                        break;
                    }

                    case 2: { // Address
                        String newAddress;
                        while (true) {
                            System.out.print("Enter new address: ");
                            newAddress = sc.nextLine().trim();
                            if (!Validator.isValidAddress(newAddress)) {
                                System.out.println("Invalid address! Must be at least 3 characters.");
                                continue;
                            }
                            break;
                        }
                        s.setAddress(newAddress);
                        break;
                    }

                    case 3: { // Class name
                        String newClassName;
                        while (true) {
                            System.out.print("Enter class name: ");
                            newClassName = sc.nextLine().trim();
                            if (!Validator.isValidName(newClassName)) {
                                System.out.println("Invalid class name! Only letters and spaces, 3-50 chars.");
                                continue;
                            }
                            break;
                        }
                        s.getClassroomModel().setClassName(newClassName);
                        break;
                    }

                    case 4: { // Teacher name
                        String newTeacher;
                        while (true) {
                            System.out.print("Enter new teacher: ");
                            newTeacher = sc.nextLine().trim();
                            if (!Validator.isValidName(newTeacher)) {
                                System.out.println("Invalid teacher name! Only letters and spaces, 3-50 chars.");
                                continue;
                            }
                            break;
                        }
                        s.getClassroomModel().setTeacherId(newTeacher);
                        break;
                    }

                    case 5: { // Math
                        double newMath;
                        while (true) {
                            System.out.print("Enter new Math score: ");
                            newMath = sc.nextDouble();
                            if (!Validator.isValidScore(newMath)) {
                                System.out.println("Invalid Math score! Must be 0-10.");
                                continue;
                            }
                            break;
                        }
                        s.getScore().setMath(newMath);
                        sc.nextLine(); // consume newline
                        break;
                    }

                    case 6: { // Literature
                        double newLiterature;
                        while (true) {
                            System.out.print("Enter new Literature score: ");
                            newLiterature = sc.nextDouble();
                            if (!Validator.isValidScore(newLiterature)) {
                                System.out.println("Invalid Literature score! Must be 0-10.");
                                continue;
                            }
                            break;
                        }
                        s.getScore().setLiterature(newLiterature);
                        sc.nextLine();
                        break;
                    }

                    case 7: { // English
                        double newEnglish;
                        while (true) {
                            System.out.print("Enter new English score: ");
                            newEnglish = sc.nextDouble();
                            if (!Validator.isValidScore(newEnglish)) {
                                System.out.println("Invalid English score! Must be 0-10.");
                                continue;
                            }
                            break;
                        }
                        s.getScore().setEnglish(newEnglish);
                        sc.nextLine();
                        break;
                    }

                    case 8: { // Conduct
                        String newConduct;
                        while (true) {
                            System.out.print("Enter new conduct: ");
                            newConduct = sc.nextLine().trim();
                            if (!Validator.isValidConduct(newConduct)) {
                                System.out.println("Invalid conduct! Letters only, 3-20 chars.");
                                continue;
                            }
                            break;
                        }
                        ((RegularStudentModel) s).setConduct(newConduct);
                        break;
                    }

                    default: {
                        System.out.println("Invalid choice!");
                        return;
                    }
                }

            } else if (s instanceof SpecializedStudentModel) {
                System.out.println(
                        "1. Name  2. Address  3. Class Name  4. Teacher  5. Math  6. Literature  7. English  8. Specialized Subject  9. Major Score");
                System.out.print("Select field to update: ");
                int opt = sc.nextInt();
                sc.nextLine();
                switch (opt) {
                    case 1: { // Name
                        String newName;
                        while (true) {
                            System.out.print("Enter new name: ");
                            newName = sc.nextLine().trim();
                            if (!Validator.isValidName(newName)) {
                                System.out.println("Invalid name! Only letters and spaces, 3-50 chars.");
                                continue;
                            }
                            break;
                        }
                        s.setFullName(newName);
                        break;
                    }

                    case 2: { // Address
                        String newAddress;
                        while (true) {
                            System.out.print("Enter new address: ");
                            newAddress = sc.nextLine().trim();
                            if (!Validator.isValidAddress(newAddress)) {
                                System.out.println("Invalid address! Must be at least 3 characters.");
                                continue;
                            }
                            break;
                        }
                        s.setAddress(newAddress);
                        break;
                    }

                    case 3: { // Class name
                        String newClassName;
                        while (true) {
                            System.out.print("Enter class name: ");
                            newClassName = sc.nextLine().trim();
                            if (!Validator.isValidName(newClassName)) {
                                System.out.println("Invalid class name! Only letters and spaces, 3-50 chars.");
                                continue;
                            }
                            break;
                        }
                        s.getClassroomModel().setClassName(newClassName);
                        break;
                    }

                    case 4: { // Teacher name
                        String newTeacher;
                        while (true) {
                            System.out.print("Enter new teacher: ");
                            newTeacher = sc.nextLine().trim();
                            if (!Validator.isValidName(newTeacher)) {
                                System.out.println("Invalid teacher name! Only letters and spaces, 3-50 chars.");
                                continue;
                            }
                            break;
                        }
                        s.getClassroomModel().setTeacherId(newTeacher);
                        break;
                    }

                    case 5: { // Math
                        double newMath;
                        while (true) {
                            System.out.print("Enter new Math score: ");
                            newMath = sc.nextDouble();
                            if (!Validator.isValidScore(newMath)) {
                                System.out.println("Invalid Math score! Must be 0-10.");
                                continue;
                            }
                            break;
                        }
                        s.getScore().setMath(newMath);
                        sc.nextLine(); // consume newline
                        break;
                    }

                    case 6: { // Literature
                        double newLiterature;
                        while (true) {
                            System.out.print("Enter new Literature score: ");
                            newLiterature = sc.nextDouble();
                            if (!Validator.isValidScore(newLiterature)) {
                                System.out.println("Invalid Literature score! Must be 0-10.");
                                continue;
                            }
                            break;
                        }
                        s.getScore().setLiterature(newLiterature);
                        sc.nextLine();
                        break;
                    }

                    case 7: { // English
                        double newEnglish;
                        while (true) {
                            System.out.print("Enter new English score: ");
                            newEnglish = sc.nextDouble();
                            if (!Validator.isValidScore(newEnglish)) {
                                System.out.println("Invalid English score! Must be 0-10.");
                                continue;
                            }
                            break;
                        }
                        s.getScore().setEnglish(newEnglish);
                        sc.nextLine();
                        break;
                    }

                    case 8: { // Conduct
                        String newConduct;
                        while (true) {
                            System.out.print("Enter new conduct: ");
                            newConduct = sc.nextLine().trim();
                            if (!Validator.isValidConduct(newConduct)) {
                                System.out.println("Invalid conduct! Letters only, 3-20 chars.");
                                continue;
                            }
                            break;
                        }
                        ((RegularStudentModel) s).setConduct(newConduct);
                        break;
                    }

                    case 9: {
                        String newMajorSubject;
                        while (true) {
                            System.out.print("Enter major subject: ");
                            newMajorSubject = sc.nextLine().trim();
                            if (!Validator.isValidName(newMajorSubject)) {
                                System.out.println("Invalid major subject! Only letters and spaces, 3-50 chars.");
                                continue;
                            }
                            break;
                        }
                        ((SpecializedStudentModel) s).setMajorSubject(newMajorSubject);
                        break;
                    }

                    default: {
                        System.out.println("Invalid choice!");
                        return;
                    }
                }
            }
            System.out.println("Update successful!");
            this.writeFile();
            return;
        }

    }

    // ========================= REMOVE ============================

    @Override
    public void remove() {
        while (true) {
            System.out.print("Enter ID to remove: ");
            String id = sc.nextLine();
            int index = searchById(id);

            if (index == -1) {
                System.out.println("Student not found!");
                continue;
            }

            System.out.print("Confirm delete (1: Yes / 2: No): ");
            int confirm = sc.nextInt();
            sc.nextLine();

            if (confirm == 1) {
                studentList.remove(index);
                totalStudents--;

                System.out.println("Student removed!");
                this.readFile();
                return;

            } else {
                System.out.println("Delete cancelled.");
                return;
            }
        }

    }

    // ========================= SEARCH ============================

    @Override
    public void search() {
        while (true) {
            System.out.print("Enter ID to search: ");
            String id = sc.nextLine();
            int index = searchById(id);

            if (index == -1) {
                System.out.println("Student not found!");
                continue;
            } else {
                System.out.println("Result:");
                studentList.get(index).display();
                return;
            }
        }

    }

    // ========================= PRINT LIST ============================

    @Override
    public void println() {
        if (studentList.isEmpty()) {
            System.out.println("The list is empty!");
            return;
        } else {
            System.out.println("Student list:");
            System.out.println(titleHeader);
            for (StudentModel s : studentList) {
                s.display();
            }
            return;
        }

    }

    // ========================= SEARCH BY ID ============================

    @Override
    public int searchById(String id) {
        if (studentList.size() == 0) {
            return -1;
        }
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public StudentModel searchObjectById(String id) {
        int index = searchById(id);
        return index == -1 ? null : studentList.get(index);
    }

    @Override
    public void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("txt/students.txt"));
            String line;
            studentList.clear();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                String id = parts[0].trim();
                String name = parts[1].trim();
                String address = parts[2].trim();
                String classId = parts[3].trim();
                String className = parts[4].trim();
                String teacherId = parts[5].trim();

                double math = Double.parseDouble(parts[6].trim());
                double literature = Double.parseDouble(parts[7].trim());
                double english = Double.parseDouble(parts[8].trim());

                ClassroomModel tempClassroom = new ClassroomModel(classId, className, teacherId);
                ScoreModel tempScoreModel = new ScoreModel(math, literature, english);

                String conduct = parts[9].trim();

                StudentModel temp = null;

                // ========================= PHÂN LOẠI ĐÚNG =========================
                if (!conduct.equals("null")) {

                    // ---- HỌC SINH THƯỜNG ----
                    temp = new RegularStudentModel(id, name, address, tempClassroom, tempScoreModel, conduct);

                } else {

                    // ---- HỌC SINH CHUYÊN ----
                    String majorSubject = parts[10].trim();
                    double majorScore = Double.parseDouble(parts[11].trim());

                    temp = new SpecializedStudentModel(id, name, address, tempClassroom, tempScoreModel,
                            majorSubject, majorScore);
                }

                studentList.add(temp);
            }

            reader.close();
            System.out.println("Đọc file thành công!");

        } catch (Exception e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
        totalStudents = studentList.size();
    }

    @Override
    public void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("txt/students.txt", false));
            for (int i = 0; i < studentList.size(); i++) {
                writer.write(studentList.get(i).toString());
                writer.newLine();
            }

            writer.close();
            System.out.println("✅ File written successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error writing file: " + e.getMessage());
        }
    }

    // ========================= MAIN TEST ============================

    public static void main(String[] args) {
        System.out.println("Total students: " + StudentList.getTotalStudents());

        StudentList list = new StudentList();
        list.readFile();
        list.println();
        list.add();
        list.update();
        list.println();
    }

}
