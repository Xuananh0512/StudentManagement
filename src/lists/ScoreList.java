//package lists;
//
//import interfaces.ILists;
//import java.util.ArrayList;
//import java.util.Scanner;
//import models.ScoreModel;
//
//public class ScoreList implements ILists {
//    private ArrayList<ScoreModel> scoreList;
//    private ArrayList<String> studentIds; // dùng để gán ID cho mỗi ScoreModel
//    private Scanner sc = new Scanner(System.in);
//
//    public ScoreList() {
//        scoreList = new ArrayList<>();
//        studentIds = new ArrayList<>();
//    }
//
//    // ========================= ADD ============================
//    @Override
//    public void add() {
//        while (true) {
//            System.out.print("Enter student ID to add score: ");
//            String id = sc.nextLine();
//
//            if (searchById(id) != -1) {
//                System.out.println("ID already exists, please enter again!");
//                continue;
//            }
//
//            ScoreModel score = new ScoreModel();
//            score.input();
//
//            scoreList.add(score);
//            studentIds.add(id);
//
//            System.out.println("Score added successfully!");
//            break;
//        }
//    }
//
//    // ========================= UPDATE ============================
//    @Override
//    public void update() {
//        System.out.print("Enter student ID to update score: ");
//        String id = sc.nextLine();
//        int index = searchById(id);
//
//        if (index == -1) {
//            System.out.println("Student not found!");
//            return;
//        }
//
//        ScoreModel s = scoreList.get(index);
//
//        System.out.println("Select field to update:");
//        System.out.println("1. Math");
//        System.out.println("2. Literature");
//        System.out.println("3. English");
//        System.out.println("4. All");
//        System.out.print("Your choice: ");
//        int opt = sc.nextInt();
//        sc.nextLine();
//
//        switch (opt) {
//            case 1:
//                System.out.print("Enter new Math score: ");
//                s.setMath(sc.nextDouble());
//                sc.nextLine();
//                break;
//            case 2:
//                System.out.print("Enter new Literature score: ");
//                s.setLiterature(sc.nextDouble());
//                sc.nextLine();
//                break;
//            case 3:
//                System.out.print("Enter new English score: ");
//                s.setEnglish(sc.nextDouble());
//                sc.nextLine();
//                break;
//            case 4:
//                s.input();
//                break;
//            default:
//                System.out.println("Invalid choice!");
//                return;
//        }
//
//        System.out.println("Update successful!");
//    }
//
//    // ========================= REMOVE ============================
//    @Override
//    public void remove() {
//        System.out.print("Enter student ID to remove score: ");
//        String id = sc.nextLine();
//        int index = searchById(id);
//
//        if (index == -1) {
//            System.out.println("Student not found!");
//            return;
//        }
//
//        System.out.print("Confirm delete (1: Yes / 2: No): ");
//        int confirm = sc.nextInt();
//        sc.nextLine();
//
//        if (confirm == 1) {
//            scoreList.remove(index);
//            studentIds.remove(index);
//            System.out.println("Score removed successfully!");
//        } else {
//            System.out.println("Delete cancelled!");
//        }
//    }
//
//    // ========================= SEARCH ============================
//    @Override
//    public void search() {
//        System.out.print("Enter student ID to search score: ");
//        String id = sc.nextLine();
//        int index = searchById(id);
//
//        if (index == -1) {
//            System.out.println("Student not found!");
//        } else {
//            System.out.println("Search result:");
//            System.out.println("Student ID: " + studentIds.get(index));
//            scoreList.get(index).display();
//            System.out.println("Average: " + scoreList.get(index).average());
//        }
//    }
//
//    // ========================= PRINT LIST ============================
//    @Override
//    public void println() {
//        if (scoreList.isEmpty()) {
//            System.out.println("Score list is empty!");
//        } else {
//            System.out.println("===== SCORE LIST =====");
//            for (int i = 0; i < scoreList.size(); i++) {
//                System.out.println("Student ID: " + studentIds.get(i) + " | " +
//                        scoreList.get(i).toString() +
//                        " | Average: " + String.format("%.2f", scoreList.get(i).average()));
//            }
//        }
//    }
//
//    // ========================= SEARCH BY ID ============================
//    @Override
//    public int searchById(String id) {
//        for (int i = 0; i < studentIds.size(); i++) {
//            if (studentIds.get(i).equalsIgnoreCase(id)) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    @Override
//    public Object searchObjectById(String id) {
//        int index = searchById(id);
//        return index == -1 ? null : scoreList.get(index);
//    }
//    @Override
//    public void readFile(){
//
//    }
//    @Override
//    public void writeFile() {
//
//    }
//
//    // ========================= MAIN TEST ============================
//    public static void main(String[] args) {
//        ScoreList list = new ScoreList();
//        list.add();
//        list.println();
//        list.update();
//        list.println();
//        list.search();
//        list.remove();
//        list.println();
//    }
//}
