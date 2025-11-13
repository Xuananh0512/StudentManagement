//package lists;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.util.regex.Pattern;
//
//import interfaces.ILists;
//import models.RegularStudentModel;
//import models.ScoreModel;
//import models.SpecializedStudentModel;
//import models.StudentModel;
//import models.ClassroomModel;
//import java.io.BufferedReader;
//
//public class StudentList implements ILists {
//    private ArrayList<StudentModel> studentList;
//    private Scanner sc = new Scanner(System.in);
//    private String titleHeader = "Id | Name | Address | ClassId | ClassName | TeacherId | Math | Literature | English | Conduct | majorSubject | majorScore";
//
//    public StudentList() {
//        studentList = new ArrayList<>();
//    }
//
//    // ========================= UTILITY METHODS ============================
//
//    /**
//     * Helper method to safely read a double score (0-10) from the user, forcing re-entry on invalid input.
//     * @param prompt The message to display to the user.
//     * @return The valid score as a double.
//     */
//    private double readScore(String prompt) {
//        double score;
//        while (true) {
//            System.out.print(prompt);
//            if (!sc.hasNextDouble()) {
//                System.out.println("❌ Diem phai la so!");
//                sc.nextLine(); // Consume invalid input
//                continue;
//            }
//            score = sc.nextDouble();
//            sc.nextLine(); // Consume newline
//            if (score < 0 || score > 10) {
//                System.out.println("❌ Diem phai tu 0 den 10!");
//                continue;
//            }
//            return score;
//        }
//    }
//
//    // ========================= ADD ============================
//    @Override
//    public void add() {
//        while (true) {
//            String id;
//            while (true) {
//                System.out.print("Nhap ID can them: ");
//                id = sc.nextLine().trim();
//
//                if (id.isEmpty()) {
//                    System.out.println("❌ ID không được để trống. Vui lòng nhập lại!");
//                    continue;
//                }
//
//                if (searchById(id) != -1) {
//                    System.out.println("❌ Id da ton tai vui long nhap lai id!");
//                    continue;
//                }
//
//                if (!Pattern.matches("^[A-Za-z0-9]{2,10}$", id)) {
//                    System.out.println("❌ ID khong hop le (chi chua chu va so, tu 2-10 ky tu).");
//                    continue;
//                }
//                break;
//            }
//
//            int option;
//            while (true) {
//                System.out.println("1. Them hoc sinh thuong");
//                System.out.println("2. Them hoc sinh chuyen");
//                System.out.print("Chon loai: ");
//                if (!sc.hasNextInt()) {
//                    System.out.println("❌ Lua chon phai la so!");
//                    sc.nextLine();
//                    continue;
//                }
//                option = sc.nextInt();
//                sc.nextLine();
//                if (option == 1 || option == 2) break;
//                System.out.println("❌ Lua chon khong hop le!");
//            }
//
//            String fullName;
//            while (true) {
//                System.out.print("Nhap ho ten: ");
//                fullName = sc.nextLine().trim();
//                if (!Pattern.matches("^[A-Za-z\\s]{3,50}$", fullName)) {
//                    System.out.println("❌ Ho ten khong hop le (chi chu cai, toi da 50 ky tu).");
//                    continue;
//                }
//                break;
//            }
//
//            String address;
//            while (true) {
//                System.out.print("Nhap dia chi: ");
//                address = sc.nextLine().trim();
//                if (address.length() < 3) {
//                    System.out.println("❌ Dia chi phai tu 3 ky tu tro len.");
//                    continue;
//                }
//                break;
//            }
//
//            // --- Bổ sung ràng buộc cho Điểm số (0-10) ---
//            double math = readScore("Nhap diem toan (0-10): ");
//            double literature = readScore("Nhap diem ngu van (0-10): ");
//            double english = readScore("Nhap diem anh van (0-10): ");
//            ScoreModel score = new ScoreModel(math, literature, english);
//
//            // --- Bổ sung ràng buộc cho ClassroomModel ---
//            String classId;
//            while (true) {
//                System.out.print("Nhap id lop: ");
//                classId = sc.nextLine().trim();
//                if (!Pattern.matches("^[A-Za-z0-9]{2,10}$", classId)) {
//                    System.out.println("❌ ID Lop khong hop le (chi chua chu va so, tu 2-10 ky tu).");
//                    continue;
//                }
//                break;
//            }
//            String className;
//            while (true) {
//                System.out.print("Nhap ten lop: ");
//                className = sc.nextLine().trim();
//                if (!Pattern.matches("^[A-Za-z0-9\\s]{2,30}$", className)) {
//                    System.out.println("❌ Ten lop khong hop le (tu 2-30 ky tu, chi chu cai, so).");
//                    continue;
//                }
//                break;
//            }
//            String teacherId;
//            while (true) {
//                System.out.print("Nhap id giao vien chu nhiem: ");
//                teacherId = sc.nextLine().trim();
//                if (!Pattern.matches("^[A-Za-z0-9]{2,10}$", teacherId)) {
//                    System.out.println("❌ ID Giao vien khong hop le (chi chua chu va so, tu 2-10 ky tu).");
//                    continue;
//                }
//                break;
//            }
//            ClassroomModel classModel = new ClassroomModel(classId, className, teacherId);
//
//            if (option == 1) {
//                String conduct;
//                while (true) {
//                    System.out.print("Nhap hanh kiem: ");
//                    conduct = sc.nextLine().trim();
//                    if (!Pattern.matches("^[A-Za-z\\s]{3,20}$", conduct)) {
//                        System.out.println("❌ Hanh kiem khong hop le (chi chu cai, toi da 20 ky tu).");
//                        continue;
//                    }
//                    break;
//                }
//                StudentModel temp = new RegularStudentModel(id, fullName, address, classModel, score, conduct);
//                studentList.add(temp);
//                writeFile();
//                System.out.println("✅ Them hoc sinh thuong thanh cong!");
//                return;
//            } else {
//                String majorSubject;
//                while (true) {
//                    System.out.print("Nhap mon chuyen: ");
//                    majorSubject = sc.nextLine().trim();
//                    if (!Pattern.matches("^[A-Za-z\\s]{2,30}$", majorSubject)) {
//                        System.out.println("❌ Mon chuyen khong hop le (chi chu cai, toi da 30 ky tu).");
//                        continue;
//                    }
//                    break;
//                }
//                // Diem mon chuyen da co ràng buộc 0-10
//                double majorScore = readScore("Nhap diem mon chuyen (0-10): ");
//
//                StudentModel temp = new SpecializedStudentModel(id, fullName, address, classModel, score, majorSubject, majorScore);
//                studentList.add(temp);
//                writeFile();
//                System.out.println("✅ Them hoc sinh chuyen thanh cong!");
//                return;
//            }
//        }
//    }
//
//    // ========================= UPDATE ============================
//    @Override
//    public void update() {
//        System.out.print("Nhap ID can sua: ");
//        String id = sc.nextLine();
//        int index = searchById(id);
//
//        if (index == -1) {
//            System.out.println("❌ Khong tim thay hoc sinh!");
//            return;
//        }
//
//        StudentModel s = studentList.get(index);
//        int opt;
//
//        while (true) { // Vòng lặp để nhập lại nếu chọn mục không hợp lệ
//            if (s instanceof RegularStudentModel) {
//                System.out.println("1. Ten  2. Dia chi  3. ClassID  4. ClassName 5. TeacherID 6. Toan  7. Ngu Van  8. Anh Van 9. Hanh kiem 10. Huy");
//            } else if (s instanceof SpecializedStudentModel) {
//                System.out.println("1. Ten  2. Dia chi  3. ClassID  4. ClassName 5. TeacherID 6. Toan  7. Ngu Van  8. Anh Van 9. Mon chuyen  10. Diem mon chuyen 11. Huy");
//            }
//
//            System.out.print("Chon muc can sua: ");
//
//            if (!sc.hasNextInt()) {
//                System.out.println("❌ Lua chon phai la so!");
//                sc.nextLine();
//                continue;
//            }
//            opt = sc.nextInt();
//            sc.nextLine(); // Consume newline
//
//            boolean isValidOption = (s instanceof RegularStudentModel && opt >= 1 && opt <= 10) ||
//                    (s instanceof SpecializedStudentModel && opt >= 1 && opt <= 11);
//
//            if (!isValidOption) {
//                System.out.println("❌ Lua chon khong hop le!");
//                continue;
//            }
//
//            if ((s instanceof RegularStudentModel && opt == 10) || (s instanceof SpecializedStudentModel && opt == 11)) {
//                System.out.println("Huy thao tac cap nhat.");
//                return;
//            }
//
//            // Xử lý cập nhật cho các mục đã chọn
//            try {
//                switch (opt) {
//                    case 1: { // Ten
//                        String newFullName;
//                        while (true) {
//                            System.out.print("Nhap ten moi: ");
//                            newFullName = sc.nextLine().trim();
//                            if (!Pattern.matches("^[A-Za-z\\s]{3,50}$", newFullName)) {
//                                System.out.println("❌ Ho ten khong hop le (chi chu cai, toi da 50 ky tu).");
//                                continue;
//                            }
//                            s.setFullName(newFullName);
//                            break;
//                        }
//                        break;
//                    }
//                    case 2: { // Dia chi
//                        String newAddress;
//                        while (true) {
//                            System.out.print("Nhap dia chi moi: ");
//                            newAddress = sc.nextLine().trim();
//                            if (newAddress.length() < 3) {
//                                System.out.println("❌ Dia chi phai tu 3 ky tu tro len.");
//                                continue;
//                            }
//                            s.setAddress(newAddress);
//                            break;
//                        }
//                        break;
//                    }
//                    case 3: { // Class ID (Mục mới)
//                        String newClassId;
//                        while (true) {
//                            System.out.print("Nhap ID lop moi: ");
//                            newClassId = sc.nextLine().trim();
//                            if (!Pattern.matches("^[A-Za-z0-9]{2,10}$", newClassId)) {
//                                System.out.println("❌ ID Lop khong hop le (chi chua chu va so, tu 2-10 ky tu).");
//                                continue;
//                            }
//                            s.getClassroomModel().setClassId(newClassId);
//                            break;
//                        }
//                        break;
//                    }
//                    case 4: { // Class Name (Mục mới)
//                        String newClassName;
//                        while (true) {
//                            System.out.print("Nhap ten lop moi: ");
//                            newClassName = sc.nextLine().trim();
//                            if (!Pattern.matches("^[A-Za-z0-9\\s]{2,30}$", newClassName)) {
//                                System.out.println("❌ Ten lop khong hop le (tu 2-30 ky tu, chi chu cai, so).");
//                                continue;
//                            }
//                            s.getClassroomModel().setClassName(newClassName);
//                            break;
//                        }
//                        break;
//                    }
//                    case 5: { // Teacher ID
//                        String newTeacherId;
//                        while (true) {
//                            System.out.print("Nhap ID giao vien moi: ");
//                            newTeacherId = sc.nextLine().trim();
//                            if (!Pattern.matches("^[A-Za-z0-9]{2,10}$", newTeacherId)) {
//                                System.out.println("❌ ID Giao vien khong hop le (chi chua chu va so, tu 2-10 ky tu).");
//                                continue;
//                            }
//                            s.getClassroomModel().setTeacherId(newTeacherId);
//                            break;
//                        }
//                        break;
//                    }
//                    case 6: { // Toan
//                        s.getScore().setMath(readScore("Nhap diem mon toan moi (0-10): "));
//                        break;
//                    }
//                    case 7: { // Ngu Van
//                        s.getScore().setLiterature(readScore("Nhap diem ngu van moi (0-10): "));
//                        break;
//                    }
//                    case 8: { // Anh Van
//                        s.getScore().setEnglish(readScore("Nhap diem anh van moi (0-10): "));
//                        break;
//                    }
//                    case 9: { // Hanh kiem (Regular) / Mon chuyen (Specialized)
//                        if (s instanceof RegularStudentModel) {
//                            String newConduct;
//                            while (true) {
//                                System.out.print("Nhap hanh kiem moi: ");
//                                newConduct = sc.nextLine().trim();
//                                if (!Pattern.matches("^[A-Za-z\\s]{3,20}$", newConduct)) {
//                                    System.out.println("❌ Hanh kiem khong hop le (chi chu cai, toi da 20 ky tu).");
//                                    continue;
//                                }
//                                ((RegularStudentModel) s).setConduct(newConduct);
//                                break;
//                            }
//                        } else if (s instanceof SpecializedStudentModel) {
//                            String newMajorSubject;
//                            while (true) {
//                                System.out.print("Nhap mon chuyen moi: ");
//                                newMajorSubject = sc.nextLine().trim();
//                                if (!Pattern.matches("^[A-Za-z\\s]{2,30}$", newMajorSubject)) {
//                                    System.out.println("❌ Mon chuyen khong hop le (chi chu cai, toi da 30 ky tu).");
//                                    continue;
//                                }
//                                ((SpecializedStudentModel) s).setMajorSubject(newMajorSubject);
//                                break;
//                            }
//                        }
//                        break;
//                    }
//                    case 10: { // Diem mon chuyen (Specialized) / Huy (Regular)
//                        if (s instanceof SpecializedStudentModel) {
//                            ((SpecializedStudentModel) s).setMajorScore(readScore("Nhap diem mon chuyen moi (0-10): "));
//                        }
//                        break; // RegularStudentModel case 10: Huy đã được xử lý ở trên
//                    }
//                    // Case 11: Huy (Specialized) đã được xử lý ở trên
//                }
//                break; // Thoát vòng lặp while(true) sau khi cập nhật thành công
//            } catch (Exception e) {
//                System.out.println("❌ Lỗi trong quá trình cập nhật: " + e.getMessage() + ". Vui lòng thử lại.");
//                sc.nextLine(); // Clear scanner buffer if needed (although readScore handles it)
//            }
//        }
//
//        System.out.println("✅ Cap nhat thanh cong!");
//        this.writeFile();
//    }
//
//    // ========================= REMOVE ============================
//    @Override
//    public void remove() {
//        System.out.print("Nhap ID can xoa: ");
//        String id = sc.nextLine();
//        int index = searchById(id);
//
//        if (index == -1) {
//            System.out.println("❌ Khong tim thay hoc sinh!");
//            return;
//        }
//
//        int confirm;
//        while (true) {
//            System.out.print("Xac nhan xoa (1: Co / 2: Khong): ");
//            if (!sc.hasNextInt()) {
//                System.out.println("❌ Lua chon phai la so!");
//                sc.nextLine();
//                continue;
//            }
//            confirm = sc.nextInt();
//            sc.nextLine();
//            if (confirm == 1 || confirm == 2) break;
//            System.out.println("❌ Lua chon khong hop le!");
//        }
//
//        if (confirm == 1) {
//            studentList.remove(index);
//            writeFile();
//            System.out.println("✅ Da xoa hoc sinh!");
//        } else {
//            System.out.println("Huy thao tac xoa.");
//        }
//    }
//
//    // ========================= SEARCH ============================
//    @Override
//    public void search() {
//        System.out.print("Nhap ID can tim: ");
//        String id = sc.nextLine();
//        int index = searchById(id);
//
//        if (index == -1) {
//            System.out.println("❌ Khong tim thay hoc sinh!");
//        } else {
//            System.out.println("Ket qua:");
//            System.out.println(titleHeader);
//            studentList.get(index).display();
//        }
//    }
//
//    // ========================= PRINT LIST ============================
//    @Override
//    public void println() {
//        if (studentList.isEmpty()) {
//            System.out.println("Danh sach rong!");
//        } else {
//            System.out.println("Danh sach hoc sinh:");
//            System.out.println(titleHeader);
//            for (StudentModel s : studentList) {
//                s.display();
//            }
//        }
//    }
//
//    // ========================= SEARCH BY ID ============================
//    @Override
//    public int searchById(String id) {
//        if (studentList.size() == 0) {
//            return -1;
//        }
//        for (int i = 0; i < studentList.size(); i++) {
//            if (studentList.get(i).getId().equalsIgnoreCase(id)) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    @Override
//    public StudentModel searchObjectById(String id) {
//        int index = searchById(id);
//        return index == -1 ? null : studentList.get(index);
//    }
//
//    // ========================= FILE I/O ============================
//    @Override
//    public void readFile() {
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("txt/students.txt"));
//            String line;
//            studentList.clear();
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split("\\|");
//                // Check if all necessary parts are present (at least 12 for Specialized or 10 for Regular with null filler)
//                if (parts.length < 10) {
//                    System.out.println("Lỗi định dạng dòng trong file: Dữ liệu không đủ trường.");
//                    continue;
//                }
//
//                String id = parts[0].trim();
//                String name = parts[1].trim();
//                String address = parts[2].trim();
//                String classId = parts[3].trim();
//                String className = parts[4].trim();
//                String teacherId = parts[5].trim();
//
//                double math, literature, english;
//                try {
//                    math = Double.parseDouble(parts[6].trim());
//                    literature = Double.parseDouble(parts[7].trim());
//                    english = Double.parseDouble(parts[8].trim());
//                } catch (NumberFormatException e) {
//                    System.out.println("Lỗi định dạng điểm số trong file.");
//                    continue;
//                }
//
//                StudentModel temp = null;
//                ClassroomModel tempClassroom = new ClassroomModel(classId, className, teacherId);
//                ScoreModel tempScoreModel = new ScoreModel(math, literature, english);
//
//                // RegularStudentModel has Conduct (parts[9] is NOT "null")
//                if (!parts[9].trim().equalsIgnoreCase("null") && parts[9].trim().length() > 0) {
//                    String conduct = parts[9].trim();
//                    temp = new RegularStudentModel(id, name, address, tempClassroom, tempScoreModel, conduct);
//                }
//                // SpecializedStudentModel has majorSubject and majorScore (parts[9] IS "null" or empty, and parts.length >= 12)
//                else if (parts.length >= 12) {
//                    String majorSubject = parts[10].trim();
//                    double majorScore;
//                    try {
//                        majorScore = Double.parseDouble(parts[11].trim());
//                    } catch (NumberFormatException e) {
//                        System.out.println("Lỗi định dạng điểm chuyên trong file.");
//                        continue;
//                    }
//                    temp = new SpecializedStudentModel(id, name, address, tempClassroom, tempScoreModel, majorSubject, majorScore);
//                } else {
//                    System.out.println("Lỗi định dạng dòng trong file: Không xác định được loại học sinh.");
//                    continue;
//                }
//
//                studentList.add(temp);
//            }
//            reader.close();
//            System.out.println("✅ Đọc file thành công! Số học sinh: " + studentList.size());
//        } catch (FileNotFoundException e) {
//            System.out.println("❌ File không tồn tại!");
//        } catch (IOException e) {
//            System.out.println("❌ Lỗi đọc file: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("❌ Lỗi không xác định khi đọc file: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void writeFile() {// truyen vao 1 phan tu sinh vien
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter("txt/students.txt", false)); // false = ghi đè
//            for (int i = 0; i < studentList.size(); i++) {
//                writer.write(studentList.get(i).toString());// sinh vien. tostring
//                writer.newLine(); // xuống dòng
//            }
//            writer.close();
//            System.out.println("✅ Ghi file thành công!");
//        } catch (IOException e) {
//            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
//        }
//    }
//
//    // ========================= MAIN TEST ============================
//    public static void main(String[] args) {
//        StudentList list = new StudentList();
//        list.readFile();
//        list.add();
//        // list.println();
//        // list.update();
//        // list.println();
//        // list.search();
//        // list.remove();
//        // list.println();
//        list.println();
//    }
//}