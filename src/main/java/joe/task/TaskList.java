package joe.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> todoList;

    /**
     * Creates a list of tasks. Allows for direct manipulation of the tasks in the
     * list.
     * 
     * @param todoList ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> todoList) {
        if (todoList == null) {
            this.todoList = new ArrayList<>();
        } else {
            this.todoList = todoList;
        }
    }

    /**
     * Adds a task to the TaskList object.
     * 
     * @param task Task object.
     */
    public void addToList(Task task) {
        this.todoList.add(task);
        System.out.println("Got it. I've added this task: \n");
        System.out.println(task);
        System.out.println("\nNow you have " + this.todoList.size() + " tasks in the list.");
    }

    /**
     * Deletes a task from the TaskList object.
     * 
     * @param index Index of the task to be deleted from the TaskList.
     */
    public void deleteFromList(int index) {
        Task task = this.todoList.get(index);
        this.todoList.remove(index);
        System.out.println("Got it. I'v removed this task from your list: \n");
        System.out.println(task);
        System.out.println("\nNow you have " + this.todoList.size() + " tasks in the list.");
    }

    /**
     * Marks a task as done in the TaskList.
     * 
     * @param index Index of the task to be marked as done.
     */
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

    /**
     * Marks a task as not done in the TaskList.
     * 
     * @param index Index of the task to be marked as not done.
     */
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

    public Task getTask(int index) {
        return this.todoList.get(index);
    }
}