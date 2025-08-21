public class Joe {
    public static void main(String[] args) {
        Joe joe = new Joe();
        joe.endText();
        joe.byeText();
    }

    public Joe() {
        // Constructor can be used for initialization if needed
        System.out.println("Hello I'm Joe\n" + "What can I do for you?");
    }

    public void endText() {
        System.out.println("--------------------------------");
    }

    public void byeText() {
        System.out.println("Bye. Hope to see you again soon!");
        endText();
    }
}
