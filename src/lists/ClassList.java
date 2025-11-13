package lists;

import interfaces.ILists;
import java.util.ArrayList;
import java.util.Scanner;
import models.ClassroomModel;
import models.TeacherModel;

public class ClassList implements ILists {
    private ArrayList<ClassroomModel> classList;
    private Scanner sc = new Scanner(System.in);

    public ClassList() {
        classList = new ArrayList<>();
    }

    // ========================= ADD ============================
    @Override
    public void add() {
        while (true) {
            System.out.print("Enter class ID: ");
            String id = sc.nextLine();

            if (searchById(id) != -1) {
                System.out.println("Class ID already exists, please enter again");
                continue;
            }

            System.out.print("Enter class name: ");
            String name = sc.nextLine();

            System.out.println("Enter teacher ");
            String teacher = sc.nextLine();

            classList.add(new ClassroomModel(id, name, teacher));
            System.out.println("Class added successfully!");
            break;
        }
    }

    // ========================= UPDATE ============================
    @Override
    public void update() {
        System.out.print("Enter class ID to update: ");
        String id = sc.nextLine();
        int index = searchById(id);

        if (index == -1) {
            System.out.println("Class not found!");
            return;
        }

        ClassroomModel c = classList.get(index);

        System.out.println("1. Class name  2. Teacher info");
        System.out.print("Select field to update: ");
        int opt = sc.nextInt();
        sc.nextLine();

        switch (opt) {
            case 1 : {
                System.out.print("Enter new class name: ");
                c.setClassName(sc.nextLine());
            } break;
            case 2 : {
                System.out.println("Enter new teacher info:");
                c.setTeacherId(sc.nextLine());

            } break;
            default : {
                System.out.println("Invalid choice!");
                return;
            }
        }

        System.out.println("Update successful!");
    }

    // ========================= REMOVE ============================
    @Override
    public void remove() {
        System.out.print("Enter class ID to remove: ");
        String id = sc.nextLine();
        int index = searchById(id);

        if (index == -1) {
            System.out.println("Class not found!");
            return;
        }

        System.out.print("Confirm delete (1: Yes / 2: No): ");
        int confirm = sc.nextInt();
        sc.nextLine();

        if (confirm == 1) {
            classList.remove(index);
            System.out.println("Class removed successfully!");
        } else {
            System.out.println("Delete cancelled!");
        }
    }

    // ========================= SEARCH ============================
    @Override
    public void search() {
        System.out.print("Enter class ID to search: ");
        String id = sc.nextLine();
        int index = searchById(id);

        if (index == -1) {
            System.out.println("Class not found!");
        } else {
            System.out.println("Search result:");
            classList.get(index).output();
        }
    }

    // ========================= PRINT LIST ============================
    @Override
    public void println() {
        if (classList.isEmpty()) {
            System.out.println("Class list is empty!");
        } else {
            System.out.println("Class list:");
            for (ClassroomModel c : classList) {
                c.output();
            }
        }
    }

    // ========================= SEARCH BY ID ============================
    @Override
    public int searchById(String id) {
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
    public void readFile(){

    }
    @Override
    public void writeFile() {

    }

    // ========================= MAIN TEST ============================
    public static void main(String[] args) {
        ClassList list = new ClassList();
        list.add();
        list.println();
        list.update();
        list.println();
        list.search();
        list.remove();
        list.println();
    }
}
