import java.util.Scanner;

class Ui {
    private Scanner scanner = new Scanner(System.in);

    public Ui() {

    }

    public void welcomeText() {
        this.line();
        System.out.println("Hello I'm Joe\n" + "What can I do for you?");
        this.line();
    }

    public void line() {
        System.out.println("--------------------------------");
    }

    public void byeText() {
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    public String takeInput() {
        String input = scanner.nextLine();
        line();
        return input;
    }

    public void print_todoList(TaskList todoList) {
        System.out.println("Your To-Do List:");
        for (int i = 0; i < todoList.getTodoList().size(); i++) {
            System.out.println((i + 1) + ". " + todoList.getTodoList().get(i));
        }
        line();
    }
}