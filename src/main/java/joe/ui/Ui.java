package joe.ui;

import java.util.List;
import java.util.Scanner;

import joe.task.Task;
import joe.task.TaskList;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

    public Ui() {

    }

    /**
     * Prints the welcome message onto the terminal.
     */
    public void welcomeText() {
        this.line();
        System.out.println("Hello I'm Joe\n" + "What can I do for you?");
        this.line();
    }

    /**
     * Prints a line that acts as a divider between input of user and output of joe.
     */
    public void line() {
        System.out.println("--------------------------------");
    }

    /**
     * Prints the exit message onto the terminal.
     */
    public void byeText() {
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    /**
     * Allows the user to input a string via the terminal. Returns the input as a
     * string.
     * 
     * @return Input as a string.
     */
    public String takeInput() {
        String input = scanner.nextLine();
        line();
        return input;
    }

    /**
     * Prints all the tasks in the todo list onto the terminal one after another.
     * 
     * @param todoList Current list of tasks.
     */
    public void printTodoList(TaskList todoList) {
        System.out.println("Your To-Do List:");
        for (int i = 0; i < todoList.getTodoList().size(); i++) {
            System.out.println((i + 1) + ". " + todoList.getTodoList().get(i));
        }
        line();
    }

    public void printMatches(List<Task> matches) {
        System.out.println("Here are the matching tasks in your list: ");
        if (matches.size() == 0) {
            System.out.println("No matches!");
        } else {
            for (int i = 0; i < matches.size(); i++) {
                System.out.println((i + 1) + ". " + matches.get(i));
            }
            line();
        }

    }
}
