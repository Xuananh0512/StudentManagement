package lists;

import java.io.*;
import java.lang.classfile.ClassModel;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import interfaces.ILists;
import models.RegularStudentModel;
import models.ScoreModel;
import models.SpecializedStudentModel;
import models.StudentModel;
import models.ClassroomModel;
import java.io.BufferedReader;

public class StudentList implements ILists {
    private ArrayList<StudentModel> studentList;
    private Scanner sc = new Scanner(System.in);
    private String titleHeader = "Id | Name | Address | ClassId | ClassName | TeacherId | Math | Literature | English | Conduct | majorSubject | majorScore";

    public StudentList() {
        studentList = new ArrayList<>();
    }

    // ========================= ADD ============================

    @Override
    public void add() {
        while (true) {
            String id;

            while (true) {
                System.out.print("Nhap ID can them: ");
                id = sc.nextLine().trim();

                if (id.isEmpty()) {
                    System.out.println("ID không được để trống. Vui lòng nhập lại!");
                    continue;
                }

                if (searchById(id) != -1) {
                    System.out.println("Id da ton tai vui long nhap lai id!");
                    continue;
                }

                if (!Pattern.matches("^[A-Za-z0-9]{2,10}$", id)) {
                    System.out.println("ID khong hop le (chi chua chu va so, tu 2-10 ky tu).");
                    continue;
                }
                break;

            }


            int option;

            while (true) {

                System.out.println("1. Them hoc sinh thuong");
                System.out.println("2. Them hoc sinh chuyen");
                System.out.print("Chon loai: ");
                if (!sc.hasNextInt()) {
                    System.out.println("Lua chon phai la so!");

                    sc.nextLine();

                    continue;

                }
                option = sc.nextInt();
                sc.nextLine();
                if (option == 1 || option == 2) break;
                System.out.println("Lua chon khong hop le!");
            }


            String fullName;
            while (true) {

                System.out.print("Nhap ho ten: ");
                fullName = sc.nextLine().trim();
                if (!Pattern.matches("^[A-Za-z\\s]{3,50}$", fullName)) {
                    System.out.println("Ho ten khong hop le (chi chu cai, toi da 50 ky tu).");
                    continue;
                }
                break;
            }


            String address;

            while (true) {
                System.out.print("Nhap dia chi: ");
                address = sc.nextLine().trim();
                if (address.length() < 3) {
                    System.out.println("Dia chi phai tu 3 ky tu tro len.");
                    continue;
                }
                break;
            }

            ScoreModel score;
//            while (true) {
            System.out.println("Nhap diem toan: ");
            double math = sc.nextDouble();
            System.out.println("Nhap diem ngu van : ");
            double literature = sc.nextDouble();
            System.out.println("Nhap diem anh van : ");
            double english = sc.nextDouble();
            sc.nextLine();
            score = new ScoreModel(math, literature, english);
//            }

            ClassroomModel classModel;
//            while (true) {
            System.out.println("Nhap id lop: ");
            String classId = sc.nextLine();
            System.out.println("Nhap ten lop: ");
            String className = sc.nextLine();
            System.out.println("Nhap ten giao vien chu nhiem: ");
            String teacherId = sc.nextLine();
            classModel = new ClassroomModel(classId, className, teacherId);
//           }

            if (option == 1) {
                String conduct;
                while (true) {
                    System.out.print("Nhap hanh kiem: ");
                    conduct = sc.nextLine().trim();
                    if (!Pattern.matches("^[A-Za-z\\s]{3,20}$", conduct)) {
                        System.out.println("Hanh kiem khong hop le (chi chu cai, toi da 20 ky tu).");
                        continue;
                    }
                    break;
                }
                StudentModel temp = new RegularStudentModel(id, fullName, address, classModel, score, conduct);
                studentList.add(temp);
                writeFile();
                System.out.println("Them hoc sinh thuong thanh cong!");
                return;
            } else {
                String majorSubject;
                while (true) {
                    System.out.print("Nhap mon chuyen: ");
                    majorSubject = sc.nextLine().trim();
                    if (!Pattern.matches("^[A-Za-z\\s]{2,30}$", majorSubject)) {
                        System.out.println("Mon chuyen khong hop le (chi chu cai, toi da 30 ky tu).");
                        continue;
                    }
                    break;
                }
                double majorScore;
                while (true) {
                    System.out.print("Nhap diem mon chuyen: ");
                    if (!sc.hasNextDouble()) {
                        System.out.println("Diem phai la so!");
                        sc.nextLine();
                        continue;
                    }
                    majorScore = sc.nextDouble();
                    sc.nextLine();
                    if (majorScore < 0 || majorScore > 10) {
                        System.out.println("Diem phai tu 0 den 10!");
                        continue;
                    }
                    break;
                }
                StudentModel temp = new SpecializedStudentModel(id, fullName, address, classModel, score, majorSubject, majorScore);
                studentList.add(temp);
                writeFile();
                System.out.println("Them hoc sinh chuyen thanh cong!");
            }
        }
    }


    // ========================= UPDATE ============================

    @Override

