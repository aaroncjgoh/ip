package joe.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import joe.task.Deadline;
import joe.task.Event;
import joe.task.Task;
import joe.task.TaskList;
import joe.task.ToDo;

import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> todoList = new ArrayList<>();
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public void logTodoList(TaskList todoList) {
        try {
            File dir = new File("data");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            FileWriter fw = new FileWriter("data/joe.txt", false);

            for (Task task : todoList.getTodoList()) {
                fw.write(task.toString() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> loadTodoList() {
        File f = new File(filepath);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String task = s.nextLine();
                if (!task.isEmpty()) {
                    char taskType = task.charAt(1);
                    boolean isDone = task.charAt(4) == '1';
                    switch (taskType) {
                    case 'T': {
                        String description = task.split(" ", 2)[1];
                        this.todoList.add(new ToDo(description, isDone));
                        break;
                    }

                    case 'D': {
                        String description = task.split(" ", 2)[1];
                        String descriptionFinal = description.split("\\(")[0].strip();
                        String deadline = task.split("by:")[1].strip();
                        String deadlineFinal = deadline.substring(0, deadline.length() - 1);
                        this.todoList.add(new Deadline(descriptionFinal, formatDatesFromMemory(deadlineFinal), isDone));
                        break;
                    }

                    case 'E': {
                        String description = task.split(" ", 2)[1];
                        String descriptionFinal = description.split("\\(")[0].strip();
                        String from = task.split("from:")[1].split("to:")[0].strip();
                        String to = task.split("to: ")[1];
                        String toFinal = to.substring(0, to.length() - 1);
                        this.todoList.add(new Event(descriptionFinal, formatDatesFromMemory(from),
                                formatDatesFromMemory(toFinal), isDone));
                        break;
                    }

                    default: {
                        break;
                    }

                    }
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return this.todoList;
    }

    public String formatDatesFromMemory(String date) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String formattedDate = LocalDate.parse(date, inputFormat).format(outputFormat);

        return formattedDate;
    }
}