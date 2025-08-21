import java.util.Scanner;

public class Joe {
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
        } else {
            line();
            System.out.println("You just said: " + input);
            line();
            this.takeInput(scanner);
        }
    }
}