    public void update() {

        System.out.print("Nhap ID can sua: ");
        String id = sc.nextLine();
        int index = searchById(id);

        if (index == -1) {
            System.out.println("Khong tim thay hoc sinh!");
            return;
        }

        StudentModel s = studentList.get(index);

        if (s instanceof RegularStudentModel) {
            System.out.println("1. Ten  2. Dia chi  3. Ten lop  4. Giao vien  5. Toan  6. Ngu Van  7. Anh Van 8. Hanh kiem");
            System.out.print("Chon muc can sua: ");
            int opt = sc.nextInt();
            sc.nextLine();


            switch (opt) {
                case 1: {
                    System.out.print("Nhap ten moi: ");
                    s.setFullName(sc.nextLine().trim());
                    break;
                }

                case 2: {
                    System.out.print("Nhap dia chi moi: ");
                    s.setAddress(sc.nextLine().trim());
                    break;
                }

                case 3: {
                    System.out.print("Nhap hanh kiem moi: ");
                    ((RegularStudentModel) s).setConduct(sc.nextLine().trim());
                    break;
                }

                case 4: {
                    System.out.print("Nhap giao vien moi: ");
                    s.getClassroomModel().setTeacherId(sc.nextLine().trim());
                    break;
                }

                case 5: {
                    System.out.print("Nhap diem mon toan moi: ");
                    s.getScore().setMath(sc.nextDouble());
                    break;
                }

//                ....


                default: {
                    System.out.println("Lua chon khong hop le!");
                    return;
                }
            }

        } else if (s instanceof SpecializedStudentModel) {
            System.out.println("1. Ten  2. Dia chi  3. Ten lop  4. Giao vien  5. Toan  6. Ngu Van  7. Anh Van 8. Mon chuyen  9. Diem mon chuyen");
            System.out.print("Chon muc can sua: ");
            int opt = sc.nextInt();
            sc.nextLine();
            switch (opt) {
                case 1: {
                    System.out.print("Nhap ten moi: ");
                    s.setFullName(sc.nextLine().trim());
                }
                case 2: {
                    System.out.print("Nhap dia chi moi: ");
                    s.setAddress(sc.nextLine().trim());
                }
                case 3: {
                    System.out.print("Nhap mon chuyen moi: ");
                    ((SpecializedStudentModel) s).setMajorSubject(sc.nextLine().trim());
                }
                case 4: {
                    System.out.print("Nhap diem mon chuyen moi: ");
                    double newScore = sc.nextDouble();
                    sc.nextLine();
                    ((SpecializedStudentModel) s).setMajorScore(newScore);
                }
//                ....
                default: {
                    System.out.println("Lua chon khong hop le!");
                    return;
                }
            }
        }
        System.out.println("Cap nhat thanh cong!");
        this.writeFile();
    }

    // ========================= REMOVE ============================

    @Override
    public void remove() {
        System.out.print("Nhap ID can xoa: ");
        String id = sc.nextLine();
        int index = searchById(id);

        if (index == -1) {
            System.out.println("Khong tim thay hoc sinh!");
            return;
        }


        System.out.print("Xac nhan xoa (1: Co / 2: Khong): ");
        int confirm = sc.nextInt();
        sc.nextLine();

        if (confirm == 1) {
            studentList.remove(index);
            System.out.println("Da xoa hoc sinh!");
        } else {
            System.out.println("Huy thao tac xoa.");
        }

    }


    // ========================= SEARCH ============================

    @Override

    public void search() {
        System.out.print("Nhap ID can tim: ");
        String id = sc.nextLine();
        int index = searchById(id);

        if (index == -1) {
            System.out.println("Khong tim thay hoc sinh!");
        } else {
            System.out.println("Ket qua:");
            studentList.get(index).display();
        }

    }


    // ========================= PRINT LIST ============================

    @Override
    public void println() {
        if (studentList.isEmpty()) {
            System.out.println("Danh sach rong!");
        } else {
            System.out.println("Danh sach hoc sinh:");
            System.out.println(titleHeader);
            for (StudentModel s : studentList) {
                s.display();
            }

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
                String name = parts[1];
                String address = parts[2];
                String classId = parts[3];
                String className = parts[4];
                String teacherId = parts[5];
                double math = Double.parseDouble(parts[6]);
                double literature = Double.parseDouble(parts[7]);
                double english = Double.parseDouble(parts[8]);
                StudentModel temp = null;
                ClassroomModel tempClassroom = new ClassroomModel(classId, className, teacherId);
                ScoreModel tempScoreModel = new ScoreModel(math, literature, english);
                if (parts[9].equals("null")) {
                    String majorSubject = parts[10];
                    double majorScore = Double.parseDouble(parts[11]);
                    temp = new SpecializedStudentModel(id, name, address, tempClassroom, tempScoreModel, majorSubject, majorScore);
                    studentList.add(temp);
                } else {
                    String conduct = parts[9];
                    temp = new RegularStudentModel(id, name, address, tempClassroom, tempScoreModel, conduct);
                    studentList.add(temp);
                }
            }
            reader.close();
            System.out.println("✅ Đọc file thành công! Số học sinh: " + studentList.size());
        } catch (FileNotFoundException e) {
            System.out.println("File không tồn tại!");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
    }


    @Override
    public void writeFile() {// truyen vao 1 phan tu sinh vien
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("txt/students.txt", false)); // false = ghi đè
            for (int i = 0; i < studentList.size(); i++) {
                writer.write(studentList.get(i).toString());// sinh vien. tostring
                writer.newLine(); // xuống dòng
            }

            writer.close();
            System.out.println("✅ Ghi file thành công!");
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
        }
    }


    // ========================= MAIN TEST ============================

    public static void main(String[] args) {
        StudentList list = new StudentList();
        list.readFile();
        list.add();
//        list.println();

//        list.update();
//        list.println();
//        list.search();
//        list.remove();
//        list.println();
//        list.readFile();
        list.println();


    }

}