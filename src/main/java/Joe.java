import java.util.Scanner;
import java.util.ArrayList;

public class Joe {
    private ArrayList<Task> todoList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Joe joe = new Joe();
    }

    public Joe() {
        // Constructor can be used for initialization if needed
        this.line();
        System.out.println("Hello I'm Joe\n" + "What can I do for you?");
        this.line();
        this.takeInput();
    }

    public void line() {
        System.out.println("--------------------------------");
    }

    public void byeText() {
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    public void takeInput() {
        String input = scanner.nextLine();
        line();

        try {
            this.executeCommand(input);
        } catch (InvalidJoeInputException e) {
            System.out.println(e.getMessage());
            line();
            takeInput();
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

    public void executeCommand(String input) throws InvalidJoeInputException {
        String[] parts = input.split(" ");
        String command = parts[0];
        switch (command) {
            case "bye": {
                this.byeText();
                this.scanner.close();
                break;
            }

            case "list": {
                this.print_todoList();
                this.takeInput();
                break;
            }

            case "mark": {
                if (parts.length < 2) {
                    throw new InvalidJoeInputException(command);
                }

                if (Integer.parseInt(parts[1]) > this.todoList.size()) {
                    throw new InvalidJoeInputException(command, "Invalid index");
                }

                this.markTaskAsDone(Integer.parseInt(parts[1]));
                this.takeInput();
                break;
            }

            case "unmark": {
                if (parts.length < 2) {
                    throw new InvalidJoeInputException(command);
                }

                if (Integer.parseInt(parts[1]) > this.todoList.size()) {
                    throw new InvalidJoeInputException(command, "Invalid index");
                }

                this.markTaskAsNotDone(Integer.parseInt(parts[1]));
                this.takeInput();
                break;
            }

            case "todo": {
                if (parts.length < 2) {
                    throw new InvalidJoeInputException(command);
                }
                
                String description = input.split(" ", 2)[1];
                this.addToList(new ToDo(description));
                this.takeInput();
                break;
            }

            case "deadline": {
                if (parts.length < 2) {
                    throw new InvalidJoeInputException(command);
                }
                
                String description = input.split(" ", 2)[1].split(" /by ")[0].trim();
                String by = input.split(" /by ")[1].trim();
                this.addToList(new Deadline(description, by));
                this.takeInput(); 
                break;
            }

            case "event": {
                if (parts.length < 2) {
                    throw new InvalidJoeInputException(command);
                }
                
                String description = input.split(" /from ")[0].trim();
                String from = input.split(" /from ")[1].split(" /to ")[0].trim();
                String to = input.split(" /to ")[1].trim();
                this.addToList(new Event(description, from, to));
                this.takeInput();
                break;
            }

            default: {
                throw new InvalidJoeInputException();
            }
        }   

    }
}
