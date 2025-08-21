import java.util.Scanner;
import java.util.ArrayList;

public class Joe {
    private ArrayList<Task> todoList = new ArrayList<>();
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
        if (input.equals("bye")) {
            this.byeText();
            scanner.close();

        } else if (input.toLowerCase().equals("list")) {
            line();
            this.print_todoList();
            this.takeInput(scanner);

        } else if (input.split(" ")[0].toLowerCase().equals("mark")) {
            this.markTaskAsDone(Integer.parseInt(input.split(" ")[1]));
            this.takeInput(scanner);

        } else if (input.split(" ")[0].toLowerCase().equals("unmark")) {
            this.markTaskAsNotDone(Integer.parseInt(input.split(" ")[1]));
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
        this.todoList.add(new Task(item));
        System.out.println("Added: " + item);
    }

    public void markTaskAsDone(int index) {
        if (index > this.todoList.size()) {
            System.out.println("Invalid task number. Please try again.");
            line();
        } else {
            System.out.println("Nice I've marked this task as done:");
            Task task = todoList.get(index - 1);
            task.markAsDone();
            System.out.println(task);
            line();
        }
    }

    public void markTaskAsNotDone(int index) {
        if (index > this.todoList.size()) {
            System.out.println("Invalid task number. Please try again.");
            line();
        } else {
            System.out.println("Ok, I've marked the task as not done:");
            Task task = todoList.get(index - 1);
            task.markAsNotDone();
            System.out.println(task);
            line();
        }
    }
}
