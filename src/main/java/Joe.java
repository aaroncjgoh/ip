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
        line();
        if (input.equals("bye")) {
            this.byeText();
            scanner.close();

        } else if (input.toLowerCase().equals("list")) {
            this.print_todoList();
            this.takeInput(scanner);

        } else if (input.split(" ")[0].toLowerCase().equals("mark")) {
            this.markTaskAsDone(Integer.parseInt(input.split(" ")[1]));
            this.takeInput(scanner);

        } else if (input.split(" ")[0].toLowerCase().equals("unmark")) {
            this.markTaskAsNotDone(Integer.parseInt(input.split(" ")[1]));
            this.takeInput(scanner);

        } else if (input.split(" ")[0].toLowerCase().equals("todo")) {
            String[] parts = input.split(" ", 2);
            String description = parts[1];
            this.addToList(new ToDo(description));
            this.takeInput(scanner);

        } else if (input.split(" ")[0].toLowerCase().equals("deadline")) {
            String initial = input.split(" ", 2)[1];
            String description = initial.split(" /by ")[0].trim();
            String by = initial.split(" /by ")[1].trim();
            this.addToList(new Deadline(description, by));
            this.takeInput(scanner); 

        } else if (input.split(" ")[0].toLowerCase().equals("event")) {
            String initial = input.split(" ", 2)[1];
            String description = initial.split(" /from ")[0].trim();
            String from = initial.split(" /from ")[1].split(" /to ")[0].trim();
            String to = initial.split(" /to ")[1].trim();
            this.addToList(new Event(description, from, to));
            this.takeInput(scanner);
            
        } else {
            System.out.println("Sorry I don't recognise that command...");
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

    public void addToList(Task task) {
        this.todoList.add(task);
        System.out.println("Got it. I've added this task: \n");
        System.out.println(task);
        System.out.println("\nNow you have " + this.todoList.size() + " tasks in the list.");
        line();
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
