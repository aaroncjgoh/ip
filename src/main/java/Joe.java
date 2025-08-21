import java.util.Scanner;
import java.util.ArrayList;

public class Joe {
    private ArrayList<String> todoList = new ArrayList<>();
    public static void main(String[] args) {
        Joe joe = new Joe();
    }

    public Joe() {
        // Constructor can be used for initialization if needed
        Scanner scanner = new Scanner(System.in);
        this.line();
        System.out.println("Hello I'm Joe\n" + "What can I do for you?");
        this.line();
        this.takeInput(scanner);
    }

    public void line() {
        System.out.println("--------------------------------");
    }

    public void byeText() {
        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    public void takeInput(Scanner scanner) {
        String input = scanner.nextLine();
        if (input.toLowerCase().equals("bye")) {
            this.byeText();
        } else if (input.toLowerCase().equals("list")) {
            line();
            this.print_todoList();
            this.takeInput(scanner);
        } else {
            line();
            this.addToList(input);
            line();
            this.takeInput(scanner);
        }
    }

    public void print_todoList() {
        System.out.println("Your To-Do List:");
        for (int i = 0; i < this.todoList.size(); i++) {
            System.out.println((i + 1) + ". " + this.todoList.get(i));
        }
        line();
    }

    public void addToList(String item) {
        this.todoList.add(item);
        System.out.println("Added: " + item);
    }
}
