import java.util.ArrayList;

class TaskList {
    private ArrayList<Task> todoList;

    public TaskList(ArrayList<Task> todoList) {
        if (todoList == null) {
            this.todoList = new ArrayList<>();
        } else {
            this.todoList = todoList;
        }
    }

    public void addToList(Task task) {
        this.todoList.add(task);
        System.out.println("Got it. I've added this task: \n");
        System.out.println(task);
        System.out.println("\nNow you have " + this.todoList.size() + " tasks in the list.");
    }

    public void deleteFromList(int index) {
        Task task = this.todoList.get(index);
        this.todoList.remove(index);
        System.out.println("Got it. I'v removed this task from your list: \n");
        System.out.println(task);
        System.out.println("\nNow you have " + this.todoList.size() + " tasks in the list.");
    }

    public void markTaskAsDone(int index) {
        if (index > this.todoList.size()) {
            System.out.println("Invalid task number. Please try again.");
        } else {
            System.out.println("Nice I've marked this task as done:");
            Task task = todoList.get(index - 1);
            task.markAsDone();
            System.out.println(task);
        }
    }

    public void markTaskAsNotDone(int index) {
        if (index > this.todoList.size()) {
            System.out.println("Invalid task number. Please try again.");
        } else {
            System.out.println("Ok, I've marked the task as not done:");
            Task task = todoList.get(index - 1);
            task.markAsNotDone();
            System.out.println(task);
        }
    }

    public ArrayList<Task> getTodoList() {
        return this.todoList;
    }

    public int getLength() {
        return this.todoList.size();
    }
}